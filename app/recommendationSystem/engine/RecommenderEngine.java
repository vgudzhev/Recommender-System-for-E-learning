package recommendationSystem.engine;

import java.io.IOException;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import play.Logger;
import play.Play;
import recommendationSystem.dataset.DatasetType;

public class RecommenderEngine {
	public static final String BOOKS_RATING = "data/rating/books-rating.csv";
	public static final String VIDEO_RATING = "data/rating/video-rating.csv";
	public static final String ABSTRACT_ITEMS_RATING = "data/rating/abstract-item-rating.csv";
	public static final String COURSES_RATING = "data/rating/courses-rating.csv";

	protected final int NEAREST_K = 5;
	protected final int MAX_RECOMMENDATIONS = 20;
	long userId;

	protected DataModel getDataModel(DatasetType datasetType) {
		try {
			switch (datasetType) {
			case BOOKS:
				return new FileDataModel(Play.application().getFile(BOOKS_RATING));
			case VIDEOS:
				return new FileDataModel(Play.application().getFile(VIDEO_RATING));
			case ABSTRACT_ITEMS:
				return new FileDataModel(Play.application().getFile(ABSTRACT_ITEMS_RATING));
			case COURSES:
				return new FileDataModel(Play.application().getFile(COURSES_RATING));
			default:
				break;
			}
			
		} catch (IOException e) {
			Logger.info("Unable to read rating.csv");
			System.out.println("Unable to read rating.csv");
			return null;
		}
		return null;
	}

}
