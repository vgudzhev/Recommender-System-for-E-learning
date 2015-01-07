package recommendationSystem.dataset;

public final class DatasetPath {
	private static final String DATASET_FOLDER = "data/";
	private static final String BOOKS_RATING = "books-rating.csv";
	private static final String BOOKS = "books.csv";
	private static final String COURSES = "courses.csv";
	private static final String COURSES_RATING = "courses-rating.csv";
	private static final String ITEMS_RATING = "abstract-item-rating.csv";
	private static final String ITEMS = "abstract-items.csv";
	private static final String USERS = "users.csv";
	private static final String VIDEO_RATING = "video-rating.csv";
	private static final String VIDEO = "videos.csv";
	
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
