package controllers;

import play.mvc.*;
import recommendationSystem.dataset.BooksDataset;
import recommendationSystem.dataset.CoursesDataset;
import recommendationSystem.dataset.ItemsDataset;
import recommendationSystem.dataset.UserDataset;
import recommendationSystem.dataset.VideoDataset;
import views.html.*;

public class ApplicationController extends Controller {
	
	public static Result home() {
		return ok(home.render());
	}

	public static Result pageNotFound(){
		return ok(notFound.render());
	}
	
	public static Result init(){
		UserDataset.init();
		ItemsDataset.init();
		CoursesDataset.init();
		VideoDataset.init();
		BooksDataset.init();
		return ok(home.render());
	}
	
	public static Result about(){
		return ok(about.render());
	}
}
