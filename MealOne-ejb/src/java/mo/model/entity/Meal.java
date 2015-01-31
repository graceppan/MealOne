/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hasee
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Meal.FindMealExistence",query="select m from Meal m where m.dish.id=:id and m.mealDate=:orderDate and m.mealSize=:size "),
})
public class Meal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mealSize;
    private Double prize;
    @Temporal(TemporalType.DATE)
    private Date mealDate;
    @ManyToMany(mappedBy="meals",cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Orders> orders;
    @ManyToOne
    @JoinColumn(name="dish_id")
    private Dish dish;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealSize() {
        return mealSize;
    }

    public void setMealSize(String mealSize) {
        this.mealSize = mealSize;
    }

    public Double getPrize() {
        return prize;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
    
}
