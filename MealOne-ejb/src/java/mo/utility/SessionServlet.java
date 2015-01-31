/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.utility;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hasee
 */
public class SessionServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,
            java.io.IOException {

        res.setContentType("text/html");
        java.io.PrintWriter out = res.getWriter();
        out.println("<HTML>");
        out.println("<HEAD><TITLE>User Example</TITLE></HEAD>");
        out.println("<BODY>");

        String username = req.getRemoteUser();
        if (username == null) {
            out.println("Hello. You are not logged in.");
        } else if ("Bob".equals(username)) {
            out.println("Hello, Bob. Nice to see you again.");
        } else {
            out.println("Hello, " + username + ".");
        }
        out.println("</BODY>");
        out.println("</HTML>");
        out.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String pin = req.getParameter("pin");

        if (!allowUser(account, password, pin)) {
            out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
            out.println("<BODY>Your login and password are invalid.<BR>");
            out.println("You may want to <A HREF=\"/login.html\">try again</A>");
            out.println("</BODY></HTML>");
        } else {
            out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
            out.println("<BODY>Your login and password are valid.<BR>");
            out.println("</BODY></HTML>");
            HttpSession session = req.getSession();
            session.setAttribute("logon.isDone", account);  // just a marker object

            try {
                String target = (String) session.getAttribute("login.target");
                if (target != null) {
                    res.sendRedirect(target);
                    return;
                }
            } catch (Exception ignored) {
            }

            //res.sendRedirect("/");
        }
    }

    protected boolean allowUser(String account, String password, String pin) {
        return true;
    }
}
