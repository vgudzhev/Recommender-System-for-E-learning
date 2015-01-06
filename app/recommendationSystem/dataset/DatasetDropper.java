package recommendationSystem.dataset;

import java.util.List;

import com.avaje.ebean.Ebean;

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
		List<User> userTable = User.find.all();
		Ebean.delete(userTable);
		String initialData =  Yaml.load("initial-data.yml").toString();
        String[] adminData = initialData.split(" ");
        User admin = new User.UserBuilder().email(adminData[0]).password(adminData[1]).id(1l).build();
        admin.save();
	}
	
	@Transactional
	public static void dropAbstractItemTable(){
		List<AbstractItem> abstractItemTable = AbstractItem.find.all();
		Ebean.delete(abstractItemTable);
	}
	
	@Transactional
	public static void dropBookTable(){
		List<Book> bookTable = Book.find.all();
		Ebean.delete(bookTable);
	}
	
	@Transactional
	public static void dropCourseTable(){
		List<Course> courseTable = Course.find.all();
		Ebean.delete(courseTable);
	}
	
	@Transactional
	public static void dropVideoTable(){
		List<Video> videoTable = Video.find.all();
		Ebean.delete(videoTable);
	}
	
	@Transactional
	public static void dropAbstractItemRatingTable(){
		List<AbstractItemRating> abstractItemRatingTable = AbstractItemRating.find.all();
		Ebean.delete(abstractItemRatingTable);
	}
	
	@Transactional
	public static void dropBookRatingTable(){
		List<BookRating> bookRatingTable = BookRating.find.all();
		Ebean.delete(bookRatingTable);
	}
	
	@Transactional
	public static void dropCourseRatingTable(){
		List<CourseRating> courseRatingTable = CourseRating.find.all();
		Ebean.delete(courseRatingTable);
	}
	
	@Transactional
	public static void dropVideoRatingTable(){
		List<VideoRating> videoRatingTable = VideoRating.find.all();
		Ebean.delete(videoRatingTable);
	}
	
}
