package recommendationSystem.dataset;

public final class DatasetPath {
	public static final String DATASET_FOLDER = "data/";
	public static final String BOOKS_RATING = "books-rating.csv";
	public static final String BOOKS = "books.csv";
	public static final String COURSES = "courses.csv";
	public static final String COURSES_RATING = "courses-rating.csv";
	public static final String ITEMS_RATING = "abstract-item-rating.csv";
	public static final String ITEMS = "abstract-items.csv";
	public static final String USERS = "users.csv";
	public static final String VIDEO_RATING = "video-rating.csv";
	public static final String VIDEO = "videos.csv";
	
	public static final String DEMO_DATASET = DATASET_FOLDER + "demo/";
	public static final String NORMAL_DATASET = DATASET_FOLDER + "normal/";
	public static final String LARGE_DATASET  = DATASET_FOLDER + "large/";

	
	public static String booksRatingPath() {
		return DATASET_FOLDER + BOOKS_RATING;
	}
	
	public static String booksPath() {
		return DATASET_FOLDER + BOOKS;
	}
	
	public static String coursesPath() {
		return DATASET_FOLDER + COURSES;
	}
	
	public static String coursesRatingPath() {
		return DATASET_FOLDER + COURSES_RATING;
	}
	
	public static String itemsRatingPath() {
		return DATASET_FOLDER + ITEMS_RATING;
	}
	
	public static String abstractItemsPath() {
		return DATASET_FOLDER + ITEMS;
	}
	
	public static String usersPath() {
		return DATASET_FOLDER + USERS;
	}
	
	public static String videoRatingPath() {
		return DATASET_FOLDER + VIDEO_RATING;
	}
	
	public static String videoPath() {
		return DATASET_FOLDER + VIDEO;
	}
}
