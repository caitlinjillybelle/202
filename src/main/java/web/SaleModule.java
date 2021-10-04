/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.SaleDAO;
import domain.Sale;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author caitlindyas
 */
public class SaleModule extends Jooby {
    
    public SaleModule(SaleDAO saleDAO){

        post("/api/sales", ctx -> {
            Sale sale = ctx.body().to(Sale.class);
            System.out.println(sale);
            saleDAO.save(sale);
            return ctx.send(StatusCode.CREATED);
        });
        
    }    
}
