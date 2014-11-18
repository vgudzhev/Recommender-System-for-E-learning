package recommendationSystem.dataset;

import java.io.FileReader;
import java.io.IOException;

import play.Logger;
import models.AbstractItem;
import models.rating.AbstractItemRating;
import au.com.bytecode.opencsv.CSVReader;

public class ItemsDataset {
	private static final String ITEMS_RATING = "data/rating/abstract-item-rating.csv";
	private static final String ITEMS = "data/initial-data/abstract-items.csv";

	public static void init() {
		try {
			importItems(ITEMS);
			importItemsRating(ITEMS_RATING);
		} catch (IOException e) {
			Logger.info("Cannot Abstract items import CSV files");
			
		}
	}

	private static void importItems(String path) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			Long id = Long.parseLong(nextLine[0]);
			String name = nextLine[1];
			String topic = nextLine[2];

			AbstractItem abstractItem = new AbstractItem();
			abstractItem.id = id;
			abstractItem.title = name;
			abstractItem.description = topic;

			abstractItem.save();
		}
		reader.close();
	}


	private static void importItemsRating(String path) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			long userID = Long.parseLong(nextLine[0]);
			long itemID = Long.parseLong(nextLine[1]);
			double rating = Double.parseDouble(nextLine[2]);

			AbstractItemRating abstractItemRating = new AbstractItemRating.Builder().userID(userID).itemID(itemID).rating(rating).build();
			abstractItemRating.save();
		}
		reader.close();
	}
}
