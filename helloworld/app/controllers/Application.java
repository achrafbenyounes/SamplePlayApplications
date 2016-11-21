package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;

import views.html.*;
import views.html.foodCommand;


public class Application extends Controller {
	
	public static class Eat {
		public String choosenMenu;
		@Required public Integer quantity;
		@Required @Min(1) @Max(50) public Double price;
		public Double totalCmd;	
	}
	
	/**
	 * 
	 */
	
	public static Result eatSomething() {
        return ok(eat.render(form(Eat.class)));      
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
    	Form<Eat> eatForm = form(Eat.class).bindFromRequest();
    	if (eatForm.hasErrors()) {
    		return badRequest(eat.render(eatForm));
    	} else {
    		Eat data = eatForm.get();
    		//return ok("You have choosen " + data.choosenMenu + " " + " Price " + data.price);
    		//return ok(eat.render(eatForm)); 
    		return ok(foodCommand.render(data.choosenMenu, data.quantity, data.price, data.totalCmd));
        }
  
    }
}
