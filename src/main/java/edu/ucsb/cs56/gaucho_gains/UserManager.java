package edu.ucsb.cs56.gaucho_gains;

import java.util.HashMap;

public class UserManager {
    private HashMap<String,String> users = new HashMap<String,String>();

    public UserManager(){
	users.put("clare@example.org","boyle");
	users.put("chandler@example.org","forrest");
    }
    
    public boolean checkUser(String email, String pass){
	if(users.containsKey(email))
	    return (users.get(email)).equals(pass);
	else
	    return false;
    }

    public boolean addUser(String email, String pass){
	if(users.containsKey(email)){
	    users.put(email,pass);
	    return true;
	}
	else
	    return false;
    }	    
}

