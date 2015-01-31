/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.mb;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import mo.ejb.DishService;
import mo.model.entity.Dish;
import mo.model.entity.Users;

/**
 *
 * @author hasee
 */
@Named(value = "dishMB")
@RequestScoped
public class DishMB implements Serializable {

    @EJB
    private DishService dishService;
    private Dish dish;
    private static Dish _dish;
    private String category;
    private Part part;
    private String statusMessage;

    public DishMB() {
        this.dish = new Dish();
        _dish = this.dish;
    }

    public static Dish get_Dish() {
        return _dish;
    }

    public void createDish() {
        dishService.create(_dish);
    }

    public void setUserSessionData(Users user) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
    }

    public DishService getDishService() {
        return dishService;
    }

    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
