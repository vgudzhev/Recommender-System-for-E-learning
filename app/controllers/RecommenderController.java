package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Book;
import models.Course;
import models.AbstractItem;
import models.User;
import models.Video;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import recommendationSystem.RecommendationFactory;
import recommendationSystem.RecommendationType;
import recommendationSystem.dataset.DatasetType;
import views.html.recommendations.*;


public class RecommenderController extends Controller {
	private static long USER_ID;
	private static DatasetType datasetType;
	
	@Security.Authenticated(SecuredController.class)
	public static Result info(String type) {
			USER_ID = User.findByEmail(session("user")).userID;
			if (type.equals("books")) {
				datasetType = DatasetType.BOOKS;
				
			}else if (type.equals("videos")) {
				datasetType = DatasetType.VIDEOS;
				
			}else if (type.equals("courses")) {
				datasetType = DatasetType.COURSES;
				
			}else if (type.equals("items")) {
				datasetType = DatasetType.ABSTRACT_ITEMS;
			}
		
		return ok(recommendationsInfo.render());
	}
	
	public static Result infoPage(){
		return ok(recommendationsInfo.render());
	}
	
	@Security.Authenticated(SecuredController.class)
	public static Result getRecommendations(String type) throws Exception{
		List<RecommendedItem> list = null;
		RecommendationType recommendationType = RecommendationType.getType(type);
		list = RecommendationFactory.get(recommendationType, USER_ID, datasetType);
		
		if (datasetType == null || list ==null) {
			redirect("/404");
		}
		List<Long> recommendedItemsIDs = getRecommendedItemsIDs(list);
		
		switch (datasetType) {
		case BOOKS:	
			List<Book> books = new ArrayList<Book>();
			for (int i = 0; i < recommendedItemsIDs.size(); i++) {
				books.add(Book.findById(recommendedItemsIDs.get(i)));
			}	
			return ok(listBookRecommendatons.render(books, recommendationType.getDescription()));
			
		case COURSES:
			List<Course> courses = new ArrayList<Course>();
			for (int i = 0; i < recommendedItemsIDs.size(); i++) {
				courses.add(Course.findByID(recommendedItemsIDs.get(i)));
			}	
			return ok(listCourseRecommendations.render(courses, recommendationType.getDescription()));
			
		case ABSTRACT_ITEMS: 
			List<AbstractItem> items = new ArrayList<AbstractItem>();
			for (int i = 0; i < recommendedItemsIDs.size(); i++) {
				items.add(AbstractItem.findByID(recommendedItemsIDs.get(i)));
			}
			
			return ok(listAbstractItemRecommendations.render(items, recommendationType.getDescription()));
			
		case VIDEOS:
			List<Video> videos = new ArrayList<Video>();
			for (int i = 0; i < recommendedItemsIDs.size(); i++) {
				videos.add(Video.findByID(recommendedItemsIDs.get(i)));
			}
			
			return ok(listVideoRecommendations.render(videos, recommendationType.getDescription()));			
		default:
			return redirect("/404");
		}
			
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