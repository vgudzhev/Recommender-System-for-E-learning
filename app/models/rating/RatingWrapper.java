package models.rating;

import java.util.ArrayList;
import java.util.List;

public class RatingWrapper {
	public long userID;
	public long itemID;
	public double rating;
		
	public RatingWrapper(long userID, long itemID, double rating) {
		this.userID = userID;
		this.itemID = itemID;
		this.rating = rating;
	}
	
	public static RatingWrapper getRatingWrapper(long userID, long itemID, double rating) {
		return new RatingWrapper(userID, itemID, rating);
	}

	public static List<RatingWrapper> wrapVideos(List<VideoRating> videos){
		List<RatingWrapper> allItems = new ArrayList<RatingWrapper>();
		for (VideoRating videoRating : videos) {
			allItems.add(RatingWrapper.getRatingWrapper(videoRating.userID, videoRating.userID, videoRating.rating));
		}
		
		return allItems;
	}
	
	public static List<RatingWrapper> wrapCourses(List<CourseRating> courses){
		List<RatingWrapper> allItems = new ArrayList<RatingWrapper>();
		for (CourseRating courseRating: courses) {
			allItems.add(RatingWrapper.getRatingWrapper(courseRating.userID, courseRating.courseID, courseRating.rating));
		}
		
		return allItems;
	}
	
	public static List<RatingWrapper> wrapBooks(List<BookRating> books){
		List<RatingWrapper> allItems = new ArrayList<RatingWrapper>();
		for (BookRating bookRating : books) {
			allItems.add(RatingWrapper.getRatingWrapper(bookRating.userID, bookRating.bookID, bookRating.rating));
		}
		
		return allItems;
	}
	
	public static List<RatingWrapper> wrapAbstractItems(List<AbstractItemRating> items){
		List<RatingWrapper> allItems = new ArrayList<RatingWrapper>();
		for (AbstractItemRating itemRating : items) {
			allItems.add(RatingWrapper.getRatingWrapper(itemRating.userID, itemRating.itemID, itemRating.rating));
		}
		
		return allItems;
	}
	
	
}
