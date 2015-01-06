package recommendationSystem.dataset.ExportUtils;

import java.util.List;

import models.AbstractItem;
import models.Book;
import models.Course;
import models.User;
import models.Video;
import models.rating.AbstractItemRating;
import models.rating.BookRating;
import models.rating.CourseRating;
import models.rating.VideoRating;
import play.mvc.Results.Chunks;
import play.mvc.Results.StringChunks;

public class CSVExporter {
	private static final String SEPARATOR=",";
	private static final String NEW_LINE="\n";
	
	public Chunks<String> exportAbstractItemsRating() {
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<AbstractItemRating> ratings = AbstractItemRating.find.all();
				for (AbstractItemRating currentRating : ratings) {
					out.write(currentRating.id+"");
					out.write(SEPARATOR);
					out.write(currentRating.userID+"");
					out.write(SEPARATOR);
					out.write(currentRating.itemID+"");
					out.write(SEPARATOR);
					out.write(currentRating.rating+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};
		return chunks;
	}
	
	public Chunks<String> exportCourseRating() {
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<CourseRating> ratings = CourseRating.find.all();
				for (CourseRating currentRating : ratings) {
					out.write(currentRating.id+"");
					out.write(SEPARATOR);
					out.write(currentRating.userID+"");
					out.write(SEPARATOR);
					out.write(currentRating.courseID+"");
					out.write(SEPARATOR);
					out.write(currentRating.rating+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};
		return chunks;
	}
	
	public Chunks<String> exportVideoRating() {
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<VideoRating> ratings = VideoRating.find.all();
				for (VideoRating currentRating : ratings) {
					out.write(currentRating.id+"");
					out.write(SEPARATOR);
					out.write(currentRating.userID+"");
					out.write(SEPARATOR);
					out.write(currentRating.videoID+"");
					out.write(SEPARATOR);
					out.write(currentRating.rating+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};
		return chunks;
	}
	
	public Chunks<String> exportBookRating() {
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<BookRating> ratings = BookRating.find.all();
				
				for(BookRating currentRating: ratings){
					out.write(currentRating.id+"");
					out.write(SEPARATOR);
					out.write(currentRating.userID+"");
					out.write(SEPARATOR);
					out.write(currentRating.bookID+"");
					out.write(SEPARATOR);
					out.write(currentRating.rating+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};
		return chunks;
	}

	public Chunks<String> exportUsers() {
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<User> users = User.find.all();
				// TODO 
				for(User currentUser: users){
					out.write(currentUser.userID+"");
					out.write(SEPARATOR);
					out.write(currentUser.password+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};
		return chunks;
	}

	public Chunks<String> exportCourses() {
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<Course> courses = Course.find.all();
				// TODO 
				for(Course currentCourse: courses){
					out.write(currentCourse.title+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};
		return chunks;
	}

	public Chunks<String> exportAbstractItems() {
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<AbstractItem> items = AbstractItem.find.all();
				// TODO 
				for(AbstractItem currentItem: items){
					out.write(currentItem.title+"");
					out.write(NEW_LINE);
				}
				out.close();
			}
		};
		return chunks;
	}

	public Chunks<String> exportVideos() {
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<Video> videos = Video.find.all();
				// TODO 
				for(Video currentVideo: videos){
					out.write(currentVideo.title);
					out.write(NEW_LINE);
				}
				out.close();
			}
		};
		return chunks;
	}

	public Chunks<String> exportBooks() {
		Chunks<String> chunks = new StringChunks() {

			public void onReady(Chunks.Out<String> out) {
				List<Book> books = Book.find.all();
				// TODO 
				for(Book currentBook: books){
					out.write(currentBook.title);
					out.write(NEW_LINE);
				}
				out.close();
			}
		};
		return chunks;
	}
}
