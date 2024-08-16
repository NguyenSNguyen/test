/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nsnguyen.data.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nsnguyen.data.dao.ProductDao;
import nsnguyen.data.driver.MySQLDriver;
import nsnguyen.data.model.Product;

/**
 *
 * @author nsngu
 */
public class ProductImpl implements ProductDao {
    Connection con = MySQLDriver.getConnection();
    @Override
    public List<Product> findAll(){
        List<Product> listProduct = new ArrayList<>();
        String sql = "select * from products";
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int id_category = rs.getInt("id_category");
                String name = rs.getString("name");
                String image = rs.getString("image");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                listProduct.add(new Product(id, id_category, name, image, price, quantity, status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }
    @Override
    public Product findProduct(int id_product){
      String sql = "select * from products where id="+id_product;
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next())return new Product(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ProductImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      }

    @Override
    public void insertProduct(String name, String image, String price, String quantity, String status, String id_category) {
        String sql = "INSERT INTO products (name, image, price, quantity, status, id_category) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setString(1, name);
            sttm.setString(2, image);
            sttm.setDouble(3, Double.parseDouble(price));
            sttm.setInt(4, Integer.parseInt(quantity));
            sttm.setBoolean(5, Boolean.parseBoolean(status));
            sttm.setString(6, id_category);
            sttm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteProduct(int id_product) {
        String sql = "DELETE FROM products WHERE id = ?";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, id_product);
            sttm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ?, status = ?, image = ?, id_category = ? WHERE id = ?";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setString(1, product.getName());
            sttm.setDouble(2, product.getPrice());
            sttm.setInt(3, product.getQuantity());
            sttm.setBoolean(4, product.isStatus());
            sttm.setString(5, product.getImage());
            sttm.setInt(7, product.getId());
            sttm.setInt(6, product.getId_category());
            sttm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }   

}
