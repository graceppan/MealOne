/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mo.boundary.AbstractFacade;
import mo.model.entity.Dish;
import mo.model.entity.Guest;

/**
 *
 * @author hasee
 */
@Stateless
public class GuestService extends AbstractFacade<Guest>{

        @PersistenceContext(name = "MealOne-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public GuestService() {
        super(Guest.class);
    }
}
