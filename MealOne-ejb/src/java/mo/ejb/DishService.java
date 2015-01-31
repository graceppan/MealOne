/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mo.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mo.boundary.AbstractFacade;
import mo.model.entity.Dish;

/**
 *
 * @author hasee
 */
@Stateless
public class DishService extends AbstractFacade<Dish> {

    @PersistenceContext(name = "MealOne-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DishService() {
        super(Dish.class);
    }

    public List<Dish> displayDishes() {
        TypedQuery<Dish> query = em.createNamedQuery("Dish.FindAllDishes", Dish.class);
        List<Dish> dishes = query.getResultList();
        return dishes;
    }
    
    public Dish findDishById(int id){
        try{
            TypedQuery<Dish> query = em.createNamedQuery("Dish.FindDishById", Dish.class).setParameter("id", id);
            Dish d = query.getSingleResult();
            return d;
        }catch(Exception e){
            return null;
        }
    }
}
