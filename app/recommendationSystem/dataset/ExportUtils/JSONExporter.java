package recommendationSystem.dataset.ExportUtils;

import java.util.List;

import models.AbstractItem;
import models.Book;
import models.Course;
import models.User;
import models.Video;
import models.rating.AbstractItemRating;
import models.rating.BookRating;
import models.rating.CourseRating;
import models.rating.VideoRating;
import play.mvc.Result;
import play.libs.Json;

public class JSONExporter {

	public Result exportAbstractItemsRating() {
		List<AbstractItemRating> ratings = AbstractItemRating.find.all();
		return play.mvc.Results.ok(Json.toJson(ratings));
	}

	public Result exportCourseRating() {
		List<CourseRating> ratings = CourseRating.find.all();
		return play.mvc.Results.ok(Json.toJson(ratings));
	}

	public Result exportVideoRating() {
		List<VideoRating> ratings = VideoRating.find.all();
		return play.mvc.Results.ok(Json.toJson(ratings));
	}

	public Result exportBookRating() {
		List<BookRating> ratings = BookRating.find.all();
		return play.mvc.Results.ok(Json.toJson(ratings));
	}

	public Result exportUsers() {
		List<User> users = User.find.all();
		return play.mvc.Results.ok(Json.toJson(users));
	}

	public Result exportAbstractItems() {
		List<AbstractItem> items = AbstractItem.find.all();
		return play.mvc.Results.ok(Json.toJson(items));
	}

	public Result exportCourses() {
		List<Course> courses = Course.find.all();
		return play.mvc.Results.ok(Json.toJson(courses));
	}

	public Result exportBooks() {
		List<Book> books = Book.find.all();
		return play.mvc.Results.ok(Json.toJson(books));
	}

	public Result exportVideos() {
		List<Video> videos = Video.find.all();
		return play.mvc.Results.ok(Json.toJson(videos));
	}
}
