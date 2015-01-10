package controllers;

import models.Book;
import models.Course;
import models.AbstractItem;
import models.Video;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.insert.*;


public class InsertController extends Controller {

	public static Result renderInfo() {
		return ok(insertInfo.render());
	}
	
	@Security.Authenticated(SecuredController.class)
	public static Result renderInsertVideo() {
		 return ok(insertVideo.render(Form.form(VideoForm.class)));
	}
	
	@Security.Authenticated(SecuredController.class)
	public static Result renderInsertItem() {
		return ok(insertItem.render(Form.form(ItemForm.class)));
	}
	
	@Security.Authenticated(SecuredController.class)
	public static Result renderInsertBook() {
		return ok(insertBook.render(Form.form(BookForm.class)));
	}
	
	@Security.Authenticated(SecuredController.class)
	public static Result renderInsertCourse() {
		return ok(insertCourse.render(Form.form(CourseForm.class)));
	}
	
	@Security.Authenticated(SecuredController.class)
	public static Result insertVideo() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String title = requestData.get("title");
		String description = requestData.get("description");
		String url = requestData.get("url");
		String imgPath = requestData.get("img-path");
		try {
			Video video = new Video.VideoBuilder()
					.title(title).description(description).url(url)
					.img(imgPath).build();
			video.save();

		} catch (Exception e) {
			ok(renderErrorPage.render());
		}
		return ok(renderSuccessPage.render());
	}
	
	@Security.Authenticated(SecuredController.class)
	public static Result insertCourse() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String title = requestData.get("title");
		String description = requestData.get("description");
		String url = requestData.get("url");
		String imgPath = requestData.get("img-path");
		try {
			int courseId = Course.find.findRowCount();
			Course course = new Course.CourseBuilder().id(++courseId)
					.title(title).description(description).url(url)
					.img(imgPath).build();
			course.save();

		} catch (Exception e) {
			ok(renderErrorPage.render());
		}
		return ok(renderSuccessPage.render());
	}
	
	@Security.Authenticated(SecuredController.class)
	public static Result insertItem() {
		DynamicForm requestData = Form.form().bindFromRequest();

		try {
			String title = requestData.get("title");
			String description = requestData.get("description");
			int id = AbstractItem.find.findRowCount();
			AbstractItem item = new AbstractItem.AbstractItemBuilder().title(title).description(description).build();
			item.save();

		} catch (Exception e) {
			ok(renderErrorPage.render());
		}
		return ok(renderSuccessPage.render());
	}
	
	@Security.Authenticated(SecuredController.class)
	public static Result insertBook() {
		DynamicForm requestData = Form.form().bindFromRequest();

		try {
			String title = requestData.get("title");
			String author = requestData.get("author");
			String publisher = requestData.get("publisher");
			String year = requestData.get("year");
			String url = requestData.get("url");
			int id = Book.find.findRowCount();
			Book book = new Book.BookBuilder().id(++id).title(title)
					.author(author).publisher(publisher).year(year).build();
			book.save();

		} catch (Exception e) {
			ok(renderErrorPage.render());
		}
		return ok(renderSuccessPage.render());
	}

	public static class VideoForm {
		@Required
		public String title;
		public String description;
		public String url;
		public String img;
	}

	public static class CourseForm {
		@Required
		public String title;
		public String description;
		public String url;
		public String img;
	}

	public static class ItemForm {
		@Required
		public String title;
		public String description;
	}

	public static class BookForm {
		@Required
		public String title;
		public String author;
		public String year;
		public String publisher;
		public String url;
	}

}
