/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author hasee
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Customer.FindCustomerById",query="select c from Customer c where c.id=:id"),
    @NamedQuery(name="Customer.FindCustomer",query="select c from Customer c where c.email=:email and c.password=:password"),
})
public class Customer extends Users implements Serializable {
    private static final long serialVersionUID = 1L;

    private String password;
    private int times;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Orders> orders;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}
