package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Video extends Model {
	@Id
	@GeneratedValue
	public long id;
	public String title;
	public String description;
	public String url;
	public String img;
	
	public static Video findByID(Long id) {
        return find.where().eq("id", id).findUnique();
    }
	
	public static Finder<String,Video> find = new Finder<String,Video>(
	        String.class, Video.class);
	
	private Video(VideoBuilder VideoBuilder){
		this.title = VideoBuilder.title;
		this.description = VideoBuilder.description;
		this.url = VideoBuilder.url;
		this.img = VideoBuilder.img;
		
	}
	
	public static class VideoBuilder {
		private String title;
		private String description;
		private String url;
		private String img;
		
		public VideoBuilder title(String title){
			this.title= title;
			return this;
		}
		
		public VideoBuilder description(String description){
			this.description = description;
			return this;
		}
		
		public VideoBuilder url(String url){
			this.url = url;
			return this;
		}
		
		public VideoBuilder img(String img){
			this.img = img;
			return this;
		}
		
		public Video build(){
			return new Video(this);
		}
	}
}

