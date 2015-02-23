package recommendationSystem.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import play.Logger;
import recommendationSystem.dataset.DatasetPath;
import recommendationSystem.dataset.DatasetType;


public class RatingSerializer {

	private static final String CSV_SEPARATOR = ",";

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
			return DatasetPath.itemsRatingPath();
		case BOOKS:
			return DatasetPath.booksRatingPath();
		case COURSES:
			return DatasetPath.coursesRatingPath();
		case VIDEOS:
			return DatasetPath.videoRatingPath();
		}
		return null;
	}
}
