package recommendationSystem.dataset;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import models.Course;
import models.rating.CourseRating;
import play.Logger;
import au.com.bytecode.opencsv.CSVReader;

public class CoursesDataset {
	private static final String COURSES = "data/initial-data/courses.csv";
	private static final String RATING = "data/rating/courses-rating.csv";

	public static void init() {
		try {
			importCourses(COURSES);
			importRating(RATING);
		} catch (FileNotFoundException e) {
			Logger.info("Cannot import Courses CSV files");
		} catch (IOException ex) {
			Logger.info("Cannot import Courses CSV files");
		}
	}

	private static void importCourses(String path) throws FileNotFoundException {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				long courseID = Long.parseLong(nextLine[0]);
				String title = nextLine[1];
				String description = nextLine[2];

				Course course = new Course.CourseBuilder().title(title)
						.id(courseID).description(description).build();

				course.save();

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void importRating(String path) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			long userID = Long.parseLong(nextLine[0]);
			long courseID = Long.parseLong(nextLine[1]);
			double rating = Double.parseDouble(nextLine[2]);

			CourseRating courseRating = new CourseRating.Builder().userID(userID).courseID(courseID).rating(rating).build();

			courseRating.save();
		}
		reader.close();
	}
}
