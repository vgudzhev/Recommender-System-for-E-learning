package controllers;

import java.util.ArrayList;
import java.util.List;

import models.AbstractItem;
import models.Book;
import models.Course;
import models.User;
import models.Video;
import models.rating.*;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import recommendationSystem.*;
import recommendationSystem.dataset.DatasetType;
import views.html.recommendations.*;

/**
 * This class provide users with recommender functionality. All methods should
 * be secured and used for authenticated users. Note that The user can access
 * its personal recommendations only if has enough ratings. The number of
 * minimal ratings is 5.
 * 
 * @author VGudzhev
 *
 */
public class RecommenderController extends Controller {
	private static long USER_ID;
	private static DatasetType datasetType;
	static int MIN_ITEMS_FOR_RATE = 5;
	static final String MIN_ELEMENTS_ERROR = "You should rate at least "
			+ MIN_ITEMS_FOR_RATE + " items";
	static final String UNEXPECTED_ERROR = "You can`t get your reccomendations at this momment!";

	@Security.Authenticated(SecuredController.class)
	public static Result info(String type) {
		USER_ID = User.findByEmail(session("user")).userID;
		if (type.equals("books")) {
			datasetType = DatasetType.BOOKS;

		} else if (type.equals("videos")) {
			datasetType = DatasetType.VIDEOS;

		} else if (type.equals("courses")) {
			datasetType = DatasetType.COURSES;

		} else if (type.equals("items")) {
			datasetType = DatasetType.ABSTRACT_ITEMS;
		}

		return ok(recommendationsInfo.render());
	}

	public static Result infoPage() {
		return ok(recommendationsInfo.render());
	}

	@Security.Authenticated(SecuredController.class)
	public static Result getRecommendations(String type) {
		List<RecommendedItem> list = null;
		RecommendationType recommendationType = RecommendationType
				.getType(type);
		if (!isEligibleForRecommendation(USER_ID, datasetType)) {
			return ok(recommendationStatus.render(MIN_ELEMENTS_ERROR));
		}

		try {
			list = RecommendationFactory.get(recommendationType, USER_ID,
					datasetType);
		} catch (Exception e) {
			return ok(recommendationStatus.render(UNEXPECTED_ERROR));
		}

		List<Long> recommendedItemsIDs = getRecommendedItemsIDs(list);

		switch (datasetType) {
		case BOOKS:
			List<Book> books = new ArrayList<Book>();
			for (int i = 0; i < recommendedItemsIDs.size(); i++) {
				books.add(Book.findById(recommendedItemsIDs.get(i)));
			}
			return ok(listBookRecommendatons.render(books,
					recommendationType.getDescription()));
		case COURSES:
			List<Course> courses = new ArrayList<Course>();
			for (int i = 0; i < recommendedItemsIDs.size(); i++) {
				courses.add(Course.findByID(recommendedItemsIDs.get(i)));
			}
			return ok(listCourseRecommendations.render(courses,
					recommendationType.getDescription()));

		case ABSTRACT_ITEMS:
			List<AbstractItem> items = new ArrayList<AbstractItem>();
			for (int i = 0; i < recommendedItemsIDs.size(); i++) {
				items.add(AbstractItem.findByID(recommendedItemsIDs.get(i)));
			}

			return ok(listAbstractItemRecommendations.render(items,
					recommendationType.getDescription()));

		case VIDEOS:
			List<Video> videos = new ArrayList<Video>();
			for (int i = 0; i < recommendedItemsIDs.size(); i++) {
				videos.add(Video.findByID(recommendedItemsIDs.get(i)));
			}

			return ok(listVideoRecommendations.render(videos,
					recommendationType.getDescription()));

		default:
			return redirect("/404");
		}

	}

	private static boolean isEligibleForRecommendation(long user_id,
			DatasetType dataset_Type) {
		int count = 0;
		switch (dataset_Type) {
		case ABSTRACT_ITEMS:
			count = AbstractItemRating.findByUserID(user_id).size();
			break;

		case BOOKS:
			count = BookRating.findByUserID(user_id).size();
			break;
		case COURSES:
			count = CourseRating.findByUserID(user_id).size();
			break;
		case VIDEOS:
			count = VideoRating.findByUserID(user_id).size();
			break;
		default:
			count = 0;
			break;
		}

		return count > 5 ? true : false;

	}

	private static List<Long> getRecommendedItemsIDs(List<RecommendedItem> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}

		List<Long> idList = new ArrayList<Long>(list.size());
		for (RecommendedItem item : list) {
			idList.add(item.getItemID());
		}
		return idList;
	}
}