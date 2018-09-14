package edu.ucsb.cs56.gaucho_gains;

import java.util.HashMap;

public class UserManager {
    private HashMap<String,String> users;

    public UserManager(){
	users = new HashMap<String, String>();
	users.put("clare@example.org","boyle");
	users.put("chandler@example.org","forrest");
    }
    
    public boolean checkUser(String email, String pass){
	 if (users.containsKey(email) == false)
		 return false;
	 return users.get(email).equals(pass);
	
    }

    public boolean addUser(String email, String pass){
	if(users.containsKey(email) == false){
	    users.put(email,pass);
	    return true;
	}
	else
	    return false;
    }	    
}

