/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Sale;
import domain.SaleItem;
import java.time.LocalDateTime;
import org.jdbi.v3.sqlobject.SqlObject;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

/**
 *
 * @author caitlindyas
 */
public interface SaleJdbiDAO extends SaleDAO, SqlObject {

    @SqlUpdate("INSERT INTO SALE (DATE, STATUS, CUSTOMER_ID) VALUES (:date, :status, customer.customerID)")
    @GetGeneratedKeys
    Integer insertSale(@BindBean Sale sale);

    @SqlUpdate("INSERT INTO SALE_ITEM (QUANTITY_PURCHASED, SALE_PRICE, SALE_ID, PRODUCT_ID) VALUES (:quantityPurchased, :salePrice, :sale.saleId, :product.productId)")
    void insertSaleItem(@BindBean SaleItem item, @Bind("saleId") Integer saleId);

    @SqlUpdate("UPDATE PRODUCT SET QUANTITY_PURCHASED - :quantityPurchased WHERE PRODUCT_ID =:product.productId")
    void updateStockLevel(@BindBean SaleItem item);

    @Transaction
    default void save(Sale sale) {
        // save current date
        sale.setDate(LocalDateTime.now());

        // set sale status
        sale.setStatus("NEW ORDER");

        // call the insertSale method, and get the generated sale ID.
        Integer saleId = insertSale(sale);

        // loop through the sale's items.
        for (SaleItem item : sale.getItems()) {
            insertSaleItem(item, saleId);
            updateStockLevel(item);
        }
    }
}