package recommendationSystem;

/**
 * {@link RecommendedItemTuple} is used to store the tuple recommended item id , and coeffecient
 *
 */
public class RecommendedItemTuple {
	private long id;
	private double rating;
	
	public RecommendedItemTuple(long id, double rating) {
		this.id = id;
		this.rating = rating;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "RecommendedItemTuple [id=" + id + ", rating=" + rating + "]";
	}
	
	public static RecommendedItemTuple getInstance(long id, double rating){
		RecommendedItemTuple recItem = new RecommendedItemTuple(id, rating);
		return recItem;
	}
	
	
	
}
