package recommendationSystem.engine;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import recommendationSystem.dataset.DatasetType;

public class UserBasedEngine extends RecommenderEngine {
	private static int userNeighborhood =10;
	private DataModel model;
	public UserBasedEngine(DatasetType datasetType, long userId) {
		 model = getDataModel(datasetType);
		 super.userId = userId;
	}
	
	public List<RecommendedItem> getPearsonCorellation()
			throws TasteException, IOException {
		UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new  NearestNUserNeighborhood(NEAREST_K, userSimilarity, model);
//		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, userSimilarity, model);
		GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, userSimilarity);
		
		List<RecommendedItem> recommendations = recommender.recommend(userId, MAX_RECOMMENDATIONS);
		
		return recommendations;
	}

	public List<RecommendedItem> getWeightedPearsonCorellation()
			throws TasteException, IOException {
		UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model, Weighting.WEIGHTED);
		UserNeighborhood neighborhood =
				new NearestNUserNeighborhood(userNeighborhood, userSimilarity, model);
//		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, userSimilarity, model);
		GenericUserBasedRecommender recommender =
				new GenericUserBasedRecommender(model, neighborhood, userSimilarity);				
		List<RecommendedItem> recommendations = recommender.recommend(
				userId, MAX_RECOMMENDATIONS);
		return recommendations;
	}

	public List<RecommendedItem> getEuclideanDistance()
			throws TasteException, IOException {
		UserSimilarity userSimilarity = new EuclideanDistanceSimilarity(model);
		UserNeighborhood neighborhood =
				new NearestNUserNeighborhood(userNeighborhood, userSimilarity, model);
		GenericUserBasedRecommender recommender =
				new GenericUserBasedRecommender(model, neighborhood, userSimilarity);				
		List<RecommendedItem> recommendations = recommender.recommend(
				userId, MAX_RECOMMENDATIONS);
		return recommendations;
	}
	
	public List<RecommendedItem> getWeightedEuclideanDistance()
			throws TasteException, IOException {
		UserSimilarity userSimilarity = new EuclideanDistanceSimilarity(model, Weighting.WEIGHTED);
		UserNeighborhood neighborhood =
				new NearestNUserNeighborhood(userNeighborhood, userSimilarity, model);
		GenericUserBasedRecommender recommender =
				new GenericUserBasedRecommender(model, neighborhood, userSimilarity);				
		List<RecommendedItem> recommendations = recommender.recommend(
				userId, MAX_RECOMMENDATIONS);
		return recommendations;
	}

	public List<RecommendedItem> getSpearmanCorellation()
			throws TasteException, IOException {
		UserSimilarity userSimilarity = new SpearmanCorrelationSimilarity(model);
		UserNeighborhood neighborhood =
				new NearestNUserNeighborhood(userNeighborhood, userSimilarity, model);
		GenericUserBasedRecommender recommender =
				new GenericUserBasedRecommender(model, neighborhood, userSimilarity);				
		List<RecommendedItem> recommendations = recommender.recommend(
				userId, MAX_RECOMMENDATIONS);
		return recommendations;
	}
	
	public List<RecommendedItem> getTanimotoCoefficient()
			throws TasteException, IOException {
		UserSimilarity userSimilarity = new TanimotoCoefficientSimilarity(model);
		UserNeighborhood neighborhood =
				new NearestNUserNeighborhood(userNeighborhood, userSimilarity, model);
		GenericUserBasedRecommender recommender =
				new GenericUserBasedRecommender(model, neighborhood, userSimilarity);				
		List<RecommendedItem> recommendations = recommender.recommend(
				userId, MAX_RECOMMENDATIONS);
		return recommendations;
	}
}
