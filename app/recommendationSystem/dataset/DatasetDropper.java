package recommendationSystem.dataset;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import com.avaje.ebean.Ebean;

import play.Logger;
import play.db.ebean.Transactional;
import play.libs.Yaml;
import models.AbstractItem;
import models.Book;
import models.Course;
import models.User;
import models.Video;
import models.rating.AbstractItemRating;
import models.rating.BookRating;
import models.rating.CourseRating;
import models.rating.VideoRating;

public class DatasetDropper {
	
	@Transactional
	public static void dropUserTable(){
		// Drop user table
		List<User> userTable = User.find.all();
		Ebean.delete(userTable);
		
		// Load admin
		String initialData =  Yaml.load("initial-data.yml").toString();
        String[] adminData = initialData.split(" ");
        User admin = new User.UserBuilder().email(adminData[0]).password(adminData[1]).id(1l).build();
        admin.save();
        
       CSVEraser(DatasetPath.usersPath());
	}
	
	@Transactional
	public static void dropAbstractItemTable(){
		List<AbstractItem> abstractItemTable = AbstractItem.find.all();
		Ebean.delete(abstractItemTable);
		CSVEraser(DatasetPath.abstractItemsPath());
	}
	
	@Transactional
	public static void dropBookTable(){
		List<Book> bookTable = Book.find.all();
		Ebean.delete(bookTable);
		CSVEraser(DatasetPath.booksPath());
	}
	
	@Transactional
	public static void dropCourseTable(){
		List<Course> courseTable = Course.find.all();
		Ebean.delete(courseTable);
		CSVEraser(DatasetPath.coursesPath());
	}
	
	@Transactional
	public static void dropVideoTable(){
		List<Video> videoTable = Video.find.all();
		Ebean.delete(videoTable);
		CSVEraser(DatasetPath.videoPath());
	}
	
	@Transactional
	public static void dropAbstractItemRatingTable(){
		List<AbstractItemRating> abstractItemRatingTable = AbstractItemRating.find.all();
		Ebean.delete(abstractItemRatingTable);
		CSVEraser(DatasetPath.itemsRatingPath());
	}
	
	@Transactional
	public static void dropBookRatingTable(){
		List<BookRating> bookRatingTable = BookRating.find.all();
		Ebean.delete(bookRatingTable);
		CSVEraser(DatasetPath.booksRatingPath());
	}
	
	@Transactional
	public static void dropCourseRatingTable(){
		List<CourseRating> courseRatingTable = CourseRating.find.all();
		Ebean.delete(courseRatingTable);
		CSVEraser(DatasetPath.coursesRatingPath());
	}
	
	@Transactional
	public static void dropVideoRatingTable(){
		List<VideoRating> videoRatingTable = VideoRating.find.all();
		Ebean.delete(videoRatingTable);
		CSVEraser(DatasetPath.videoRatingPath());
	}
	
	private static void CSVEraser(String path){
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(path,false), "UTF-8"));
			bw.append("");
			
			bw.flush();
			bw.close();
		} catch (Exception e) {
			Logger.info("Unable to save rating." + e.getMessage());
		} 
	}
	
}
