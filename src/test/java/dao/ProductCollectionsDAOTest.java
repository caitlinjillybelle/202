/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author caitlinjillybelle
 */
public class ProductCollectionsDAOTest {
    // dao = new ProductCollectionsDAO();
    ProductDAO dao = JdbiDaoFactory.getProductDAO();
    
    
    
    @BeforeAll
    public static void initialise() {
    JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
        // dao = new ProductCollectionsDAO();
        // dao = JdbiDaoFactory.getProductDAO();
    }
    
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSaveProduct() {
    }

    @Test
    public void testGetProducts() {
    }

    @Test
    public void testRemoveProduct() {
    }

    @Test
    public void testGetCategories() {
    }    
}
