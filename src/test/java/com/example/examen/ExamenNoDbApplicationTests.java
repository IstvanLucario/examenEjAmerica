package com.example.examen;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.examen.modelo.User;
import com.example.examen.validacion.Utils;

@SpringBootTest
class ExamenNoDbApplicationTests {
	private final static Logger LOGGER = Logger.getLogger("ExamenNoDbApplicationTests");

	@Test
	void contextLoads() {
	}

	@Autowired
	Utils val;

	// validar que genere primer nombre con palabra entre 3 y 5 caracteres sin “a,
	// o, u, m, p”
	@Test
	void generarNombre() {
		String firstName = val.getFirstName();
		LOGGER.info("nombre:" + firstName);
		System.out.println("nombre:" + firstName);
	}

	// validar que genere segundo nombre con palabra entre 3 y 5 caracteres solo con
	// “asdfghjklopuytem”
	@Test
	void generarSegundoNombre() {
		String secondName = val.getSecondName();
		LOGGER.info("nombre:" + secondName);
		System.out.println("nombre:" + secondName);
	}

	// validacion donde nombres cumplen reglas
	@Test
	void validarNombre() {
		User usr = new User();
		usr.setName("jjj kkk");
		String name = val.validateName(usr);
		LOGGER.info("nombre:" + name);
		System.out.println("nombre:" + name);
	}
	
	// validacion donde nombres No cumplen reglas de 2 palabras
	@Test
	void validarNombreNo2Palabras() {
		User usr = new User();
		usr.setName("jjj");
		String name = val.validateName(usr);
		LOGGER.info("nombre:" + name);
		System.out.println("nombre:" + name);
	}	

	// validacion donde nombres No cumplen reglas de primer palabra contiene letras no permitidas
	@Test
	void validarNombreNoCumpleLetras() {
		User usr = new User();
		usr.setName("jaj kkk");
		String name = val.validateName(usr);
		LOGGER.info("nombre:" + name);
		System.out.println("nombre:" + name);
	}
	// validacion donde nombres No cumplen reglas de segunda palabra solo contiene letras  permitidas
	@Test
	void validarNombreNo2CumpleLetras() {
		User usr = new User();
		usr.setName("jjj iii");
		String name = val.validateName(usr);
		LOGGER.info("nombre:" + name);
		System.out.println("nombre:" + name);
	}

	// validar que generador cree edad entre 18 y 45
	@Test
	void generarEdad() {
		int age = val.getAge();
		LOGGER.info("edad:" + age);
		System.out.println("edad:" + age);
	}

	// validar que fecha cumple con el parametro 18 a 45
	@Test
	void validateEdad() {
		User usr = new User();
		usr.setAge(18);
		usr = val.validateRangeAge(usr);
		LOGGER.info("mensaje:" + usr.getError());
		System.out.println("mensaje:" + usr.getError());
	}

	// validar que fecha no cumple con validacion mayor a 18
	@Test
	void validateEdadNoMayor() {
		User usr = new User();
		usr.setAge(10);
		usr = val.validateRangeAge(usr);
		LOGGER.info("mensaje:" + usr.getError());
		System.out.println("mensaje:" + usr.getError());
	}

	// validar que fecha no cumple con validacion menor a 45
	@Test
	void validateEdadNoMenor() {
		User usr = new User();
		usr.setAge(46);
		usr = val.validateRangeAge(usr);
		LOGGER.info("mensaje:" + usr.getError());
		System.out.println("mensaje:" + usr.getError());
	}
	
	//validar que se genere email
	@Test
	void generarEmail() {
		
		String email = val.getEmail();
		LOGGER.info("email:" + email);
		System.out.println("email:" + email);
		
	}
	@Test
	void generarNumeroTelefonico() {
		Long phone=val.getPhone();
		LOGGER.info("phone:" + phone);
		System.out.println("phone:" + phone);
		
	}
}
