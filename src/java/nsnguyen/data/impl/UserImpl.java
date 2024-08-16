/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nsnguyen.data.impl;

import java.sql.Connection;
import nsnguyen.data.dao.UserDao;
import nsnguyen.data.driver.MySQLDriver;
import nsnguyen.data.model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import nsnguyen.data.utils.API;

/**
 *
 * @author nsngu
 */
public class UserImpl implements UserDao {
    Connection con = MySQLDriver.getConnection();
    @Override
    public User findUser(String emailphone, String password) {
        String sql;
        if(emailphone.contains("@")) sql="select * from users where email = '" + emailphone + "' and password= '" + API.getMd5(password) + "'";
        else sql="select * from users where phone = '" + emailphone + "' and password= '" + API.getMd5(password) + "'";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()) return new User(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User findUser(String emailphone) {
        String sql;
        if(emailphone.contains("@")) sql="select * from users where email = '" + emailphone + "'";
        else sql="select * from users where phone = '" + emailphone + "'";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if(rs.next()) return new User(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insertUser(String name, String email, String phone, String password) {
       String sql="insert into users(name, email, phone, password, role) values('"+name+"','"+email;
       sql=sql +"','"+phone+"','"+password+"','')";
       try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.execute();
        } catch (SQLException e) {
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, phone = ? WHERE id = ?";
        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setString(1, user.getName());
            sttm.setString(2, user.getEmail());
            sttm.setString(3, user.getPhone());
            sttm.setInt(4, user.getId());
            sttm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
}
