package nsnguyen.data.dao;

import java.util.Date;
import java.util.List;
import nsnguyen.data.model.Invoice;

public interface InvoiceDao {
    public List<Invoice> findAll();
    public void insertInvoice(int user_id, Date invoice_date, double total_amount, boolean status);
}
