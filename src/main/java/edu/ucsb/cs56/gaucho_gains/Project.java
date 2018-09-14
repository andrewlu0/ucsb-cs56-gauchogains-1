package edu.ucsb.cs56.gaucho_gains;

import static spark.Spark.port;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;

public class Project {
    public static final String CLASSNAME="Project";
    public static final Logger log = Logger.getLogger(CLASSNAME);
    
    public static void main(String[] args) {
	log.setLevel(Level.INFO);

	log.trace("Trace Message!");
	log.debug("Debug Message!");
	log.info("Info Message!");
	log.warn("Warn Message!");
	log.error("Error Message!");
	log.fatal("Fatal Message!");
	
        port(getHerokuAssignedPort());

	/* Dummy Database */
	HashMap<String, String> um = new HashMap<String, String>();
	um.put("clare@example.org","boyle");
	um.put("admin@example.org","admin");
	/* Login Page */
	get("/", (rq, rs) ->  new ModelAndView(um, "login.mustache"), new MustacheTemplateEngine());
	/* Profile Page */
	post("/profile", (rq, rs) -> {
		String user = rq.queryParams("email");
		String pass = rq.queryParams("pass");
		if(um.containsKey(user))
			return new ModelAndView(um, "signup.mustache");
		um.put(user,pass);
		return new ModelAndView(um, "profile.mustache");
	    }, new MustacheTemplateEngine());

	/* Next Page After Login */
	post("/community", (rq, rs) -> {
		log.info("post /community");
		String user = rq.queryParams("email");
		String pass = rq.queryParams("pass");
		System.out.println(user);
			if (um.get(user) != null && um.get(user).equals(pass)) {
			return new ModelAndView(um, "community.mustache");
		}
		else
			return new ModelAndView(um, "logon.mustache");
	    }, new MustacheTemplateEngine());

	get("/signup", (rq, rs) -> new ModelAndView(um, "signup.mustache"), new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null)
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }	
}
