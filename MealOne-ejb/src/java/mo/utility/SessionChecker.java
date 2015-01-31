/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.utility;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hasee
 */
public class SessionChecker {

    private boolean userPrincipal;

    public SessionChecker() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        if (session.getAttribute("loggedUserId") != null) {
            this.userPrincipal = true;
        } else if (session.getAttribute("loggedUserId") == null) {
            this.userPrincipal = false;
        }
    }

    public boolean isUserPrincipal() {
        return userPrincipal;
    }

    public void setUserPrincipal(boolean userPrincipal) {
        this.userPrincipal = userPrincipal;
    }



}
