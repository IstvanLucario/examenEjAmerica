package com.example.examen.controladorImpl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.examen.modelo.User;
import com.example.examen.services.UsuarioService;
import com.example.examen.validacion.Utils;

@Controller
@RequestMapping("/crud")
public class ControladorUsuarioWeb {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	Utils utils;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listausuarios(ModelMap mp) {
		int n1 = (int) Math.floor(Math.random() * 10);
		int n2 = (int) Math.floor(Math.random() * 10);
		int suma = n1 + n2;
		String sumS = n1 + "+" + n2 + "=" + suma;
		System.out.println("sum:" + sumS);
		List<User> usr = usuarioService.getAllUsers();
		User u = new User();
		u.setName(sumS);
		if (usr.isEmpty()) {
			return "crud/vacio";
		} else {
			mp.put("usuarios", usuarioService.getAllUsers());
			mp.put("suma", u);
			return "crud/lista";
		}
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(ModelMap mp) {
		mp.put("usuario", new User());
		return "crud/nuevo";
	}

	@RequestMapping(value = "/nuevoRamdom", method = RequestMethod.GET)
	public String nuevoRamdom(ModelMap mp) {
		usuarioService.generateUser();
		List<User> usr = usuarioService.getAllUsers();
		if (usr.isEmpty()) {
			return "crud/vacio";
		} else {
			int n1 = (int) Math.floor(Math.random() * 10);
			int n2 = (int) Math.floor(Math.random() * 10);
			int suma = n1 + n2;
			String sumS = n1 + "+" + n2 + "=" + suma;
			System.out.println("sum:" + sumS);
			User u = new User();
			u.setName(sumS);
			mp.put("suma", u);
			mp.put("usuarios", usuarioService.getAllUsers());
			return "crud/lista";
		}
	}

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String crear(@Valid User user, BindingResult bindingResult, ModelMap mp) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> le = bindingResult.getAllErrors();
			StringBuilder sb = new StringBuilder();
			for (ObjectError objectError : le) {
				sb.append(objectError.getDefaultMessage());
			}
			user.setError(sb.toString());
			mp.put("usuario", user);
			return "/crud/nuevo";
		} else {
			try {
				String nameValid = utils.validateName(user);

				if (nameValid != null) {
					user.setError(nameValid);
					mp.put("usuario", user);
					return "/crud/nuevo";
				}

				utils.validateRangeAge(user);
				if (user.getError() != null) {
					mp.put("usuario", user);
					return "crud/nuevo";
				}
				user.setRelocation(utils.getRelocationFlagByAge(user));
				usuarioService.addUser(user);
				mp.put("usuario", user);
				return "crud/creado";
			} catch (Exception e) {
				e.printStackTrace();
				mp.put("usuario", user);
				return "crud/existe";
			}

		}
	}

	@RequestMapping(value = "/creado", method = RequestMethod.POST)
	public String creado(@RequestParam("usuario") User user) {
		return "/crud/creado";
	}

	@RequestMapping(value = "/borrar/{id}", method = RequestMethod.GET)
	public String borrar(@PathVariable("id") long id, ModelMap mp) {

		User userMod = usuarioService.getUserById(id);
		if (userMod != null)
			usuarioService.deleteUser(id);
		List<User> usr = usuarioService.getAllUsers();
		if (usr.isEmpty()) {
			return "crud/vacio";
		} else {
			int n1 = (int) Math.floor(Math.random() * 10);
			int n2 = (int) Math.floor(Math.random() * 10);
			int suma = n1 + n2;
			String sumS = n1 + "+" + n2 + "=" + suma;
			System.out.println("sum:" + sumS);
			User u = new User();
			u.setName(sumS);
			mp.put("suma", u);
			mp.put("usuarios", usuarioService.getAllUsers());
			return "crud/lista";
		}

	}

	@RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
	public String editar(@PathVariable("id") long id, ModelMap mp) {

		User user = usuarioService.getUserById(id);
		user.setPhoneOut(utils.printPhone(user.getPhone()));
		mp.put("usuario", user);
		return "crud/editar";
	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	public String actualizar(@Valid User user, BindingResult bindingResult, ModelMap mp) {
		if (bindingResult.hasErrors()) {
			mp.put("usuario", user);
			return "crud/editar";
		}
		String nameValid = utils.validateName(user);

		if (nameValid != null) {
			user.setError(nameValid);
			mp.put("usuario", user);
			return "crud/editar";
		}

		utils.validateRangeAge(user);
		if (user.getError() != null) {
			mp.put("usuario", user);
			return "crud/creado";
		}

		User usr = usuarioService.getUserById(user.getId());
		usr.setName(user.getName());
		usr.setAge(user.getAge());
		usr.setRelocation(user.getRelocation());
		usr.setPhone(user.getPhone());
		usr.setEmail(user.getEmail());
		usuarioService.updateUser(usr);
		mp.put("usuario", usr);
		return "crud/actualizado";

	}

	@RequestMapping(value = "/borrarTodos", method = RequestMethod.POST)
	public String borrarTodos(@Valid User user, ModelMap mp) {

		int n1 = (int) Math.floor(Math.random() * 10);
		int n2 = (int) Math.floor(Math.random() * 10);
		int suma = n1 + n2;
		String cleanS = user.getPhoneOut().replace("=", "");
		if (cleanS.isEmpty()) {
			String sumS = n1 + "+" + n2 + "=" + suma;
			System.out.println("sum:" + sumS);
			User u = new User();
			u.setName(sumS);
			mp.put("suma", u);
			mp.put("usuarios", usuarioService.getAllUsers());
			return "crud/lista";
		}
		if (user.getAge() == Integer.parseInt(cleanS.replace(" ", ""))) {
			usuarioService.deleteAllUser();
			List<User> usr = usuarioService.getAllUsers();
			if (usr.isEmpty()) {
				return "crud/vacio";
			} else {
				String sumS = n1 + "+" + n2 + "=" + suma;
				System.out.println("sum:" + sumS);
				User u = new User();
				u.setName(sumS);
				mp.put("suma", u);
				mp.put("usuarios", usuarioService.getAllUsers());
				return "crud/lista";
			}
		} else {

			String sumS = n1 + "+" + n2 + "=" + suma;
			System.out.println("sum:" + sumS);
			User u = new User();
			u.setName(sumS);
			u.setError("Wrong Plus");
			mp.put("suma", u);
			mp.put("usuarios", usuarioService.getAllUsers());
			return "crud/lista";
		}

	}

}
