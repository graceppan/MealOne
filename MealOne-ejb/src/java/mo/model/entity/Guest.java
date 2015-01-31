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
import javax.persistence.OneToMany;

/**
 *
 * @author hasee
 */
@Entity
public class Guest extends Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
    
}
