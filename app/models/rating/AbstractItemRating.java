package models.rating;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import models.User;
import play.db.ebean.Model;

@Entity
public class AbstractItemRating extends Model {
	
	@Id
	@GeneratedValue
	public long id;
	public long userID;
	public long itemID;
	public double rating;
	
	private AbstractItemRating(Builder builder){
		userID= builder.userID;
		itemID = builder.itemID;
		rating = builder.rating;
	}
	
	public static class Builder {
		private long userID;
		private long itemID;
		private double rating;
		
		public Builder userID(long userID){
			this.userID =userID;
			return this;
		}
		
		public Builder itemID(long bookID){
			this.itemID = bookID;
			return this;
		}
		
		public Builder rating(double rating){
			this.rating= rating;
			return this;
		}
		
		public AbstractItemRating build(){
			return new AbstractItemRating(this);
		}
	}

	public static Finder<String, AbstractItemRating> find = new Finder<String, AbstractItemRating>(
			String.class, AbstractItemRating.class);

	public static List<AbstractItemRating> findByUserID(long userID) {
        return find.where().eq("userID", userID).findList();
    }
	
	public static List<AbstractItemRating> findByUserEmail(String email){
		User user = User.findByEmail(email);
		long id = user.userID;
		return find.where().eq("userID", id).findList();
	}
}
