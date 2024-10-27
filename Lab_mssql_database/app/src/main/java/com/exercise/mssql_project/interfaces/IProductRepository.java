package com.exercise.mssql_project.interfaces;

import com.exercise.mssql_project.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProductRepository {
    ArrayList<Product> getProducts() throws SQLException;
    void createProduct(String name, double price) throws SQLException;
    void deleteProduct(int id) throws SQLException;
    ArrayList<Product> searchProduct(String name) throws SQLException;
    void updateProduct(int id, String name, double price) throws SQLException;
}
