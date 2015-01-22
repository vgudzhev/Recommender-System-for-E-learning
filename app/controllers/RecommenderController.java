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
 * be secured and used for authenticated users.
 * 
 * @author VGudzhev
 *
 */
public class RecommenderController extends Controller {
	private static long USER_ID;
	private static DatasetType datasetType;
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
		
		try {
			list = RecommendationFactory.get(recommendationType, USER_ID,
					datasetType);
		} catch (Exception e) {
			return ok(recommendationStatus.render(UNEXPECTED_ERROR));
		}

		List<RecommendedItemTuple> recommendedItems = getRecommendedItems(list);
		if (recommendedItems == null) {
			return badRequest(recommendationStatus.render("No rating found"));
		}
		
		
		List<Double> ratings = new ArrayList<Double>();
		switch (datasetType) {
		case BOOKS:
			List<Book> books = new ArrayList<Book>();
			for (int i = 0; i < recommendedItems.size(); i++) {
				books.add(Book.findById(recommendedItems.get(i).getId()));
				ratings.add(recommendedItems.get(i).getRating());
			}

			return ok(listBookRecommendatons.render(books,
					recommendationType.getDescription(), ratings));
		case COURSES:
			List<Course> courses = new ArrayList<Course>();
			for (int i = 0; i < recommendedItems.size(); i++) {
				courses.add(Course.findByID(recommendedItems.get(i).getId()));
				ratings.add(recommendedItems.get(i).getRating());
			}
			return ok(listCourseRecommendations.render(courses,
					recommendationType.getDescription(), ratings));

		case ABSTRACT_ITEMS:
			List<AbstractItem> items = new ArrayList<AbstractItem>();
			for (int i = 0; i < recommendedItems.size(); i++) {
				items.add(AbstractItem.findByID(recommendedItems.get(i).getId()));
				ratings.add(recommendedItems.get(i).getRating());
			}

			return ok(listAbstractItemRecommendations.render(items,
					recommendationType.getDescription(), ratings));

		case VIDEOS:
			List<Video> videos = new ArrayList<Video>();
			for (int i = 0; i < recommendedItems.size(); i++) {
				videos.add(Video.findByID(recommendedItems.get(i).getId()));
				ratings.add(recommendedItems.get(i).getRating());
			}

			return ok(listVideoRecommendations.render(videos,
					recommendationType.getDescription(), ratings));

		default:
			return redirect("/404");
		}

	}

	private static List<RecommendedItemTuple> getRecommendedItems(List<RecommendedItem> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}

		List<RecommendedItemTuple> items = new ArrayList<RecommendedItemTuple>(list.size());
		for (RecommendedItem item : list) {
			long itemID = item.getItemID();
			float rating = item.getValue();
			items.add(RecommendedItemTuple.getInstance(itemID, rating));
		}
		return items;
	}
}