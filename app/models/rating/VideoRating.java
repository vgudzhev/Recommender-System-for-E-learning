package models.rating;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import models.User;
import play.db.ebean.Model;

@Entity
public class VideoRating extends Model {

	@Id
	@GeneratedValue
	public long id;
	public long userID;
	public long videoID;
	public double rating;

	public VideoRating(Builder builder) {
		userID = builder.userID;
		videoID = builder.videoID;
		rating = builder.rating;
	}

	public static class Builder {
		private long userID;
		private long videoID;
		private double rating;

		public Builder userID(long userID) {
			this.userID = userID;
			return this;
		}

		public Builder videoID(long videoID) {
			this.videoID = videoID;
			return this;
		}

		public Builder rating(double rating) {
			this.rating = rating;
			return this;
		}

		public VideoRating build() {
			return new VideoRating(this);
		}
	}

	public static Finder<String, VideoRating> find = new Finder<String, VideoRating>(
			String.class, VideoRating.class);

	public static List<VideoRating> findByUserID(long userID) {
		return find.where().eq("userID", userID).findList();
	}

	public static VideoRating findByVideoID(long videoID) {
		return find.where().eq("videoID", videoID).findUnique();
	}

	public static List<VideoRating> findByUserEmail(String email) {
		User user = User.findByEmail(email);
		long id = user.userID;
		return find.where().eq("userID", id).findList();
	}

}
