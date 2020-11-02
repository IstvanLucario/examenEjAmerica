package com.example.examen.services;

import java.util.List;

import com.example.examen.modelo.User;

public interface UsuarioService {
	
	List<User> getAllUsers();
	
    User getUserById(Long idUser);
	
	User addUser(User userNuevo) throws Exception;
	
	User updateUser(User userModifica);
	
    User deleteUser(Long  id) ;

	User generateUser();

	Boolean deleteAllUser();
}
