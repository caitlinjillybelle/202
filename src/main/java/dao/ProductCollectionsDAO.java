/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author caitlinjillybelle
 */
public class ProductCollectionsDAO implements ProductDAO {
    
    private static Collection<Product> products = new ArrayList<>();
    private static Collection<String> categories = new HashSet<String>();
    // Map to associate products with their IDs
    private static Map<String, Product> productIDMap = new HashMap<>();
    private static Multimap<String, Product> categoryMap = HashMultimap.create();
    
    @Override
    public void saveProduct(Product product){
        products.add(product);
        categories.add(product.getCategory());
        productIDMap.put(product.getProductId(), product);  
    }

    @Override
    public Collection<Product> getProducts(){
        return products;
    }
    @Override
    public void removeProduct(Product product){
        products.remove(product);
    }
    
    @Override
    public Collection<String> getCategories(){
        return categories;
    }
    
    @Override
    public Product searchById(String productId){

        boolean doesExist = productIDMap.containsKey(productId);        
        if (!doesExist){
            return (productIDMap.get(productId));
        }else {
            return null;
        }
    }
    
    @Override
    public Collection<Product> filterByCategory(String category){
        for (Product product : products ){
            categoryMap.put(product.getCategory(), product);
        }
        Collection<Product> elements = categoryMap.get(category);
        return elements;
    }
}
