package com.example.webshop.service;

import com.example.webshop.database.ProductDAO;
import com.example.webshop.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    ProductDAO productDAO ;
    public ProductService() {
        this.productDAO = new ProductDAO();
    }
    public ArrayList<Product> getProducts() throws SQLException {
        return productDAO.getAllProducts();
    }
    public Product searchProduct(int id) throws SQLException {
        return productDAO.getProductById(id);
    }
    public void addProduct(Product product) throws SQLException {
        productDAO.insertProduct(product);
    }
    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }
    public void deleteProduct(int id) throws SQLException {
        productDAO.deleteProduct(id);
    }
}
