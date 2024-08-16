/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nsnguyen.data.dao;

import nsnguyen.data.impl.CategoryImpl;
import nsnguyen.data.impl.InvoiceImpl;
import nsnguyen.data.impl.ProductImpl;
import nsnguyen.data.impl.UserImpl;



/**
 *
 * @author nsngu
 */
public class Database {
    public  static CategoryDao getCategoryDao(){
        return new CategoryImpl();
    }
    public  static ProductDao getProductDao(){
        return new ProductImpl();
    }
    public  static UserDao getUserDao(){
        return new UserImpl();
    }

    public static InvoiceDao getInvoiceDao() {
        return new InvoiceImpl();
    }

}
