/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mo.boundary.AbstractFacade;
import mo.model.entity.Dish;
import mo.model.entity.Guest;
import mo.model.entity.Orders;

/**
 *
 * @author hasee
 */
@Stateless
@LocalBean
public class OrderService extends AbstractFacade<Orders> {

    @PersistenceContext(name = "MealOne-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderService() {
        super(Orders.class);
    }

    public List<Orders> findOrdersByDate(Date orderDate) {
        TypedQuery<Orders> query = em.createNamedQuery("Orders.FindOrdersByDate", Orders.class).setParameter("orderDate", orderDate);
        List<Orders> orders = query.getResultList();
        return orders;
    }
}
