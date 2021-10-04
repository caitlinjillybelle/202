/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.JdbiDaoFactory;
import dao.ProductDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;

/**
 *
 * @author caitlindyas
 */
public class ProductModule extends Jooby {
    
    ProductModule(ProductDAO productDAO){

        get("/api/products", ctx -> productDAO.getProducts());
        get("/api/products/{id}", ctx -> {
            String id = ctx.path("id").value();
            return productDAO.searchById(id);
        });
        get("/api/categories", ctx -> productDAO.getCategories());
        get("/api/categories/{cat}", ctx -> {
            String cat = ctx.path("cat").value();
            return productDAO.filterByCategory(cat);
        });
    }
    
}
