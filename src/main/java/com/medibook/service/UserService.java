package com.medibook.service;

import com.medibook.dao.UserDAO;
import com.medibook.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User u) {
        User existing = userDAO.getUserByEmail(u.getEmail());
        if (existing != null) {
            return false;
        }
        return userDAO.registerUser(u);
    }
    
    public User loginUser(String email, String password) {
		User user = userDAO.getUserByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
    
}