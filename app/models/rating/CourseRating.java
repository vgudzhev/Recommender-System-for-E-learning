package models.rating;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import models.User;
import play.db.ebean.Model;

@Entity
public class CourseRating extends Model {

	@Id
	@GeneratedValue
	public long id;
	public long userID;
	public long courseID;
	public double rating;

	public CourseRating(Builder builder) {
		userID = builder.userID;
		courseID = builder.courseID;
		rating = builder.rating;
	}

	public static class Builder {
		private long userID;
		private long courseID;
		private double rating;

		public Builder userID(long userID) {
			this.userID = userID;
			return this;
		}

		public Builder courseID(long courseID) {
			this.courseID = courseID;
			return this;
		}

		public Builder rating(double rating) {
			this.rating = rating;
			return this;
		}

		public CourseRating build() {
			return new CourseRating(this);
		}
	}

	public static Finder<String, CourseRating> find = new Finder<String, CourseRating>(
			String.class, CourseRating.class);

	public static List<CourseRating> findByUserID(long userID) {
		return find.where().eq("userID", userID).findList();
	}

	public static List<CourseRating> findByUserEmail(String email) {
		User user = User.findByEmail(email);
		long id = user.userID;
		return find.where().eq("userID", id).findList();
	}
}
