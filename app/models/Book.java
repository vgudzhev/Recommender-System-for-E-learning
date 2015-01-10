package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Book extends Model{
	
	@Id
	public long id;
	public String title;
	public String author;
	public String year;
	public String publisher;
	public String img;
	public String shortDescription;
	public String description;

	public static Finder<String, Book> find = new Finder<String, Book>(
			String.class, Book.class);

	public static Book findById(long id) {
		return find.where().eq("id", id).findUnique();
	}
	
	private Book(BookBuilder bookBuilder){
		id=bookBuilder.id;
		title = bookBuilder.title;
		author = bookBuilder.author;
		year = bookBuilder.year;
		publisher = bookBuilder.publisher;
		img = bookBuilder.img;
		shortDescription = bookBuilder.shortDescription;
		description = bookBuilder.description;
		
	}
	
	public static class BookBuilder {
		private long id;
		private String title;
		private String author;
		private String year;
		private String publisher;
		private String img;
		private String shortDescription;
		private String description;
		
		public BookBuilder id(long id){
			this.id =id;
			return this;
		}
		
		public BookBuilder title(String title){
			this.title= title;
			return this;
		}
		
		public BookBuilder author(String author){
			this.author = author;
			return this;
		}
		
		public BookBuilder year(String year){
			this.year = year;
			return this;
		}
		
		public BookBuilder publisher(String publisher){
			this.publisher = publisher;
			return this;
		}
		
		public BookBuilder image(String img){
			this.img = img;
			return this;
		}
		
		public BookBuilder shortDescription(String shortDescription){
			this.shortDescription = shortDescription;
			return this;
		}
		
		public BookBuilder description(String description){
			this.description = description;
			return this;
		}
		
		public Book build(){
			return new Book(this);
		}
	}
}
