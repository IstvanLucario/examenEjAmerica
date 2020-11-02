package com.example.examen.modelo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

	private Long id;
	@NotEmpty
	private String Name;
	
	@NotNull
	private int Age;
	
	private Boolean Relocation;
	@NotNull
	private Long Phone;
	@NotEmpty 
    @Email
	private String Email;
	private String PhoneOut;
	private String Error;
	
    public User() {
		super();
	}

	public User(Long id, String name, int age, Boolean relocation, Long phone, String email, String phoneOut, String error) {
		super();
		this.id = id;
		this.Name = name;
		Age = age;
		Relocation = relocation;
		Phone = phone;
		Email = email;
		PhoneOut=phoneOut;
		Error=error;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public Boolean getRelocation() {
		return Relocation;
	}

	public void setRelocation(Boolean relocation) {
		Relocation = relocation;
	}

	public Long getPhone() {
		return Phone;
	}

	public void setPhone(Long phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneOut() {
		return PhoneOut;
	}

	public void setPhoneOut(String phoneOut) {
		PhoneOut = phoneOut;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}
	
	
	
	
	
	



	
	
}
