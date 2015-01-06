package recommendationSystem.dataset.ExportUtils;

public class DatasetExporter {

	public static CSVExporter getCSVExporter() {
		return new CSVExporter();
	}
	
	public static JSONExporter getJSONExporter(){
		return new JSONExporter();
	}

}
