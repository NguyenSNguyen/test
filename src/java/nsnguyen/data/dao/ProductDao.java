/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nsnguyen.data.dao;

import java.util.List;
import nsnguyen.data.model.Product;

/**
 *
 * @author nsngu
 */
public interface ProductDao {
    public List<Product> findAll();
    public Product findProduct(int id_product);
    public void insertProduct(String name, String image, String price, String quantity, String status, String id_category);
    public void deleteProduct(int id_product);
    public void updateProduct(Product product);
}
