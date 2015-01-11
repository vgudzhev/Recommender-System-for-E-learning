package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import play.db.ebean.Model;

@Entity
public class User extends Model{
	
	@Id
	public Long userID;
    public String email;
	public String name;
    public String password;
    public int age = 0;
    public String gender;
    public String profession;
    
    public User(UserBuilder userBuilder){
    	this.userID = userBuilder.userID;
    	this.email = userBuilder.email;
    	this.name = userBuilder.name;
    	this.password = userBuilder.password;
    	this.age= userBuilder.age;
        this.gender=userBuilder.gender;
        this.profession = userBuilder.profession;
    }
    
    public static class UserBuilder {
    	private Long userID;
    	private String email;
    	private String name;
    	private String password;
    	private int age;
    	private String gender;
    	private String profession;
    	
    	public UserBuilder id(Long userID){
    		this.userID = userID;
    		return this;
    	}
    	
    	public UserBuilder email(String email){
    		this.email=email;
    		return this;
    	}
    	
    	public UserBuilder name(String name){
    		this.name = name;
    		return this;
    	}
    	
    	public UserBuilder password(String password){
    		this.password = password;
    		return this;
    	}
    	
    	public UserBuilder gender(String gender){
    		this.gender = gender;
    		return this;
    	}
    	
    	public UserBuilder profession(String profession){
    		this.profession = profession;
    		return this;
    	}
    	
    	public UserBuilder age(int age){
    		this.age=age;
    		return this;
    	}
    	
    	public User build(){
    		return new User(this);
    	}
    }

    public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    );
    
    public static boolean authenticate(String email, String password) {
         if(find.where().eq("email", email).eq("password", password).findUnique() != null){
        	 return true;
         }
         return false;
    }
    
    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }
    
    public static User findByID(Long userID) {
        return find.where().eq("userID", userID).findUnique();
    }
}
