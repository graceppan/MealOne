/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.mb;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import mo.utility.SessionChecker;

/**
 *
 * @author hasee
 */
@Named(value = "checkSessionMB")
@RequestScoped
public class CheckSessionMB {

    private String session;
    private SessionChecker sc;
    private String userSession;

    public CheckSessionMB() {
        sc = new SessionChecker();
        if (sc.isUserPrincipal() == true) {
            this.session="user logged";
        } else {
            this.session=null;
        }
    }

    public String getUserSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession s = (HttpSession) ec.getSession(true);
        System.out.println("**************"+s.getAttribute("loggedUserId"));
        userSession=(String) s.getAttribute("loggedUserId");
        return "index?faces-redirect=true";
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public SessionChecker getSc() {
        return sc;
    }

    public void setSc(SessionChecker sc) {
        this.sc = sc;
    }

//    public String getUserSession() {
//        return userSession;
//    }
//    
    public void setUserSession(String userSession) {
        this.userSession = userSession;
    }

}
