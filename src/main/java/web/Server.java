/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerDAO;
import dao.JdbiDaoFactory;
import dao.ProductDAO;
import dao.SaleDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.json.GsonModule;

/**
 *
 * @author caitlindyas
 */
public class Server extends Jooby {
    // private ProductDAO productDao = new ProductCollectionsDAO();
    // private CustomerDAO customerDAO = new CustomerCollectionsDAO();
    private CustomerDAO customerDAO = JdbiDaoFactory.getCustomerDAO();
    private ProductDAO productDAO = JdbiDaoFactory.getProductDAO();
    private SaleDAO saleDAO = JdbiDaoFactory.getSaleDAO();

    public static void main(String[] args) throws Exception {
        System.out.println("\nStarting Server.");
        new Server().start();
    }  
    
    public Server(){
        setServerOptions(new ServerOptions().setPort(8080));
        install(new GsonModule());
        mount(new ProductModule(productDAO));
        mount(new CustomerModule(customerDAO));
        mount(new SaleModule(saleDAO));
        mount(new StaticAssetModule());
    }
}
