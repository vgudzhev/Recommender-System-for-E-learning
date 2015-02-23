package controllers;

import models.AbstractItem;
import models.Book;
import models.Course;
import models.User;
import models.Video;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import recommendationSystem.dataset.DatasetType;
import recommendationSystem.rating.RatingEngine;
import views.html.rateItems.rateItems;
import views.html.rateItems.rateItemsInfo;
import views.html.rateItems.rateItemsStatus;

/**
 * This class provides rating functionality. Every registered user should be
 * able to rate all items.
 * 
 * @author VGudzhev
 */
public class RateItemsController extends Controller {
	static DatasetType datasetType;

	@Security.Authenticated(SecuredController.class)
	public static Result rate(String type) {

		User user = User.findByEmail(session("user"));
		long userID = user.userID;
		RatingEngine ratingEngine = new RatingEngine(userID);
		long id = -1;
		String title = null;
		String description = null;

		if (type.equals("books")) {
			datasetType = DatasetType.BOOKS;
			id = ratingEngine.getBookForRate();
			Book book = Book.findById(id);
			title = book.title;
			description = book.description;	

		} else if (type.equals("videos")) {
			datasetType = DatasetType.VIDEOS;
			id = ratingEngine.getVideoForRate();
			Video video = Video.findByID(id);
			title = video.title;
			description = video.description;

		} else if (type.equals("courses")) {
			datasetType = DatasetType.COURSES;
			id = ratingEngine.getCoursesForRate();
			Course course = Course.findByID(id);
			title = course.title;
			description = course.description;

		} else if (type.equals("items")) {
			datasetType = DatasetType.ABSTRACT_ITEMS;
			id = ratingEngine.getAbstractItemForRate();
			AbstractItem item = AbstractItem.findByID(id);
			title = item.title;
			description = item.description;
		}

		return ok(rateItems.render(id, title, description));
	}

	@Security.Authenticated(SecuredController.class)
	public static Result postRate(String itemID) {
		DynamicForm requestedData = new DynamicForm().bindFromRequest();
		String input = requestedData.get("input-21b");
		double rating = Double.parseDouble(input);

		User user = User.findByEmail(session("user"));
		long ratedItemID = Long.parseLong(itemID);
		String message = null;
		try {
			RatingEngine.rate(user.userID, ratedItemID, rating, datasetType);
			message = "Your item was successfully rated with " + rating
					+ " stars!";
			return ok(rateItemsStatus.render(message, 0));
		} catch (Exception e) {
			message = "Rating operation was unsuccessful!";
			return ok(rateItemsStatus.render(message, 1));
		}
	}

	public static Result info() {
		return ok(rateItemsInfo.render());
	}

	public static class RatingForm {
		public String rating;
	}
}
