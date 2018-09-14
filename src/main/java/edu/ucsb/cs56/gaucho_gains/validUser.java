package edu.ucsb.cs56.gaucho_gains;

public class validUser {
	private String name;
	private boolean check;
	
	public validUser(String name, boolean check) {
		this.name = name;
		this.check = check;
	}
	@Override
	public String toString() {
		if (check)
			return "name: " + this.name + " (valid)";
		return "name: " + this.name + " (invalid)";
	}
}
