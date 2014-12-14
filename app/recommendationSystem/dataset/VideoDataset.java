package recommendationSystem.dataset;

import java.io.FileReader;
import java.io.IOException;

import play.Logger;
import models.Video;
import models.rating.VideoRating;
import au.com.bytecode.opencsv.CSVReader;

public class VideoDataset {
	private static final String VIDEO_RATING = "data/rating/video-rating.csv";
	private static final String VIDEO = "data/initial-data/videos.csv";

	public static void init() {
		try {
			importVideo(VIDEO);
			importVideoRating(VIDEO_RATING);
		} catch (IOException e) {
			Logger.info("Cannot import Video CSV files" + e.getMessage());
			
		}
	}

	private static void importVideo(String path) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			String name = nextLine[0];
			String url = nextLine[1];
			String description = nextLine[2];

			Video video = new Video.VideoBuilder().title(name).description(description).url(url).build();

			video.save();
		}
		reader.close();
	}


	private static void importVideoRating(String path) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			long userID = Long.parseLong(nextLine[0]);
			long videoID = Long.parseLong(nextLine[1]);
			double rating = Double.parseDouble(nextLine[2]);

			VideoRating videoRating = new VideoRating.Builder().userID(userID).videoID(videoID).rating(rating).build();

			videoRating.save();
		}
		reader.close();
	}
}
