package controllers;

import java.util.List;

import models.AbstractItem;
import models.Book;
import models.Course;
import models.ItemWrapper;
import models.User;
import models.Video;
import models.rating.BookRating;
import models.rating.CourseRating;
import models.rating.AbstractItemRating;
import models.rating.RatingWrapper;
import models.rating.VideoRating;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.smartSearch.*;

/**
 * The class is responsible for the search functionality. User should be able to
 * search items and to search others`s users preferences. All methods cannot be
 * accessed by non-registered users.
 * 
 * @author VGudzhev
 */
public class SmartSearchController extends Controller {
	private static int PAGE_LENGTH = 20;
	
	@Security.Authenticated(SecuredController.class)
	public static Result showPage() {
		return ok(smartSearch.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result search() {
		DynamicForm requestedData = Form.form().bindFromRequest();
		String type = requestedData.get("selectItem");
		String title = requestedData.get("title");
		List<ItemWrapper> allItems = null;

		if (type.equals("video")) {
			if (Video.find.where().contains("title", title).findRowCount() > 0) {
				List<Video> videos = Video.find.where()
						.contains("title", title).findList();
				allItems = ItemWrapper.wrapVideos(videos);

			}

		} else if (type.equals("course")) {
			if (Course.find.where().contains("title", title).findRowCount() > 0) {
				List<Course> courses = Course.find.where()
						.contains("title", title).findList();
				allItems = ItemWrapper.wrapCourses(courses);
			}

		} else if (type.equals("book")) {
			if (Book.find.where().contains("title", title).findRowCount() > 0) {
				List<Book> books = Book.find.where().contains("title", title)
						.findList();
				allItems = ItemWrapper.wrapBooks(books);
			}

		} else if (type.equals("item")) {
			if (AbstractItem.find.where().contains("title", title)
					.findRowCount() > 0) {
				List<AbstractItem> items = AbstractItem.find.where()
						.contains("title", title).findList();
				allItems = ItemWrapper.wrapAbstractItems(items);
			}
		}

		return ok(smartSearchResult.render(allItems));
	}

	public static Result info() {
		return ok(smartSearchInfo.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result renderSearchUserByID() {
		return ok(renderSearchUser.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result renderSearchUsersByVideoPref() {
		return ok(renderSearchVideo.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result renderSearchUsersByCoursePref() {
		return ok(renderSearchCourse.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result renderSearchUserByItemPref() {
		return ok(renderSearchItem.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result renderSearchUserByBookPref() {
		return ok(renderSearchBook.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result searchUserByID(int page) {
		DynamicForm requestData = Form.form().bindFromRequest();
		String type = requestData.get("selectItem");
		String userDetails = requestData.get("userDetails");
		String dataType = requestData.get("dataType");
		long userID = 0;
		List<RatingWrapper> allItems = null;
		
		if (type.equals("id")) {
			userID = Long.parseLong(userDetails);

		} else if (type.equals("email")) {
			userID = User.findByEmail(userDetails).userID;
		}
		
		int collectionLength = 0;
		int from = (page - 1) * PAGE_LENGTH;
		int to = (from + PAGE_LENGTH) > collectionLength ? collectionLength
				: from + PAGE_LENGTH;
		if(dataType.equals("video")){
			collectionLength = VideoRating.findByUserID(userID).size();
			allItems = RatingWrapper.wrapVideos(VideoRating.findByUserID(userID).subList(from, to));
			
		}else if (dataType.equals("book")) {
			collectionLength = BookRating.findByUserID(userID).size();
			allItems = RatingWrapper.wrapBooks(BookRating.findByUserID(userID).subList(from, to));
					
		}else if (dataType.equals("course")) {
			collectionLength = CourseRating.findByUserID(userID).size();
			allItems = RatingWrapper.wrapCourses(CourseRating.findByUserID(userID).subList(from, to));
					
		}else if (dataType.equals("item")) {
			collectionLength = AbstractItemRating.findByUserID(userID).size();
			allItems = RatingWrapper.wrapAbstractItems(AbstractItemRating.findByUserID(userID).subList(from, to));
					
		}

		return ok(listUserRating.render(allItems, collectionLength, page, PAGE_LENGTH, dataType));
	}

	public static Result searchUserByVideoPref() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String videoID = requestData.get("videoid");
		List<VideoRating> videoRating = VideoRating.find.where()
				.eq("videoID", videoID).findList();

		return ok(listUserByVideoPref.render(videoRating));
	}

	@Security.Authenticated(SecuredController.class)
	public static Result searchUserByCoursePref() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String courseID = requestData.get("courseid");
		List<CourseRating> courseRating = CourseRating.find.where()
				.eq("courseID", courseID).findList();

		return ok(listUserByCoursePref.render(courseRating));
	}

	@Security.Authenticated(SecuredController.class)
	public static Result searchUserByItemPref() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String itemID = requestData.get("itemid");
		List<AbstractItemRating> abstractItemRating = AbstractItemRating.find
				.where().eq("itemID", itemID).findList();

		return ok(listUserByItemPref.render(abstractItemRating));
	}

	@Security.Authenticated(SecuredController.class)
	public static Result searchUserByBookPref() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String bookID = requestData.get("bookid");
		List<BookRating> booksRating = BookRating.find.where()
				.eq("bookID", bookID).findList();

		return ok(listUserByBookPref.render(booksRating));
	}
}
