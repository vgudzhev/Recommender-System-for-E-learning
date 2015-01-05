package controllers;

import java.util.Iterator;
import java.util.List;

import models.Book;
import models.Course;
import models.AbstractItem;
import models.User;
import models.Video;
import models.rating.AbstractItemRating;
import models.rating.BookRating;
import models.rating.CourseRating;
import models.rating.VideoRating;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;

public class AdminController extends Controller {
	private static final int PAGE_LENGTH = 20;
	private static final String SEPARATOR=",";
	private static final String NEW_LINE="\n";

	public static Result adminHome() {
		return ok(admin.render());
	}

	public static Result listUsers(int page) {
		int collectionLength = User.find.findRowCount();
		int from = getStartPage(page);
		int to = getEndPage(collectionLength, from);
		List<User> allUsers = User.find.findList().subList(from, to);

		return ok(listUsers.render(allUsers, collectionLength, page,
				PAGE_LENGTH));
	}

	public static Result listCourses(int page) {
		int collectionLength = Course.find.findRowCount();
		int from = getStartPage(page);
		int to = getEndPage(collectionLength, from);
		List<Course> allCourses = Course.find.findList().subList(from, to);
		return ok(listCourses.render(allCourses, collectionLength, page,
				PAGE_LENGTH));
	}

	public static Result listBooks(int page) {
		int collectionLength = Book.find.findRowCount();
		int from = getStartPage(page);
		int to = getEndPage(collectionLength, from);
		List<Book> allbooks = Book.find.findList().subList(from, to);
		return ok(listBooks.render(allbooks, collectionLength, page,
				PAGE_LENGTH));
	}

	public static Result listVideos(int page) {
		int collectionLength = Video.find.findRowCount();
		int from = getStartPage(page);
		int to = getEndPage(collectionLength, from);
		List<Video> allVideos = Video.find.findList().subList(from, to);
		return ok(listVideos.render(allVideos, collectionLength, page,
				PAGE_LENGTH));
	}

	public static Result listAbstractItems(int page) {
		int collectionLength = AbstractItem.find.findRowCount();
		int from = getStartPage(page);
		int to = getEndPage(collectionLength, from);
		List<AbstractItem> allratings = AbstractItem.find.findList().subList(
				from, to);
		return ok(listAbstractItems.render(allratings, collectionLength, page,
				PAGE_LENGTH));
	}

	public static Result listAbstractItemsRating(int page) {
		int collectionLength = AbstractItemRating.find.findRowCount();
		int from = getStartPage(page);
		int to = getEndPage(collectionLength, from);

		List<AbstractItemRating> allratings = AbstractItemRating.find
				.findList().subList(from, to);
		return ok(listAbstractItemsRating.render(allratings, collectionLength,
				page, PAGE_LENGTH));
	}
	
	public static Result listAbstractItemsRatingAsCSV(){
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<AbstractItemRating> ratings = AbstractItemRating.find.all();
				for (AbstractItemRating currentRating : ratings) {
					out.write(currentRating.id+"");
					out.write(SEPARATOR);
					out.write(currentRating.userID+"");
					out.write(SEPARATOR);
					out.write(currentRating.itemID+"");
					out.write(SEPARATOR);
					out.write(currentRating.rating+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};

		response().setContentType("text/csv");
		response().setHeader("Content-disposition",
				"attachment; filename=" + "abstractItemsRating.csv");
		return ok(chunks);
	}

	public static Result listCourseRating(int page) {
		int collectionLength = CourseRating.find.findRowCount();
		int from = getStartPage(page);
		int to = getEndPage(collectionLength, from);
		List<CourseRating> allratings = CourseRating.find.findList().subList(
				from, to);
		return ok(listCourseRating.render(allratings, collectionLength, page,
				PAGE_LENGTH));
	}
	
	public static Result listCourseRatingAsCSV(){
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<CourseRating> ratings = CourseRating.find.all();
				for (CourseRating currentRating : ratings) {
					out.write(currentRating.id+"");
					out.write(SEPARATOR);
					out.write(currentRating.userID+"");
					out.write(SEPARATOR);
					out.write(currentRating.courseID+"");
					out.write(SEPARATOR);
					out.write(currentRating.rating+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};

		response().setContentType("text/csv");
		response().setHeader("Content-disposition",
				"attachment; filename=" + "courseRating.csv");
		return ok(chunks);
	}

	public static Result listVideoRating(int page) {
		int collectionLength = VideoRating.find.findRowCount();
		int from = getStartPage(page);
		int to = getEndPage(collectionLength, from);
		List<VideoRating> allratings = VideoRating.find.findList().subList(
				from, to);
		return ok(listVideoRating.render(allratings, collectionLength, page,
				PAGE_LENGTH));
	}
	
	public static Result listVideoRatingAsCSV(){
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<VideoRating> ratings = VideoRating.find.all();
				for (VideoRating currentRating : ratings) {
					out.write(currentRating.id+"");
					out.write(SEPARATOR);
					out.write(currentRating.userID+"");
					out.write(SEPARATOR);
					out.write(currentRating.videoID+"");
					out.write(SEPARATOR);
					out.write(currentRating.rating+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};

		response().setContentType("text/csv");
		response().setHeader("Content-disposition",
				"attachment; filename=" + "videoRating.csv");
		return ok(chunks);
	}

	public static Result listBookRating(int page) {
		int collectionLength = BookRating.find.findRowCount();
		int from = getStartPage(page);
		int to = getEndPage(collectionLength, from);
		List<BookRating> allratings = BookRating.find.findList().subList(from,
				to);
		return ok(listBooksRating.render(allratings, collectionLength, page,
				PAGE_LENGTH));
	}
	
	public static Result listBookRatingAsCSV(){	
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<BookRating> ratings = BookRating.find.all();
				
				for(BookRating currentRating: ratings){
					out.write(currentRating.id+"");
					out.write(SEPARATOR);
					out.write(currentRating.userID+"");
					out.write(SEPARATOR);
					out.write(currentRating.bookID+"");
					out.write(SEPARATOR);
					out.write(currentRating.rating+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};

		response().setContentType("text/csv");
		response().setHeader("Content-disposition",
				"attachment; filename=" + "export.csv");
		return ok(chunks);
		
	}
	
	public static Result importExport(){		
		return ok(importExport.render());
	}
	
	public static Result renderImport(){
		return ok(importPage.render());
	}
	
	public static Result renderExport(){
		return ok(exportPage.render());
	}
	
	public static Result exportAll(){
//		DynamicForm requestData = Form.form().bindFromRequest();
		
		return TODO;
	}

	public static Result integration() {
		return ok(integration.render());
	}

	private static int getEndPage(int collectionLength, int from) {
		int to = (from + PAGE_LENGTH) > collectionLength ? collectionLength
				: from + PAGE_LENGTH;
		return to;
	}

	private static int getStartPage(int page) {
		int from = (page - 1) * PAGE_LENGTH;
		return from;
	}
}
