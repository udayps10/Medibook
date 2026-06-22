package com.medibook.service;
import com.medibook.util.PasswordUtil;


import com.medibook.dao.UserDAO;
import com.medibook.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User u) {
        User existing = userDAO.getUserByEmail(u.getEmail());
        if (existing != null) {
            return false;
        }
        String hashed = PasswordUtil.hashPassword(u.getPassword());
        u.setPassword(hashed);
        
        return userDAO.registerUser(u);
    }
    
    public User loginUser(String email, String password) {
		User user = userDAO.getUserByEmail(email);
		if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) 
			return user;
		
		return null;
	}
 
    
}