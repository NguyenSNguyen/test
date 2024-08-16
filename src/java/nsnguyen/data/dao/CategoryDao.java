/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nsnguyen.data.dao;

import java.util.List;
import nsnguyen.data.model.Category;

/**
 *
 * @author nsngu
 */
public interface CategoryDao {
    public List<Category> findAll();
    public boolean insert(Category category);
    public boolean update(Category category);
    public boolean dalete(int id);
    public Category find(int id);
}
