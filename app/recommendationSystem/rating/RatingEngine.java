package recommendationSystem.rating;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import recommendationSystem.dataset.DatasetType;
import recommendationSystem.utils.RatingSerializer;
import models.AbstractItem;
import models.Book;
import models.Course;
import models.Video;
import models.rating.AbstractItemRating;
import models.rating.BookRating;
import models.rating.CourseRating;
import models.rating.VideoRating;

public class RatingEngine {
	private long userID;

	public RatingEngine(long userID) {
		this.userID = userID;
	}

	public long getAbstractItemForRate() {
		List<AbstractItemRating> ratedItems = AbstractItemRating
				.findByUserID(userID);
		List<AbstractItem> allItems = AbstractItem.find.all();
		List<Long> ratedItemsIDs = new ArrayList<Long>();
		for (int i = 0; i < ratedItems.size(); i++) {
			ratedItemsIDs.add(ratedItems.get(i).itemID);
		}

		long idForRate = -1;
		for (AbstractItem item : allItems) {
			if (!ratedItemsIDs.contains(item.id)) {
				idForRate = item.id;
				break;
			}
		}
		return idForRate;
	}

	public long getCoursesForRate() {
		List<CourseRating> ratedItems = CourseRating.findByUserID(userID);
		List<Course> allItems = Course.find.all();
		List<Long> ratedItemsIDs = new ArrayList<Long>();

		for (int i = 0; i < ratedItems.size(); i++) {
			ratedItemsIDs.add(ratedItems.get(i).courseID);
		}

		long idForRate = -1;
		for (Course item : allItems) {
			if (!ratedItemsIDs.contains(item.id)) {
				idForRate = item.id;
				break;
			}
		}
		return idForRate;
	}

	public long getVideoForRate() {
		List<VideoRating> ratedItems = VideoRating.findByUserID(userID);
		List<Video> allItems = Video.find.all();
		List<Long> ratedItemsIDs = new ArrayList<Long>();

		for (int i = 0; i < ratedItems.size(); i++) {
			ratedItemsIDs.add(ratedItems.get(i).videoID);
		}

		long idForRate = -1;
		for (Video item : allItems) {
			if (!ratedItemsIDs.contains(item.id)) {
				idForRate = item.id;
				break;
			}
		}
		return idForRate;
	}

	public long getBookForRate() {
		List<BookRating> ratedItems = BookRating.findByUserID(userID);
		List<Book> allItems = Book.find.all();
		List<Long> ratedItemsIDs = new ArrayList<Long>();

		for (int i = 0; i < ratedItems.size(); i++) {
			ratedItemsIDs.add(ratedItems.get(i).bookID);
		}

		long idForRate = -1;
		for (Book item : allItems) {
			if (!ratedItemsIDs.contains(item.id)) {
				idForRate = item.id;
				break;
			}
		}
		return idForRate;
	}

	public static void rate(long userID, long itemID, double rating,
			DatasetType datasetType) {
		RatingSerializer.writeToCSV(userID, itemID, rating, datasetType);
		switch (datasetType) {
		case ABSTRACT_ITEMS:
			AbstractItemRating itemRating = new AbstractItemRating.Builder()
											.userID(userID)
											.itemID(itemID)
											.rating(rating)
											.build();
			itemRating.save();
			break;
		case BOOKS:
			BookRating bookRating = new BookRating.Builder()
									.userID(userID)
									.bookID(itemID)
									.rating(rating)
									.build();
			bookRating.save();
			break;
		case COURSES:
			CourseRating courseRating = new CourseRating.Builder()
										.userID(userID)
										.courseID(itemID)
										.rating(rating)
										.build();
			courseRating.save();
			break;
		case VIDEOS:
			VideoRating videoRating = new VideoRating.Builder()
										.userID(userID)
										.videoID(itemID)
										.rating(rating)
										.build();
			videoRating.save();
		default:
			Logger.info("Cannot rate null item");
			break;
		}
	}
}