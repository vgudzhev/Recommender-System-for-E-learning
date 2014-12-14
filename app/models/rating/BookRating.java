package models.rating;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import models.User;
import play.db.ebean.Model;

@Entity
public class BookRating extends Model {

	@Id
	@GeneratedValue
	public long id;
	public long userID;
	public long bookID;
	public double rating;

	public static List<BookRating> findByUserID(long userID) {
		return find.where().eq("userID", userID).findList();
	}

	public BookRating(Builder bookRatingBuilder) {
		userID = bookRatingBuilder.userID;
		bookID = bookRatingBuilder.bookID;
		rating = bookRatingBuilder.rating;
	}

	public static class Builder {
		private long userID;
		private long bookID;
		private double rating;

		public Builder userID(long userID) {
			this.userID = userID;
			return this;
		}

		public Builder bookID(long bookID) {
			this.bookID = bookID;
			return this;
		}

		public Builder rating(double rating) {
			this.rating = rating;
			return this;
		}

		public BookRating build() {
			return new BookRating(this);
		}
	}

	public static Finder<String, BookRating> find = new Finder<String, BookRating>(
			String.class, BookRating.class);

	public static List<BookRating> findByUserEmail(String email) {
		User user = User.findByEmail(email);
		long id = user.userID;
		return find.where().eq("userID", id).findList();
	}

}