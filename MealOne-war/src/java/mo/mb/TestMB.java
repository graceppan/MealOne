/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mo.mb;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import mo.ejb.AdministratorService;
import mo.ejb.CustomerService;
import mo.ejb.GuestService;
import mo.model.entity.Administrator;
import mo.model.entity.Customer;
import mo.model.entity.Guest;

/**
 *
 * @author hasee
 */
@Named(value = "testMB")
@RequestScoped
public class TestMB {

    @EJB
    private CustomerService customerService;
    @EJB
    private AdministratorService administratorService;
    @EJB
    private GuestService guestService;

    public void testRun() {
//        Administrator admin = new Administrator();
//        admin.setName("Grace");
//        admin.setEmail("grace.pan92@gmail.com");
//        admin.setPhone("2223332345");
//        admin.setPassword("12345");
//        administratorService.create(admin);
//          Customer c=new Customer();
//          c.setName("Emma");
//          c.setEmail("emma@gmail.com");
//          c.setPassword("12345");
//          c.setPhone("3193889288");
//          c.setTimes(0);
//          customerService.create(c);
          Guest g=new Guest();
          g.setName("Michael");
          g.setEmail("michael@gmail.com");
          g.setPhone("7398990984");
          guestService.create(g);
        
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public void setAdministratorService(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    public GuestService getGuestService() {
        return guestService;
    }

    public void setGuestService(GuestService guestService) {
        this.guestService = guestService;
    }
    
}
