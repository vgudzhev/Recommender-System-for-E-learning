package controllers;

import play.mvc.*;
import views.html.*;

public class ApplicationController extends Controller {
	
	public static Result home() {
		return ok(home.render());
	}

	public static Result pageNotFound(){
		return ok(notFound.render());
	}
	
	public static Result about(){
		return ok(about.render());
	}
}
