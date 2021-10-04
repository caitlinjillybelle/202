/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerDAO;
import domain.Customer;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author caitlindyas
 * 
 * Create a new module class named CustomerModule for managing customer accounts. 
 * Use the ProductModule as a guide. Don’t forget to inject the DAO into the constructor.
 */
public class CustomerModule extends Jooby {
    
    public CustomerModule(CustomerDAO customerDao){
        // Get Customers by a username:
        get("/api/customers/{username}", ctx -> {
            String username = ctx.path("username").value();
            Customer customer = customerDao.getCustomer(username);
            if (customer != null){
                return customer;
            } else {
                return ctx.send(StatusCode.NOT_FOUND);
            }
        });      
        
        post("/api/register", ctx -> {
            Customer customer = ctx.body().to(Customer.class);
            customerDao.saveCustomer(customer);
            return ctx.send(StatusCode.CREATED);
        });
    }
    
}
