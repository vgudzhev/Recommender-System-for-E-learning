package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import play.db.ebean.Model;

@Entity
public class AbstractItem extends Model {

	@Id
	@GeneratedValue
	public long id;
	public String title;
	public String shortDescription;
	public String description;
	public String img;

	public static AbstractItem findByID(Long id) {
		return find.where().eq("id", id).findUnique();
	}

	public static Finder<String, AbstractItem> find = new Finder<String, AbstractItem>(
			String.class, AbstractItem.class);

	private AbstractItem(AbstractItemBuilder abstractItemBuilder) {
		id = abstractItemBuilder.id;
		title = abstractItemBuilder.title;
		shortDescription = abstractItemBuilder.shortDescription;
		description = abstractItemBuilder.description;
		img = abstractItemBuilder.img;
	}

	public static class AbstractItemBuilder {
		private long id;
		private String title;
		private String shortDescription;
		private String description;
		private String img;
		
		public AbstractItemBuilder id(long id){
			this.id = id;
			return this;
		}

		public AbstractItemBuilder title(String title) {
			this.title = title;
			return this;
		}

		public AbstractItemBuilder img(String img) {
			this.img = img;
			return this;
		}

		public AbstractItemBuilder shortDescription(String shortDescription) {
			this.shortDescription = shortDescription;
			return this;
		}

		public AbstractItemBuilder description(String description) {
			this.description = description;
			return this;
		}

		public AbstractItem build() {
			return new AbstractItem(this);
		}
	}
}
