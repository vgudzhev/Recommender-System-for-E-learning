package recommendationSystem;

public enum RecommendationType {
		PEARSON_CORELLATION_USER("pcu", "User Based recommendation based on Pearson corellation between users"),
		PEARSON_CORELLATION_W_USER("pcwu", "User Based recommendation based on Pearson corellation between users with weighted score"),
		EUCLIDEAN_DISTANCE_USER("edu", "User Based recommendation based on Euclidean distance between users"),
		EUCLIDEAN_DISTANCE_W_USER("edwu", "User Based recommendation based on Euclidean distance between users with weighted score"),
		SPEARMAN_CORELLATION_USER("scu", "User Based recommendation based on Spearman corellation between users"),
		TANIMOTO_COEFFICIENT_USER("Tcu", "User Based recommendation based on Tanimoto coefficient between users"),
		PEARSON_CORELLATION_ITEM("pci", "Item Based recommendation based on Pearson corellation between items"),
		PEARSON_CORELLATION_W_ITEM("pcwi", "Item Based recommendation based on Pearson corellation between items with weighted score"),
		EUCLIDEAN_DISTANCE_ITEM("edi", "Item Based recommendation based on Euclidean distance between items"),
		EUCLIDEAN_DISTANCE_W_ITEM("edwi", "Item Based recommendation based on Euclidean distance between items with weighted score"),
		SPEARMAN_CORELLATION_ITEM("sci", "Item Based recommendation based on Spearman corellation between users");
		
		private String key;
		private String description;
		
		private RecommendationType(String val, String desc) {
			this.key = val;
			this.description = desc;
		}
		
		public static RecommendationType getType(String val) {
			for(RecommendationType type : RecommendationType.values()) {
				if(type.key.equalsIgnoreCase(val)) {
					return type;
				}
			}
			return null;
		}

		public String getKey() {
			return key;
		}

		public String getDescription() {
			return description;
		}
	}