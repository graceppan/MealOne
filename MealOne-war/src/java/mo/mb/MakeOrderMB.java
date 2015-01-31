/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.mb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import mo.ejb.CustomerService;
import mo.ejb.DishService;
import mo.ejb.MealService;
import mo.ejb.OrderService;
import mo.model.entity.Customer;
import mo.model.entity.Dish;
import mo.model.entity.Meal;
import mo.model.entity.Orders;

/**
 *
 * @author hasee
 */
@Named(value = "makeOrderMB")
@RequestScoped
public class MakeOrderMB {

    @EJB
    private DishService dishService;
    private Dish dish;
    @EJB
    private MealService mealService;
    private Meal meal;
    private String size;
    private Date orderDate;
    private int qty;
    private Double price;
    @EJB
    private OrderService orderService;
    private Orders order;
    @EJB
    private CustomerService customerService;
    private Customer customer;

    public MakeOrderMB() {

    }

    public String category(int Id) {
        dish = dishService.findDishById(Id);
        if (dish.getCategory().equals("Pizza")) {
            return null;
        } else {
            return "Chinese dish";
        }
    }

    public String getOrderDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 5);
        if (orderDate == null) {
            return ("No date selected.");
        } else {
            if (orderDate.getTime() > c.getTimeInMillis()) {
                String message
                        = String.format("Can only make order in ahead of 5 days");
                return (message);
            } else if (orderDate.getTime() < Calendar.getInstance().getTimeInMillis()) {
                String message
                        = String.format("This order date is not available now");
                return (message);
            }
            String message
                    = String.format("You choose '%s'.",
                            orderDate);
            return (message);
        }
    }

    public String makeOrder(int Id) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 5);
        List<Orders> orders = orderService.findOrdersByDate(orderDate);
        int orderQty = 0;
        for (int i = 0; i < orders.size(); i++) {
            orderQty += orders.get(i).getMeals().size();
        }
        if (orderDate.getTime() > c.getTimeInMillis() || orderDate.getTime() < Calendar.getInstance().getTimeInMillis()||orderDate == null) {
            FacesContext.getCurrentInstance().addMessage("order", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid order date chosen", null));
            return null;
        } else if (orderQty == 5) {
            FacesContext.getCurrentInstance().addMessage("order", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Order is full for this day", null));
            return null;
        } else if (orderQty + qty > 5) {
            FacesContext.getCurrentInstance().addMessage("order", new FacesMessage(FacesMessage.SEVERITY_ERROR, "The Qty chosen is over order limitation of this day ", null));
            return null;
        }
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        if (size == null) {
            size = "normal";
            price = 9d;
        } else if (size.equals("Large")) {
            price = 14d;
        } else {
            price = 18d;
        }
        if (mealService.findMealExistence(Id, orderDate, size) == null) {
            meal = new Meal();
            meal.setDish(dishService.findDishById(Id));
            meal.setMealDate(orderDate);
            meal.setMealSize(size);
            meal.setPrize(price);
            mealService.create(meal);
        }
        session.setAttribute("mealID", mealService.findMealExistence(Id, orderDate, size).getId());
        CheckSessionMB check=new CheckSessionMB();
        if(check.getSession()==null){
            return "payment?faces-redirect=true";
        }else{
            int userId=(int) session.getAttribute("loggedUserId");
            if(customerService.findCustomerById(userId)!=null){
                order=new Orders();
                order.setOrderDate(orderDate);
                order.setUser(customerService.findCustomerById(userId));
                order.setStatus("placed");
                List<Meal> meals=new ArrayList<Meal>();
                for(int i=0;i<qty;i++){
                    meals.add(mealService.findMealExistence(Id, orderDate, size));
                }
                order.setMeals(meals);
                orderService.create(order);
                return "homePage?faces-redirect=true";
            }else
                return null;
        }
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

    public MealService getMealService() {
        return mealService;
    }

    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
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

}
