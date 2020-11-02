package com.example.examen.servicesImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examen.modelo.User;
import com.example.examen.services.UsuarioService;
import com.example.examen.validacion.Utils;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private static List<User> users = new ArrayList<>();
	
	@Autowired
	public Utils utils;
	
	@Override
	public  List<User> getAllUsers(){
		return this.users;
	}
	@Override
	public  User getUserById(Long idUSer) {
		
		return this.users.stream().filter(item -> item.getId() == idUSer).findFirst().orElse(null);
		
	}
	@Override
	public  User addUser(User userNuevo) throws Exception {
		if(this.users.stream().filter(item ->{
			return item.getName().equalsIgnoreCase(userNuevo.getName()) &&
					item.getAge()==userNuevo.getAge() &&
					item.getPhone()==userNuevo.getPhone()&&
					item.getEmail().equals(userNuevo.getEmail());
		}).findFirst().orElse(null) != null)
			throw new Exception("Ya existe!!!!!!");
		User max = users.stream().max(Comparator.comparing(User::getId)).orElse(new User());
		if(max.getId()!=null) 
			userNuevo.setId(max.getId() + 1);
		else
			userNuevo.setId(1L);
		users.add(userNuevo);
		return userNuevo;
	}
	@Override
	public  User updateUser(User userModifica) {
		this.users.set(this.users.indexOf(this.getUserById(userModifica.getId())), userModifica);
		return userModifica;
	}
	@Override
	public  User deleteUser(Long  id) {
		return this.users.remove(this.users.indexOf(this.getUserById(id)));
	}
	
	@Override
	public  Boolean deleteAllUser() {
		return this.users.removeAll(this.users);
	}
	@Override
	public User generateUser() {
		User usuarioRm=new User();

		User max = users.stream().max(Comparator.comparing(User::getId)).orElse(new User());
		if(max.getId()!=null) 
			usuarioRm.setId(max.getId() + 1);
		else
			usuarioRm.setId(1L);
		usuarioRm.setName(utils.getFirstName().concat(" ").concat(utils.getSecondName()));
		usuarioRm.setAge(utils.getAge());
		usuarioRm.setEmail(utils.getEmail());
		usuarioRm.setPhone(utils.getPhone());
		usuarioRm.setRelocation(utils.getRelocationFlagByAge(usuarioRm));
		users.add(usuarioRm);
		return usuarioRm;
	}


}
