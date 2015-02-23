package recommendationSystem.dataset.InsertionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import models.AbstractItem;
import models.Book;
import models.Course;
import models.User;
import models.Video;
import recommendationSystem.dataset.DatasetPath;

public class DatasetInserter {
	private static String CSV_SEPARATOR = ",";
	private static String NULL_STRING= "null";

	public static void insertSingleUser(User user) throws NumberFormatException,
			IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.usersPath(), true), "UTF-8"));
		try {
				StringBuffer oneLine = new StringBuffer();
				oneLine.append(user.userID == null ? NULL_STRING : user.userID);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(user.email == null ? NULL_STRING : user.email);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(user.name == null ? NULL_STRING : user.name);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(user.age == 0 ? 0 : user.age);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(user.gender == null ? NULL_STRING : user.gender);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(user.profession == null ? NULL_STRING : user.gender);
				bw.write(oneLine.toString());
				bw.newLine();
		} finally {
			bw.flush();
			bw.close();
		}
	}

	public static void insertSingleBook(Book book) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.booksPath(), true), "UTF-8"));
		StringBuffer oneLine = new StringBuffer();
		try {
				oneLine.append(book.id);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(book.title == null ? NULL_STRING : book.title );
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(book.author== null ? NULL_STRING :book.author);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(book.year== null ? NULL_STRING : book.year);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(book.publisher == null ? NULL_STRING :book.publisher);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(book.img== null ? NULL_STRING : book.img);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(book.shortDescription == null ? NULL_STRING :book.shortDescription);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(book.description == null ? NULL_STRING :book.description);
				bw.write(oneLine.toString());
				bw.newLine();
		} finally {
			bw.flush();
			bw.close();
		}
	}

	public static void importBookRating(File file) throws IOException {
		 // TODO importBookRating
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
//				new FileOutputStream(DatasetPath.booksRatingPath(), true),
//				"UTF-8"));
//		String[] nextLine;
//		try {
//
//				StringBuffer oneLine = new StringBuffer();
//				oneLine.append(userID);
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(bookID);
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(rating);
//				bw.write(oneLine.toString());
//				bw.newLine();
//
//		} finally {
//			 
//			bw.flush();
//			bw.close();
//		}
	}

	public static void insertSingleCourse(Course course) throws IOException {		 
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(
						DatasetPath.coursesPath(), true), "UTF-8"));
		try {
				StringBuffer oneLine = new StringBuffer();
				oneLine.append(course.id);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(course.title == null ? NULL_STRING : course.title);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(course.url == null ? NULL_STRING : course.url);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(course.img == null ? NULL_STRING : course.img);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(course.shortDescription == null ? NULL_STRING :course.shortDescription);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(course.description == null ? NULL_STRING : course.description);
				bw.write(oneLine.toString());
				bw.newLine();
		} finally {	 
			bw.flush();
			bw.close();
		}
	}

	public static void importCourseRating(File file) throws IOException {
		 // TODO importCourseRating
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
//				new FileOutputStream(DatasetPath.coursesRatingPath(), true),
//				"UTF-8"));
//		String[] nextLine;
//		try {
//
//
//				StringBuffer oneLine = new StringBuffer();
//				oneLine.append(userID);
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(courseID);
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(rating);
//				bw.write(oneLine.toString());
//				bw.newLine();
//
//			
//		} finally {
//			 
//			bw.flush();
//			bw.close();
//		}
	}

	public static void insertSingleItem(AbstractItem item) throws IOException {
		 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.abstractItemsPath(), true),
				"UTF-8"));
		String[] nextLine;
		try {
			StringBuffer oneLine = new StringBuffer();
			oneLine.append(item.id);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(item.title == null ? NULL_STRING :item.title);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(item.shortDescription == null ? NULL_STRING :item.shortDescription);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(item.description == null ? NULL_STRING :item.description);
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(item.img == null ? NULL_STRING :item.img);
			bw.write(oneLine.toString());
			bw.newLine();	
		} finally { 
			bw.flush();
			bw.close();
		}
	}

	public static void importItemsRating(File file) throws IOException {
		 //TODO
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
//				new FileOutputStream(DatasetPath.itemsRatingPath(), true),
//				"UTF-8"));
//		String[] nextLine;
//		try {
//			 
//				long userID = Long.parseLong(nextLine[0]);
//				long itemID = Long.parseLong(nextLine[1]);
//				double rating = Double.parseDouble(nextLine[2]);
//
//				StringBuffer oneLine = new StringBuffer();
//				oneLine.append(userID);
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(itemID);
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(rating);
//				bw.write(oneLine.toString());
//				bw.newLine();
//
//			
//			
//		} finally {
//			 
//			bw.flush();
//			bw.close();
//		}
	}

	public static void insertSingleVideo(Video video) throws IOException {
		 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(DatasetPath.videoPath(), true), "UTF-8"));
		String[] nextLine;
		try {
				StringBuffer oneLine = new StringBuffer();
				oneLine.append(video.id);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(video.title == null ? NULL_STRING :video.title);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(video.url == null ? NULL_STRING :video.url);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(video.img == null ? NULL_STRING :video.img);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(video.shortDescription == null ? NULL_STRING :video.shortDescription);
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(video.description == null ? NULL_STRING :video.description );
				bw.write(oneLine.toString());
				bw.newLine();

			
		} finally {
			 
			bw.flush();
			bw.close();
		}
	}

	public static void importVideoRating(File file) throws IOException {
		 
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
//				new FileOutputStream(DatasetPath.videoRatingPath(), true),
//				"UTF-8"));
//		String[] nextLine;
//		try {
//				StringBuffer oneLine = new StringBuffer();
//				oneLine.append(userID);
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(videoID);
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(rating);
//				bw.write(oneLine.toString());
//				bw.newLine();
//			}
//		} finally {
//			 
//			bw.flush();
//			bw.close();
//		}
	}
}

