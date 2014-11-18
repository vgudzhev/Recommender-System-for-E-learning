package recommendationSystem.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import play.Logger;
import recommendationSystem.dataset.DatasetType;


public class RatingSerializer {

	private static final String CSV_SEPARATOR = ",";
	private static final String ITEM_RATING = "data/rating/abstract-item-rating.csv";
	private static final String BOOK_RATING = "data/rating/books-rating.csv";
	private static final String COURSE_RATING = "data/rating/courses-rating.csv";
	private static final String VIDEO_RATING = "data/rating/video-rating.csv";

	public static void writeToCSV(long userID, long itemID, double rating, DatasetType datasetType) {
		String path = getPath(datasetType);
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(path,true), "UTF-8"));
				StringBuffer oneLine = new StringBuffer();
				oneLine.append(userID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(itemID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(rating);
				bw.write(oneLine.toString());
				bw.newLine();
			
			bw.flush();
			bw.close();
		} catch (Exception e) {
			Logger.info("Unable to save rating." + e.getMessage());
		} 
	}

	private static String getPath(DatasetType datasetType) {
		switch (datasetType) {
		case ABSTRACT_ITEMS:
			return ITEM_RATING;
		case BOOKS:
			return BOOK_RATING;
		case COURSES:
			return COURSE_RATING;
		case VIDEOS:
			return VIDEO_RATING;
		}
		return null;
	}
}
