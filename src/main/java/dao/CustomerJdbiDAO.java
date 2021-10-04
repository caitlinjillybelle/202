/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Customer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author caitlindyas
 */
public interface CustomerJdbiDAO extends CustomerDAO {
    @Override
    @SqlUpdate("INSERT INTO CUSTOMER ( USERNAME, FIRST_NAME, SURNAME, PASSWORD, EMAIL_ADDRESS, SHIPPING_ADDRESS) VALUES ( :username, :firstName, :surname, :password, :emailAddress, :shippingAddress)")
    public void saveCustomer(@BindBean Customer customer);
    
    @Override
    @SqlQuery("SELECT * FROM CUSTOMER WHERE USERNAME = :username")
    @RegisterBeanMapper(Customer.class)
    public Customer getCustomer(@Bind("username") String username);
    
    @Override
    @SqlQuery("SELECT EXISTS (SELECT * FROM CUSTOMER WHERE USERNAME = :username AND PASSWORD = :password")
    @RegisterBeanMapper(Customer.class)
    public Boolean validateCredentials(@Bind("username") String username, @Bind("password") String password);
}
