/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.mb;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mo.ejb.AdministratorService;
import mo.ejb.CustomerService;
import mo.model.entity.Administrator;
import mo.model.entity.Customer;
import mo.model.entity.Users;

/**
 *
 * @author hasee
 */
@Named(value = "loginMB")
@RequestScoped
public class LoginMB {

    private String email;
    private String password;
    @EJB
    private CustomerService customerService;
    private Customer customer;
    @EJB
    private AdministratorService adminService;
    private Administrator admin;

    public LoginMB() {
    }

    public String setUserSessionData(Users user) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
//        session.setAttribute("loggedUserId", user.getId());
//        session.setAttribute("loggedUserName", user.getName());
        session.setAttribute("loggedUserId", user.getId());
        System.out.println(user.getEmail());
        return "uploadFile?faces-redirect=true";
    }

    public String login() {
        if (customerService.findCustomer(email, password) != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext ec = context.getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(true);
            session.setAttribute("loggedUserId", customerService.findCustomer(email, password).getId());
            return "homePage?faces-redirect=true";
        } else if (adminService.findAdministrator(email, password) != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext ec = context.getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(true);
            session.setAttribute("loggedUserId", adminService.findAdministrator(email, password).getId());
            return "addDish?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage("login", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid user info ", null));
            return null;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AdministratorService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdministratorService adminService) {
        this.adminService = adminService;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

}
