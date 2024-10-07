package com.example.webshop.service;

import com.example.webshop.DTOS.ProductDTO;
import com.example.webshop.database.ProductDAO;
import com.example.webshop.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    ProductDAO productDAO ;
    public ProductService() {
        this.productDAO = new ProductDAO();
    }
    public ArrayList<ProductDTO> getProducts() throws SQLException {
        return productDAO.getAllProducts();
    }
    public ProductDTO searchProduct(int id) throws SQLException {
        return productDAO.getProductById(id);
    }
    public void addProduct(ProductDTO product) throws SQLException {
        productDAO.insertProduct(product);
    }
    public void updateProduct(ProductDTO product) throws SQLException {
        productDAO.updateProduct(product);
    }
    public void deleteProduct(int id) throws SQLException {
        productDAO.deleteProduct(id);
    }
}
