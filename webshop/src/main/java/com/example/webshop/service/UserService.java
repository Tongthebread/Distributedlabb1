package com.example.webshop.service;

import com.example.webshop.DTOS.UserDTO;
import com.example.webshop.Utils.PasswordUtils;
import com.example.webshop.database.UserDAO;
import com.example.webshop.model.Role;
import com.example.webshop.model.User;

import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO;
    public UserService() {
        this.userDAO = new UserDAO();
    }
    public Boolean RegisterUser(UserDTO user) {
        try{
            if (userDAO.getUserbyUserName(user.getUsername())!= null){
                return false;
            }
            String password = PasswordUtils.hashPassword(user.getPassword());
            user.setPassword(password);
            if (user.getRole()==null){
                user.setRole(Role.CUSTOMER);
            }
            userDAO.InsertUser(user);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public UserDTO authenticateUser(String username, String password) {
        try {
            UserDTO user = userDAO.getUserbyUserName(username);
            if (user != null) {
                if (PasswordUtils.checkPassword(password, user.getPassword())) {
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean authorizeUser(UserDTO user, Role requiredRole) {
        if (user == null || requiredRole == null) {
            return false;
        }
        // Kontrollera om användarens roll matchar den krävs rollen
        return user.getRole().equals(requiredRole);
    }
}
