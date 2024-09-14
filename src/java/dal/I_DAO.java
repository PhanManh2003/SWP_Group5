/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;

/**
 *
 * @author ADMIN
 * @param <T>
 */
public interface I_DAO<T> {
    
    public List<T> findAll();
    
    public T findById(T t);
    
    public boolean update(T t);
    
    public boolean delete(T t);
    
    public int insert(T t);
}
