package recommendationSystem.dataset.ImportUtils;

public class DatasetImporter {
	
	public static CSVImporter getCSVImporter(){
		return new CSVImporter();
	}
	
	public static JSONImporter getJSONImporter(){
		return new JSONImporter();
	}
}
