package com.example.webshop.model;

import com.example.webshop.DTOS.ProductDTO;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void addItem(ProductDTO product, int quantity) {
        int productId = product.getId();
        if (items.containsKey(productId)) {
            // Om produkten redan finns i varukorgen, uppdatera kvantiteten
            CartItem existingItem = items.get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // Annars, skapa en ny CartItem och lägg till den
            CartItem newItem = new CartItem(product, quantity);
            items.put(productId, newItem);
        }
    }

    // Ta bort en produkt från varukorgen
    public void removeItem(int productId) {
        items.remove(productId);
    }

    // Uppdatera kvantiteten för en produkt
    public void updateItem(int productId, int quantity) {
        if (items.containsKey(productId)) {
            if (quantity > 0) {
                items.get(productId).setQuantity(quantity);
            } else {
                // Om kvantiteten är 0 eller mindre, ta bort produkten
                removeItem(productId);
            }
        }
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }
    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : items.values()) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}
