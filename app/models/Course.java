package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.ebean.Model;


@Entity
public class Course extends Model{
	
	@Id
	@GeneratedValue
	public long id;
	public String title;
	public String shortDescription;
	public String description;
	public String url;
	public String img;
	
	public static Course findByID(Long id) {
        return find.where().eq("id", id).findUnique();
    }
	
	public static Finder<String,Course> find = new Finder<String,Course>(
	        String.class, Course.class
	 );
	
	private Course(CourseBuilder courseBuilder){
		this.id=courseBuilder.id;
		this.title = courseBuilder.title;
		this.shortDescription= courseBuilder.shortDescription;
		this.description = courseBuilder.description;
		this.url = courseBuilder.url;
		this.img = courseBuilder.img;	
	}
	
	public static class CourseBuilder {
		private long id;
		private String title;
		private String shortDescription;
		private String description;
		private String url;
		private String img;
		
		public CourseBuilder id(long id){
			this.id =id;
			return this;
		}
		
		public CourseBuilder title(String title){
			this.title= title;
			return this;
		}
		
		public CourseBuilder description(String description){
			this.description = description;
			return this;
		}
		
		public CourseBuilder shortDescription(String shortDescription){
			this.shortDescription = shortDescription;
			return this;
		}
		
		public CourseBuilder url(String url){
			this.url = url;
			return this;
		}
		
		public CourseBuilder img(String img){
			this.img = img;
			return this;
		}
		
		public Course build(){
			return new Course(this);
		}
	}
}
