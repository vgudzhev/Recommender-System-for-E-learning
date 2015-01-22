package models;

import java.util.ArrayList;
import java.util.List;

public class ItemWrapper {
	public long id;
	public String title;
	public String description;
	String url;

	public ItemWrapper(long id, String title, String description, String url) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.url  =url;
	}

	public static ItemWrapper getItemWrapper(long id, String title,
			String description, String url) {
		return new ItemWrapper(id, title, description, url);
	}

	public static List<ItemWrapper> wrapVideos(List<Video> videos){
		List<ItemWrapper> allItems = new ArrayList<ItemWrapper>();
		for (Video video : videos) {
			allItems.add(ItemWrapper.getItemWrapper(video.id, video.title, video.description, video.url));
		}
		
		return allItems;
	}
	
	public static List<ItemWrapper> wrapCourses(List<Course> courses){
		List<ItemWrapper> allItems = new ArrayList<ItemWrapper>();
		for (Course course: courses) {
			allItems.add(ItemWrapper.getItemWrapper(course.id, course.title, course.description, course.url));
		}
		
		return allItems;
	}
	
	public static List<ItemWrapper> wrapBooks(List<Book> books){
		List<ItemWrapper> allItems = new ArrayList<ItemWrapper>();
		for (Book book : books) {
			allItems.add(ItemWrapper.getItemWrapper(book.id, book.title, book.description, book.img));
		}
		
		return allItems;
	}
	
	public static List<ItemWrapper> wrapAbstractItems(List<AbstractItem> items){
		List<ItemWrapper> allItems = new ArrayList<ItemWrapper>();
		for (AbstractItem item : items) {
			allItems.add(ItemWrapper.getItemWrapper(item.id, item.title, item.description, item.img));
		}
		
		return allItems;
	}
}
