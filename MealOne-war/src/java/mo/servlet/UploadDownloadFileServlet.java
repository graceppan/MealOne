/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.servlet;

/**
 *
 * @author hasee
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mo.ejb.DishService;
import mo.mb.DishMB;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;

    @EJB
    private DishService dishService;

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        if (fileName == null || fileName.equals("")) {
            throw new ServletException("File Name can't be null or empty");
        }
        File file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileName);
        if (!file.exists()) {
            throw new ServletException("File doesn't exists on server.");
        }
        System.out.println("File location on server::" + file.getAbsolutePath());
        ServletContext ctx = getServletContext();
        InputStream fis = new FileInputStream(file);
        String mimeType = ctx.getMimeType(file.getAbsolutePath());
        response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ServletOutputStream os = response.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read = 0;
        while ((read = fis.read(bufferData)) != -1) {
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
        System.out.println("File downloaded at client successfully");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getServletContext().getRealPath("/");
        System.out.println("********path=" + path + "*********");
        String[] arrExtension = {".gif", ".jpg", ".bmp", ".png", ".JPG",".jpeg"};
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html><head></head><body>");
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                String fileName = fileItem.getName();
                String strExtension = "";
                strExtension = fileName.substring(fileName.lastIndexOf("."));
                System.out.println("!!!!!" + strExtension);
                boolean flag = false;
                for (int i = 0; i < arrExtension.length; i++) {
                    if (strExtension.equals(arrExtension[i])) {
                        System.out.println("FieldName=" + fileItem.getFieldName());
                        System.out.println("FileName=" + fileItem.getName());
                        System.out.println("ContentType=" + fileItem.getContentType());
                        System.out.println("Size in bytes=" + fileItem.getSize());
                        System.out.println("********" + request.getServletContext().getAttribute("FILES_DIR") + "*********");
//                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
//                        System.out.println(df.format(new Date()));
//                        
//                        fileName = df.format(new Date()) + fileItem.getName();
                        File file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileItem.getName());
                        //File file = new File(path + File.separator + "images" + File.separator + fileItem.getName());
                        //File file = new File(path + "\\img" + fileItem.getName());
                        String relativePath = "\\img\\" + fileItem.getName();
                        System.out.println("*********" + request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileItem.getName() + "********");
                        System.out.println("|********" + "Absolute Path at server=" + file.getAbsolutePath() + "********|");
                        fileItem.write(file);
                        DishMB.get_Dish().setImage(relativePath);
                        createDish();
                        out.write("File " + fileItem.getName() + " uploaded successfully.");
//                        out.write(<h:commandButton value="Homepage" action="homePage?faces-redirect=true"/>);
                        flag = true;
                    }
                }
                if (flag == false) {
                    fileName = "please choose an image file!";
                }
                //out.write("<br>");
                //out.write("<a href=\"UploadDownloadFileServlet?fileName="+fileItem.getName()+"\">Download "+fileItem.getName()+"</a>");
            }
        } catch (FileUploadException e) {
            out.write("Exception in uploading file.");
        } catch (Exception e) {
            out.write("Exception in uploading file.");
        }
        out.write("</body></html>");
    }

    public void createDish() {
        dishService.create(DishMB.get_Dish());
    }

    public DishService getDishService() {
        return dishService;
    }

    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

}
