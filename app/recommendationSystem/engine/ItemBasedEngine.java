package recommendationSystem.engine;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import recommendationSystem.dataset.DatasetType;

public class ItemBasedEngine extends RecommenderEngine {
	private DataModel model;

	public ItemBasedEngine(DatasetType datasetType, long userId) {
		model = getDataModel(datasetType);
		super.userId = userId;
	}

	public List<RecommendedItem> getPearsonCorellation() throws TasteException,
			IOException {
		ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(model);
		GenericItemBasedRecommender itemBasedRecommender = new GenericItemBasedRecommender(
				model, itemSimilarity);

		List<RecommendedItem> recommendations = itemBasedRecommender.recommend(
				userId, MAX_RECOMMENDATIONS);
		return recommendations;
	}

	public List<RecommendedItem> getWeightedPearsonCorellation()
			throws TasteException, IOException {
		ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(model,
				Weighting.WEIGHTED);
		GenericItemBasedRecommender itemBasedRecommender = new GenericItemBasedRecommender(
				model, itemSimilarity);

		List<RecommendedItem> recommendations = itemBasedRecommender.recommend(
				userId, MAX_RECOMMENDATIONS);
		return recommendations;
	}

	public List<RecommendedItem> getEuclideanDistance() throws TasteException,
			IOException {
		ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(model);
		GenericItemBasedRecommender itemBasedRecommender = new GenericItemBasedRecommender(
				model, itemSimilarity);

		List<RecommendedItem> recommendations = itemBasedRecommender.recommend(
				userId, MAX_RECOMMENDATIONS);
		return recommendations;
	}

	public List<RecommendedItem> getWeightedEuclideanDistance()
			throws TasteException, IOException {
		ItemSimilarity itemSimilarity = new EuclideanDistanceSimilarity(model,
				Weighting.WEIGHTED);
		GenericItemBasedRecommender itemBasedRecommender = new GenericItemBasedRecommender(
				model, itemSimilarity);

		List<RecommendedItem> recommendations = itemBasedRecommender.recommend(
				userId, MAX_RECOMMENDATIONS);
		return recommendations;
	}
	
	public List<RecommendedItem> getTanimotoCoefficient()
			throws TasteException, IOException {
		ItemSimilarity itemSimilarity = new TanimotoCoefficientSimilarity(model);
		GenericItemBasedRecommender recommender =
				new GenericItemBasedRecommender(model, itemSimilarity);				
		List<RecommendedItem> recommendations = recommender.recommend(
				userId, MAX_RECOMMENDATIONS);
		return recommendations;
	}
}
