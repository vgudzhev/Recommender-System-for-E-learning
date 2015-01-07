package recommendationSystem.dataset.ImportUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import recommendationSystem.dataset.DatasetPath;
import models.AbstractItem;
import models.Book;
import models.Course;
import models.User;
import models.Video;
import models.rating.AbstractItemRating;
import models.rating.BookRating;
import models.rating.CourseRating;
import models.rating.VideoRating;
import au.com.bytecode.opencsv.CSVReader;

public class CSVImporter {
	private static String CSV_SEPARATOR = ",";

	public void importUsers(File file) throws NumberFormatException,
			IOException {
		CSVReader reader = new CSVReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.usersPath(), false), "UTF-8"));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				Long userID = Long.parseLong(nextLine[0]);
				String email = nextLine[1];
				String password = nextLine[2];
				String name = email.substring(0, email.indexOf("@"));

				StringBuffer oneLine = new StringBuffer();
				oneLine.append(userID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(email);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(name);
				bw.write(oneLine.toString());
				bw.newLine();

				User user = new User.UserBuilder().id(userID).email(email)
						.password(password).name(name).build();
				user.save();
			}
		} finally {
			bw.flush();
			bw.close();
		}
	}

	public void importBooks(File file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.booksPath(), false), "UTF-8"));
		String[] nextLine;
		StringBuffer oneLine = new StringBuffer();
		try {
			while ((nextLine = reader.readNext()) != null) {
				Long id = Long.parseLong(nextLine[0]);
				String title = nextLine[1];
				String author = nextLine[2];
				String year = nextLine[3];
				String publisher = nextLine[4];
				String imageUrl = nextLine[5];

				oneLine.append(id);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(title);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(author);
				bw.write(oneLine.toString());
				bw.newLine();
				oneLine = null;

				Book book = new Book.BookBuilder().id(id).title(title)
						.author(author).year(year).publisher(publisher)
						.image(imageUrl).build();
				book.save();
			}
		} finally {
			reader.close();
			bw.flush();
			bw.close();
		}
	}

	public void importBookRating(File file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.booksRatingPath(), false),
				"UTF-8"));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				long userID = Long.parseLong(nextLine[0]);
				long bookID = Long.parseLong(nextLine[1]);
				double rating = Double.parseDouble(nextLine[2]);

				StringBuffer oneLine = new StringBuffer();
				oneLine.append(userID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(bookID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(rating);
				bw.write(oneLine.toString());
				bw.newLine();

				BookRating bookRating = new BookRating.Builder().userID(userID)
						.bookID(bookID).rating(rating).build();
				bookRating.save();
			}
		} finally {
			reader.close();
			bw.flush();
			bw.close();
		}
	}

	public void importCourses(File file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(
						DatasetPath.coursesPath(), false), "UTF-8"));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				long courseID = Long.parseLong(nextLine[0]);
				String title = nextLine[1];
				String description = nextLine[2];

				StringBuffer oneLine = new StringBuffer();
				oneLine.append(courseID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(title);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(description);
				bw.write(oneLine.toString());
				bw.newLine();

				Course course = new Course.CourseBuilder().title(title)
						.id(courseID).description(description).build();

				course.save();
			}
		} finally {
			reader.close();
			bw.flush();
			bw.close();
		}
	}

	public void importCourseRating(File file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.coursesRatingPath(), false),
				"UTF-8"));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				long userID = Long.parseLong(nextLine[0]);
				long courseID = Long.parseLong(nextLine[1]);
				double rating = Double.parseDouble(nextLine[2]);

				StringBuffer oneLine = new StringBuffer();
				oneLine.append(userID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(courseID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(rating);
				bw.write(oneLine.toString());
				bw.newLine();

				CourseRating courseRating = new CourseRating.Builder()
						.userID(userID).courseID(courseID).rating(rating)
						.build();

				courseRating.save();
			}
		} finally {
			reader.close();
			bw.flush();
			bw.close();
		}
	}

	public void importItems(File file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.abstractItemsPath(), false),
				"UTF-8"));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				Long id = Long.parseLong(nextLine[0]);
				String name = nextLine[1];
				String topic = nextLine[2];

				StringBuffer oneLine = new StringBuffer();
				oneLine.append(id);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(name);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(topic);
				bw.write(oneLine.toString());
				bw.newLine();

				AbstractItem abstractItem = new AbstractItem();
				abstractItem.id = id;
				abstractItem.title = name;
				abstractItem.description = topic;

				abstractItem.save();
			}
		} finally {
			reader.close();
			bw.flush();
			bw.close();
		}
	}

	public void importItemsRating(File file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.itemsRatingPath(), false),
				"UTF-8"));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				long userID = Long.parseLong(nextLine[0]);
				long itemID = Long.parseLong(nextLine[1]);
				double rating = Double.parseDouble(nextLine[2]);

				StringBuffer oneLine = new StringBuffer();
				oneLine.append(userID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(itemID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(rating);
				bw.write(oneLine.toString());
				bw.newLine();

				AbstractItemRating abstractItemRating = new AbstractItemRating.Builder()
						.userID(userID).itemID(itemID).rating(rating).build();
				abstractItemRating.save();
			}
		} finally {
			reader.close();
			bw.flush();
			bw.close();
		}
	}

	public void importVideo(File file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.videoPath(), false), "UTF-8"));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				String name = nextLine[0];
				String url = nextLine[1];
				String description = nextLine[2];

				StringBuffer oneLine = new StringBuffer();
				oneLine.append(name);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(url);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(description);
				bw.write(oneLine.toString());
				bw.newLine();

				Video video = new Video.VideoBuilder().title(name)
						.description(description).url(url).build();

				video.save();
			}
		} finally {
			reader.close();
			bw.flush();
			bw.close();
		}
	}

	public void importVideoRating(File file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.videoRatingPath(), false),
				"UTF-8"));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				long userID = Long.parseLong(nextLine[0]);
				long videoID = Long.parseLong(nextLine[1]);
				double rating = Double.parseDouble(nextLine[2]);

				StringBuffer oneLine = new StringBuffer();
				oneLine.append(userID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(videoID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(rating);
				bw.write(oneLine.toString());
				bw.newLine();

				VideoRating videoRating = new VideoRating.Builder()
						.userID(userID).videoID(videoID).rating(rating).build();

				videoRating.save();
			}
		} finally {
			reader.close();
			bw.flush();
			bw.close();
		}
	}
}
