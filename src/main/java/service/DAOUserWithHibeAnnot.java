package service;

import java.util.List;

import entity.Users;

public interface DAOUserWithHibeAnnot {
	
	Users getUser(int id) ;
	List <Users> getAllUsers();
	void addUser(Users user) ;
	void updateUser(Users user);
	void deleteUser(int id);

}
