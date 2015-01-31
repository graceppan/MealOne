/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import mo.ejb.DishService;
import mo.ejb.GuestService;
import mo.ejb.OrderService;
import mo.model.entity.Dish;
import mo.model.entity.Guest;
import mo.model.entity.Orders;

/**
 *
 * @author hasee
 */
@Named(value = "displayImageMB")
@SessionScoped
public class DisplayImageMB implements Serializable {

    @EJB
    private DishService dishService;
    private List<Dish> dishes;
    private Dish dish;
    @EJB
    private OrderService orderService;
    private Orders order;
    
    private String phone;
    @EJB
    private GuestService guestService;
    private Guest guest;
    public DisplayImageMB() {
        
    }
    
    public String displayDish(int Id){
        displayOneDish(Id);
        return "displayDish?faces-redirect=true";
    }
    
    public void displayOneDish(int id){
        System.out.println(id);
        dish=new Dish();
        dish = dishService.findDishById(id);
    }
    public void makeOrder(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        int id = (int) session.getAttribute("displayDishId");
        System.out.println(id);
        dish=new Dish();
        dish = dishService.findDishById(id);
        guest=new Guest();
        guest.setPhone(phone);
        guestService.create(guest);
//        orderService.create(order);
//        orderService.makeOrder(dish,guest);
    }
    public void getImage(){

    }
            
    public List<Dish> getDishes() {
        dishes = new ArrayList<Dish>();
        List<Dish> d = dishService.displayDishes();
        for (int i = 0; i < d.size(); i++) {
            dishes.add(d.get(i));
            //String url=getImage.sendGet(dishes.get(i).getImage().substring(5));
            //dishes.get(i).setImage(url);
            }
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
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

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GuestService getGuestService() {
        return guestService;
    }

    public void setGuestService(GuestService guestService) {
        this.guestService = guestService;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    
}
