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
import mo.model.entity.Administrator;
import mo.model.entity.Customer;

/**
 *
 * @author hasee
 */
@Stateless
public class AdministratorService extends AbstractFacade<Administrator>{

    @PersistenceContext(name = "MealOne-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public AdministratorService() {
        super(Administrator.class);
    }
    
        public Administrator findAdministratorById(int id){
        try{
            TypedQuery<Administrator> query = em.createNamedQuery("Administrator.FindAdministratorById", Administrator.class).setParameter("id", id);
            Administrator a = query.getSingleResult();
            return a;
        }catch(Exception e){
            return null;
        }
    }
    
    public Administrator findAdministrator(String email, String password){
        try{
            TypedQuery<Administrator> query = em.createNamedQuery("Administrator.FindAdministrator", Administrator.class).setParameter("email", email).setParameter("password", password);
            Administrator a = query.getSingleResult();
            return a;
        }catch(Exception e){
            return null;
        }
    }
}
