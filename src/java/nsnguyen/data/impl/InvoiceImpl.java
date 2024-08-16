package nsnguyen.data.impl;

import nsnguyen.data.dao.InvoiceDao;
import nsnguyen.data.model.Invoice;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nsnguyen.data.driver.MySQLDriver;

public class InvoiceImpl implements InvoiceDao {
    Connection con = MySQLDriver.getConnection();

    @Override
    public List<Invoice> findAll() {
        List<Invoice> listInvoice = new ArrayList<>();
        String sql = "SELECT * FROM invoice";
        PreparedStatement sttm;
        try {
            sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                Date invoice_date = rs.getDate("invoice_date");
                double total_amount = rs.getDouble("total_amount");
                listInvoice.add(new Invoice(id, user_id, user_name, invoice_date, total_amount));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listInvoice;
    }

    @Override
    public void insertInvoice(int user_id, java.util.Date invoice_date, double total_amount, boolean status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
