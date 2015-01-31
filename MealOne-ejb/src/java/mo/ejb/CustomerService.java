/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo.ejb;

import mo.boundary.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mo.model.entity.Customer;
import mo.model.entity.Dish;

/**
 *
 * @author hasee
 */
@Stateless
public class CustomerService extends AbstractFacade<Customer>{

    @PersistenceContext(name = "MealOne-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public CustomerService() {
        super(Customer.class);
    }
    
    public Customer findCustomerById(int id){
        try{
            TypedQuery<Customer> query = em.createNamedQuery("Customer.FindCustomerById", Customer.class).setParameter("id", id);
            Customer c = query.getSingleResult();
            return c;
        }catch(Exception e){
            return null;
        }
    }
    
    public Customer findCustomer(String email, String password){
        try{
            TypedQuery<Customer> query = em.createNamedQuery("Customer.FindCustomer", Customer.class).setParameter("email", email).setParameter("password", password);
            Customer c = query.getSingleResult();
            return c;
        }catch(Exception e){
            return null;
        }
    }
}
