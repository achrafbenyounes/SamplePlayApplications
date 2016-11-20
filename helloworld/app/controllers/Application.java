package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;

import views.html.*;

public class Application extends Controller {
	
	public static class Eat {
		public String choosenMenu;
		@Required public Integer quantity;
		@Required @Min(1) @Max(50) public Integer price;
		public Double totalCmd;	
	}
	
	/**
	 * 
	 */
	
	public static Result eatSomething() {
        return ok(
        		eat.render(form(Eat.class))
            );
	}
    
    /**
     * Describes the hello form.
     */
    public static class Hello {
        @Required public String name;
        @Required @Min(1) @Max(100) public Integer repeat;
        public String color;
    } 
    
    // -- Actions
  
    /**
     * Home page
     */
    public static Result index() {
        return ok(
            index.render(form(Hello.class))
        );
    }
  
    /**
     * Handles the form submission.
     */
    public static Result sayHello() {
        Form<Hello> form = form(Hello.class).bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(index.render(form));
        } else {
            Hello data = form.get();
            return ok(
                hello.render(data.name, data.repeat, data.color)
            );
        }
    }
    
    public static Result returnFood() {
        return ok("what is food");     
    }
  
}
