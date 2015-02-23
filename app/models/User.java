package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.mindrot.jbcrypt.BCrypt;

import play.db.ebean.Model;
import recommendationSystem.utils.HashHelper;

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
    public String defaultLocale;
    public String role;
    
    public User(UserBuilder userBuilder){
    	this.userID = userBuilder.userID;
    	this.email = userBuilder.email;
    	this.name = userBuilder.name;
    	this.password = userBuilder.password;
    	this.age= userBuilder.age;
        this.gender=userBuilder.gender;
        this.profession = userBuilder.profession;
        this.defaultLocale = userBuilder.defaultLocale;
        this.role=userBuilder.role;
    }
    
    public static class UserBuilder {
    	private Long userID;
    	private String email;
    	private String name;
    	private String password;
    	private int age;
    	private String gender;
    	private String profession;
    	private String defaultLocale;
    	private String role = "user";
    	
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
    		this.password = HashHelper.createPassword(password); 
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
    	
    	public UserBuilder defaultLocale(String defaultLocale){
    		this.defaultLocale = defaultLocale;
    		return this;
    	}
    	
    	public UserBuilder age(int age){
    		this.age=age;
    		return this;
    	}
    	
    	public UserBuilder role(String role){
    		this.role=role;
    		return this;
    	}
    	
    	public User build(){
    		return new User(this);
    	}
    }

    public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    );
    
    public static User authenticate(String email, String password) {
    	User user = User.find.where().eq("email", email).findUnique();
        if (user != null && BCrypt.checkpw(password, user.password)) {
          return user;
        } else {
          return null;
        }
    }
    
    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }
    
    public static User findByID(Long userID) {
        return find.where().eq("userID", userID).findUnique();
    }
}
