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
import recommendationSystem.dataset.InsertionUtils.DatasetInserter;
import views.html.insert.*;

public class InsertController extends Controller {

	public static Result renderInfo() {
		return ok(insertInfo.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result renderInsertVideo() {
		return ok(insertVideo.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result renderInsertItem() {
		return ok(insertItem.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result renderInsertBook() {
		return ok(insertBook.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result renderInsertCourse() {
		return ok(insertCourse.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result insertVideo() {
		DynamicForm requestData = Form.form().bindFromRequest();

		try {
			String title = requestData.get("insertVideoTitle");
			String shortDescription = requestData
					.get("insertVideoShortDescription");
			String description = requestData.get("insertVideoDescription");
			String url = requestData.get("insertVideoURL");
			String img = requestData.get("insertVideoIMG");

			Video video = new Video.VideoBuilder().title(title)
					.shortDescription(shortDescription)
					.description(description).url(url).img(img).build();
			video.save();

			DatasetInserter.insertSingleVideo(video);

		} catch (Exception e) {
			ok(renderErrorPage.render());
		}
		return ok(renderSuccessPage.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result insertCourse() {
		DynamicForm requestData = Form.form().bindFromRequest();

		try {
			String title = requestData.get("insertCourseTitle");
			String url = requestData.get("insertCourseURL");
			String img = requestData.get("insertCourseIMG");
			String shortDescription = requestData
					.get("insertCourseShortDescription");
			String description = requestData.get("insertCourseDescription");
			Course course = new Course.CourseBuilder().title(title).url(url)
					.img(img).shortDescription(shortDescription)
					.description(description).build();
			course.save();
			DatasetInserter.insertSingleCourse(course);

		} catch (Exception e) {
			ok(renderErrorPage.render());
		}
		return ok(renderSuccessPage.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result insertItem() {
		DynamicForm requestData = Form.form().bindFromRequest();

		try {
			String title = requestData.get("insertItemTitle");
			String shortDescription = requestData.get("insertShortDescription");
			String description = requestData.get("insertDescripton");
			String img = requestData.get("insertIMG");

			AbstractItem item = new AbstractItem.AbstractItemBuilder()
					.title(title).shortDescription(shortDescription)
					.description(description).img(img).build();

			item.save();

			DatasetInserter.insertSingleItem(item);
		} catch (Exception e) {
			ok(renderErrorPage.render());
		}
		return ok(renderSuccessPage.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result insertBook() {
		DynamicForm requestData = Form.form().bindFromRequest();

		try {
			String title = requestData.get("insertBookTitle");
			String author = requestData.get("insertBookAuthor");
			String year = requestData.get("insertBookYear");
			String publisher = requestData.get("inseertBookPublisher");
			String img = requestData.get("insertBookIMG");
			String shortDescription = requestData.get("insertShortDescription");
			String description = requestData.get("insertDescription");
		
			int id = Book.find.findRowCount();
			Book book = new Book.BookBuilder()
							.title(title)
							.author(author)
							.year(year)
							.publisher(publisher)
							.image(img)
							.shortDescription(shortDescription)
							.description(description)
							.build();
			book.save();
			DatasetInserter.insertSingleBook(book);

		} catch (Exception e) {
			ok(renderErrorPage.render());
		}
		return ok(renderSuccessPage.render());
	}
}
