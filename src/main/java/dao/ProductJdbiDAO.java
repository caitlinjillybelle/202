/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author caitlinjillybelle
 */
public interface ProductJdbiDAO extends ProductDAO {

    @Override
    @SqlUpdate("INSERT INTO PRODUCT (PRODUCT_ID, NAME, DESCRIPTION, CATEGORY, LIST_PRICE, QUANTITY_IN_STOCK) VALUES (:productId, :name, :description, :category, :listPrice, :quantityInStock)")
    public void saveProduct(@BindBean Product product);

    @Override
    @SqlUpdate("DELETE FROM PRODUCT WHERE PRODUCT_ID = :productId")
    public void removeProduct(@BindBean Product product);

    @Override
    @SqlQuery("SELECT * FROM PRODUCT ORDER BY PRODUCT_ID")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> getProducts();
    
    @Override
    @SqlQuery("SELECT DISTINCT CATEGORY FROM PRODUCT ORDER BY CATEGORY")
    public Collection<String> getCategories();

    @Override
    @SqlQuery("SELECT * FROM PRODUCT WHERE CATEGORY = :category")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> filterByCategory(@Bind("category") String category);
    
    @Override
    @SqlQuery("SELECT * FROM PRODUCT WHERE PRODUCT_ID = :productId")
    @RegisterBeanMapper(Product.class)
    public Product searchById(@Bind("productId") String id);
    
}
