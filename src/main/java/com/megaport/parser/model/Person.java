package com.megaport.parser.model;

import java.util.Arrays;

import com.megaport.parser.util.Constants;

public class Person{
	
	private String firstName;
	private String lastName;
	
			
	public Person(String firstName, String lastName) {
		if(firstName != null)
			this.firstName = firstName.toUpperCase();
		if(lastName != null)
			this.lastName = lastName.toUpperCase();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append(this.lastName)
				.append(", ")
				.append(this.firstName)
				.toString()
				.trim();
	}

	public static Person mapToPerson(final String data) {
		if(data == null || !data.matches(Constants.REGEX_FILE_LINE_STRUCTURE)) 
			return null;
		
		String[] splitted = Arrays.stream(data.split(","))
  			  .map(String::trim)
  			  .toArray(String[]::new);
		return new Person(splitted[1], splitted[0]);
	}
	
	

}