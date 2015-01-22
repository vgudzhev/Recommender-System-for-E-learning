package recommendationSystem.engine;

import java.io.IOException;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import play.Logger;
import play.Play;
import recommendationSystem.dataset.DatasetPath;
import recommendationSystem.dataset.DatasetType;

public class RecommenderEngine {

	protected final int NEAREST_K = 5;
	protected final int MAX_RECOMMENDATIONS = 20;
	long userId;

	protected DataModel getDataModel(DatasetType datasetType) {
		try {
			switch (datasetType) {
			case BOOKS:
				return new FileDataModel(Play.application().getFile(DatasetPath.booksRatingPath()));
			case VIDEOS:
				return new FileDataModel(Play.application().getFile(DatasetPath.videoRatingPath()));
			case ABSTRACT_ITEMS:
				return new FileDataModel(Play.application().getFile(DatasetPath.abstractItemsPath()));
			case COURSES:
				return new FileDataModel(Play.application().getFile(DatasetPath.coursesRatingPath()));
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
