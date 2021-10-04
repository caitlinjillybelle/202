/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;

/**
 *
 * @author caitlinjillybelle
 */
public interface ProductDAO {

    Collection<Product> getProducts();

    void removeProduct(Product product);

    void saveProduct(Product product);
    
    Collection<String> getCategories();
    
    Product searchById(String productId);
    
    Collection<Product> filterByCategory(String category);
    
}
