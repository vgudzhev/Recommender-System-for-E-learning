package controllers;

import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import recommendationSystem.dataset.DatasetDropper;
import views.html.admin.statusPage;

public class DeleteDatasetController extends Controller{
	private static int FIRST_PAGE = 1;
	
	public static Result deleteAllUsers(){
		try{
			DatasetDropper.dropUserTable();
			return AdminController.listUsers(1);
		}catch(Exception ex){
			return badRequest(statusPage.render(Messages.get("deleteStatus.error")));
		}
	}
	
	public static Result deleteAllAbstractItems(){
		try{
			DatasetDropper.dropAbstractItemTable();
			return AdminController.listAbstractItems(FIRST_PAGE);
		}catch(Exception ex){
			return badRequest(statusPage.render(Messages.get("deleteStatus.error")));
		}
	}
	
	public static Result deleteAllBooks(){
		try{
			DatasetDropper.dropBookTable();
			return AdminController.listBooks(FIRST_PAGE);
		}catch(Exception ex){
			return badRequest(statusPage.render(Messages.get("deleteStatus.error")));
		}
	}
	
	public static Result deleteAllCourses(){
		try{
			DatasetDropper.dropCourseTable();
			return AdminController.listCourses(FIRST_PAGE);
		}catch(Exception ex){
			return badRequest(statusPage.render(Messages.get("deleteStatus.error")));
		}
	}
	
	public static Result deleteAllVideos(){
		try{
			DatasetDropper.dropVideoTable();
			return AdminController.listVideos(FIRST_PAGE);
		}catch(Exception ex){
			return badRequest(statusPage.render(Messages.get("deleteStatus.error")));
		}
	}
	
	public static Result deleteAllAbstractItemsRatings(){
		try{
			DatasetDropper.dropAbstractItemRatingTable();
			return AdminController.listAbstractItemsRating(FIRST_PAGE);
		}catch(Exception ex){
			return badRequest(statusPage.render(Messages.get("deleteStatus.error")));
		}
	}
	
	public static Result deleteAllBookRatings(){
		try{
			DatasetDropper.dropBookRatingTable();
			return AdminController.listBookRating(FIRST_PAGE);
		}catch(Exception ex){
			return badRequest(statusPage.render(Messages.get("deleteStatus.error")));
		}
	}
	
	public static Result deleteAllCourseRatings(){
		try{
			DatasetDropper.dropCourseRatingTable();
			return AdminController.listCourseRating(FIRST_PAGE);
		}catch(Exception ex){
			return badRequest(statusPage.render(Messages.get("deleteStatus.error")));
		}
	}
	
	public static Result deleteAllVideoRatings(){
		try{
			DatasetDropper.dropVideoRatingTable();
			return AdminController.listVideoRating(FIRST_PAGE);
		}catch(Exception ex){
			return badRequest(statusPage.render(Messages.get("deleteStatus.error")));
		}
	}
}
