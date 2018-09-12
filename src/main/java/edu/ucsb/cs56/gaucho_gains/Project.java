package edu.ucsb.cs56.gaucho_gains;

import static spark.Spark.port;

import org.apache.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Simple example of using Mustache Templates
 *
 */

public class Project {

	public static final String CLASSNAME="Project";
	
	public static final Logger log = Logger.getLogger(CLASSNAME);

	public static void main(String[] args) {

        port(getHerokuAssignedPort());

	Map map = new HashMap();	
	HashMap<String, String> users = new HashMap<>();
	users.put("John Smith","admin");
	users.put("Joanne Li", "admin");
	users.put("Chandler Forrestor", "admin");
	users.put("Maga Kim", "admin");
        get("/", (rq, rs) -> new ModelAndView(users, "login.mustache"), new MustacheTemplateEngine());
	post("/home", (rq, rs) -> {
    	String a, b;
    	a = rq.queryParams("userlogin");
    	b = rq.queryParams("psw");
    	if (users.get(a) != null && users.get(a).equals(b))
		return  new ModelAndView(users, "test.mustache");
	 else
    	return new ModelAndView(users, "logon.mustache");	}, new MustacheTemplateEngine());

	get("/signup", (rq, rs) -> new ModelAndView(users, "signup.mustache"), new MustacheTemplateEngine());

	post("/profile", (rq, rs) -> new ModelAndView(users, "profile.mustache"), new MustacheTemplateEngine());		
	}
	
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

	
}
