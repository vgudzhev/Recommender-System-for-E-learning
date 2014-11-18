package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class AbstractItem extends Model{
	
	@Id
	public long id;
	public String title;
	public String description;
	
	public static AbstractItem findByID(Long id) {
        return find.where().eq("id", id).findUnique();
    }
	
	public static Finder<String,AbstractItem> find = new Finder<String,AbstractItem>(
	        String.class, AbstractItem.class
	 );
}
