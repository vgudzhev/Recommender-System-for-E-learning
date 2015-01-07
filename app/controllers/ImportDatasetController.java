package controllers;

import java.io.File;
import java.io.FileWriter;

import play.Logger;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import recommendationSystem.dataset.DatasetPath;
import recommendationSystem.dataset.ImportUtils.DatasetImporter;
import views.html.admin.statusPage;

public class ImportDatasetController extends Controller {

	public static Result importUsers() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart users = body.getFile("usersInput");
		if (users != null) {
			try{
				File file = users.getFile();
				DatasetImporter.getCSVImporter().importUsers(file);
				
			}catch(Exception ex){
				return badRequest(statusPage.render(Messages.get("dataset.import.error")));
			}
			
			return AdminController.listUsers(1);
		} else {
			return badRequest(statusPage.render(Messages.get("no.csv.found")));
		}
	}
	
	public static Result importAbtractItems() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart items = body.getFile("abstractItemInput");
		if (items != null) {
			File file = items.getFile();
			try{
				DatasetImporter.getCSVImporter().importItems(file);
				
			}catch(Exception ex){
				return badRequest(statusPage.render(Messages.get("dataset.import.error")));
			}
			
			return AdminController.listAbstractItems(1);
		} else {
			return badRequest(statusPage.render(Messages.get("no.csv.found")));
		}
	}
	
	public static Result importAbtractItemRatings() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart users = body.getFile("abstractItemRatingInput");
		if (users != null) {
			File file = users.getFile();
			try{
				DatasetImporter.getCSVImporter().importItemsRating(file);
				
			}catch(Exception ex){
				return badRequest(statusPage.render(Messages.get("dataset.import.error")));
			}
			
			return AdminController.listAbstractItemsRating(1);
		} else {
			return badRequest(statusPage.render(Messages.get("no.csv.found")));
		}
	}
	
	public static Result importBooks() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart users = body.getFile("booksInput");
		if (users != null) {
			File file = users.getFile();
			try{
				DatasetImporter.getCSVImporter().importBooks(file);
				
			}catch(Exception ex){
				return badRequest(statusPage.render(Messages.get("dataset.import.error")));
			}
			
			return AdminController.listBooks(1);
		} else {
			return badRequest(statusPage.render(Messages.get("no.csv.found")));
		}
	}
	
	public static Result importBooksRating() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart users = body.getFile("booksRatingInput");
		if (users != null) {
			File file = users.getFile();
			try{
				DatasetImporter.getCSVImporter().importBookRating(file);
				
			}catch(Exception ex){
				return badRequest(statusPage.render(Messages.get("dataset.import.error")));
			}
			
			return AdminController.listBookRating(1);
		} else {
			return badRequest(statusPage.render(Messages.get("no.csv.found")));
		}
	}
	
	public static Result importCourses() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart courses = body.getFile("courseInput");
		if (courses != null) {
			File file = courses.getFile();
			try{
				DatasetImporter.getCSVImporter().importCourses(file);
				
			}catch(Exception ex){
				return badRequest(statusPage.render(Messages.get("dataset.import.error")));
			}
			
			return AdminController.listCourses(1);
		} else {
			return badRequest(statusPage.render(Messages.get("no.csv.found")));
		}
	}
	
	public static Result importCourseRating() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart users = body.getFile("courseRatingInput");
		if (users != null) {
			File file = users.getFile();
			try{
				DatasetImporter.getCSVImporter().importCourseRating(file);
				
			}catch(Exception ex){
				return badRequest(statusPage.render(Messages.get("dataset.import.error")));
			}
			
			return AdminController.listCourseRating(1);
		} else {
			return badRequest(statusPage.render(Messages.get("no.csv.found")));
		}
	}
	
	public static Result importVideos() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart users = body.getFile("videoInput");
		if (users != null) {
			File file = users.getFile();
			try{
				DatasetImporter.getCSVImporter().importVideo(file);
				
			}catch(Exception ex){
				return badRequest(statusPage.render(Messages.get("dataset.import.error")));
			}
			
			return AdminController.listVideos(1);
		} else {
			return badRequest(statusPage.render(Messages.get("no.csv.found")));
		}
	}
	
	public static Result importVideoRating() {
	MultipartFormData body = request().body().asMultipartFormData();
	FilePart users = body.getFile("videoRatingInput");
	if (users != null) {
		File file = users.getFile();
		try{
			DatasetImporter.getCSVImporter().importVideoRating(file);
			
		}catch(Exception ex){
			return badRequest(statusPage.render(Messages.get("dataset.import.error")));
		}
		
		return AdminController.listVideoRating(1);
	} else {
		return badRequest(statusPage.render(Messages.get("no.csv.found")));
	}
}
}
