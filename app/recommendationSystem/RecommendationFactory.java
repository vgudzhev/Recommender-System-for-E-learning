package recommendationSystem;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import recommendationSystem.dataset.DatasetType;
import recommendationSystem.engine.ItemBasedEngine;
import recommendationSystem.engine.UserBasedEngine;

public class RecommendationFactory {
	public static List<RecommendedItem> get(RecommendationType recommendationType, long userId, DatasetType datasetType)
			throws TasteException, IOException{
		
		ItemBasedEngine itemBasedEngine = new ItemBasedEngine(datasetType, userId);
		UserBasedEngine userBasedEngine = new UserBasedEngine(datasetType, userId);
		switch (recommendationType) {
		
		case PEARSON_CORELLATION_USER:
			return userBasedEngine.getPearsonCorellation();
			
		case EUCLIDEAN_DISTANCE_ITEM:
			return itemBasedEngine.getEuclideanDistance();
			
		case EUCLIDEAN_DISTANCE_W_ITEM:
			return itemBasedEngine.getWeightedEuclideanDistance();
			
		case PEARSON_CORELLATION_ITEM:
			return itemBasedEngine.getPearsonCorellation();
			
		case PEARSON_CORELLATION_W_ITEM:
			return itemBasedEngine.getWeightedPearsonCorellation();
			
		case EUCLIDEAN_DISTANCE_USER:
			return userBasedEngine.getEuclideanDistance();
			
		case EUCLIDEAN_DISTANCE_W_USER:
			return userBasedEngine.getWeightedEuclideanDistance();
			
		case PEARSON_CORELLATION_W_USER:
			return  userBasedEngine.getWeightedPearsonCorellation();
			
		case SPEARMAN_CORELLATION_USER:
			return  userBasedEngine.getSpearmanCorellation();
			
		case TANIMOTO_COEFFICIENT_USER:
			return userBasedEngine.getTanimotoCoefficient();
		
		case TANIMOTO_COEFFICIENT_ITEM:
			return itemBasedEngine.getTanimotoCoefficient();
			
		default:
			return null;
		}
	}
}
