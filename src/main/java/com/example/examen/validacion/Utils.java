package com.example.examen.validacion;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;

import com.example.examen.modelo.User;

@Service
public class Utils {

	@Value("${examen.age.min.relocation}")
	private int minAgeRelocation;
	@Value("${examen.age.max.relocation}")
	private int maxAgeRelocation;
	@Value("${examen.age.min}")
	private int minAge;
	@Value("${examen.age.max}")
	private int maxAge;
	@Value("${examen.name.min}")
	private int minName;
	@Value("${examen.name.max}")
	private int maxName;
	@Value("${examen.name.array.first}")
	private char[] firstWordArray;
	@Value("${examen.name.array.second}")
	private String[] secondWordArray;
	@Value("${examen.name.array.firstNo}")
	private String firstWordArrayNo;
	

	public User validateRangeAge(User user) {

		if (user.getAge() >= minAge && user.getAge() <= maxAge) {
				return user;
		} else {
			user.setError("Age must be between "+minAge+" and"+maxAge);
			return user;
		}
	}
	
	public Boolean getRelocationFlagByAge(User user) {
			if (user.getAge() >= minAgeRelocation && user.getAge() <= maxAgeRelocation) {
				return false;
			} else {
				return true;
			}
	}

	public String validateName(User user) {
		String[] nameArray = user.getName().split(" ");
		if (nameArray.length == 2) {
		   if (nameArray[0].length() >= minName && nameArray[0].length() <= maxName) {
				for (int i = 0; i < nameArray[0].length(); i++) {
					for (int j = 0; j < firstWordArray.length; j++) {
						if (nameArray[0].charAt(i) == firstWordArray[j]) {
							return "First Word  cannot have " + firstWordArray[j];
						}
					}
				}
			} else {
				return "First Word Needs to have between "+ minName+" and "+ maxName+" words";
			}

			if (nameArray[1].length() >= minName && nameArray[1].length() <= maxName) {
				for (int k = 0; k < nameArray[1].length(); k++) {
					if (!ArrayUtils.contains(secondWordArray, String.valueOf(nameArray[1].charAt(k)))) {
						String arrayStringNok=Arrays.toString(secondWordArray).replace("[", "");
						String arrayStringNok2=arrayStringNok.replace("]", "");
						return "Second Word Only can have next characters '"+arrayStringNok2+"'";
					}
				}
			} else {
				return "Second Word Needs to have between "+ minName+" and "+ maxName+" words";
			}

		} else {
			return "Name require 2 words";
		}
		return null;
	}

	public String getFirstName() {
		String arrayString=firstWordArrayNo;
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random();
		while (sb.length() < maxName) {
			int index = (int) (rnd.nextFloat() * arrayString.length());
			sb.append(arrayString.charAt(index));
		}
		String firstName = sb.toString();
		return firstName;
	}

	public String getSecondName() {
		String arrayStringNocomma=Arrays.toString(secondWordArray).replace(",", "");
		String arrayStringNok=arrayStringNocomma.replace("[", "");
		String arrayStringNok2=arrayStringNok.replace("]", "");
		String arrayString=arrayStringNok2.replace(" ", "");
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random();
		while (sb.length() < maxName) {
			int index = (int) (rnd.nextFloat() * arrayString.length());
			sb.append(arrayString.charAt(index));
		}
		String secondName = sb.toString();
		return secondName;

	}

	public int getAge() {
		Random r = new Random();
		int low = minAge;
		int high = maxAge;
		int age = r.nextInt(high - low) + low;
		return age;

	}

	public String getEmail() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return generatedstring + "@gmail.com";
	}

	public Long getPhone() {
		Random rand = new Random();
		int num1 = (rand.nextInt(7) + 1) * 100 + (rand.nextInt(8) * 10) + rand.nextInt(8);
		int num2 = rand.nextInt(743);
		int num3 = rand.nextInt(10000);

		DecimalFormat df3 = new DecimalFormat("000");
		DecimalFormat df4 = new DecimalFormat("0000");

		String stringPhoneNumber = df3.format(num1) + df3.format(num2) + df4.format(num3);
		Long phoneNumberLong = new Long(stringPhoneNumber);
		return phoneNumberLong;
	}
	
	public String printPhone(Long phoneNum) {
		StringBuilder sb = new StringBuilder(15);
		StringBuilder temp = new StringBuilder(phoneNum.toString());

		while (temp.length() < 10)
			temp.insert(0, "0");

		char[] chars = temp.toString().toCharArray();

		sb.append("(");
		for (int i = 0; i < chars.length; i++) {
			if (i == 3)
				sb.append(") ");
			else if (i == 6)
				sb.append("-");
			sb.append(chars[i]);
		}
		return sb.toString();
	}
}
