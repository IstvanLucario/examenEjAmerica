package com.example.examen.controlador;

import java.util.List;

import com.example.examen.modelo.User;

public interface ControladorUsuario {
	
	List<User> getAllUsers();
	User getUserById(Long idUser);
	User addUser(User user) throws Exception;
	User updateUser(User user);
	User deleteUser(Long idUser);

}
