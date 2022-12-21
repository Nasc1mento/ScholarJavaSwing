package org.social.oop.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.social.oop.exception.EmailFieldNotFilledException;
import org.social.oop.exception.NameFieldNotFilledException;
import org.social.oop.exception.PasswordFieldNotFilledException;
import org.social.oop.exception.PhoneFieldNotFilledException;
import org.social.oop.model.User;


public class UserDAO implements IUserPersistence{
	
	private IConnectionDB databaseMySQL;
	private static UserDAO instance;
	
	private UserDAO() {
		this.databaseMySQL = new DatabaseMySQL();
	}
	public static UserDAO getInstance() {
		if (instance == null) 
			return new UserDAO();
		else 
			return instance;
	}
	@Override
	public void createUser(User user) throws NameFieldNotFilledException,EmailFieldNotFilledException, PhoneFieldNotFilledException,PasswordFieldNotFilledException{
		if (user.getName() == null || user.getName().equalsIgnoreCase("") || user.getName().equalsIgnoreCase("\n")) 
			throw new NameFieldNotFilledException("O nome é obrigatório");
		else if (user.getEmail() == null || user.getEmail().equalsIgnoreCase("") || user.getEmail().equalsIgnoreCase("\n"))
			throw new EmailFieldNotFilledException("O email é obrigatório");
		else if (user.getPhone() == null || user.getPhone().equalsIgnoreCase("") || user.getPhone().equalsIgnoreCase("\n"))
			throw new PhoneFieldNotFilledException("O telefone é obrigatório");
		else if (user.getPassword() == null || user.getPassword().equalsIgnoreCase("") || user.getPassword().equalsIgnoreCase("\n"))
			throw new PasswordFieldNotFilledException("A senha é obrigatória");
		else {
			try {
			PreparedStatement preparedStatement = this.databaseMySQL.getConnection().prepareStatement("INSERT INTO OS_USERS VALUES (?, ?, ?, ?, ?);");
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPhone());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.execute();
			
			}catch(SQLException exception){
			exception.printStackTrace();
			}
		}
	}
		
	@Override
	public void removeUser(User user) {
		
	}
	@Override
	public void updateUser(User user) {
		
	}
	@Override
	public User locateUser(User user) {
		return user;
	}
	@Override
	public boolean authUser(User user) {
		try {
			PreparedStatement preparedStatement = this.databaseMySQL.getConnection().
					prepareStatement("SELECT * FROM OS_USERS WHERE USR_EMAIL = ? AND USR_PASSWORD = ?;");
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			ResultSet resultset = preparedStatement.executeQuery();
			return resultset.next();
		}catch(SQLException exception) {
			exception.printStackTrace();
		}
		return false;
	}
	
	
	
	
}
