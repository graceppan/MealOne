/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo.ejb;

import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mo.boundary.AbstractFacade;
import mo.model.entity.Dish;
import mo.model.entity.Guest;
import mo.model.entity.Meal;

/**
 *
 * @author hasee
 */
@Stateless
public class MealService extends AbstractFacade<Meal>{

        @PersistenceContext(name = "MealOne-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public MealService() {
        super(Meal.class);
    }
    
    public Meal findMealExistence(int dishId, Date orderDate, String size){
        try{
            TypedQuery<Meal> query = em.createNamedQuery("Meal.FindMealExistence", Meal.class).setParameter("id", dishId).setParameter("orderDate", orderDate).setParameter("size", size);
            Meal m = query.getSingleResult();
            return m;
        }catch(Exception e){
            return null;
        }
    }
}
