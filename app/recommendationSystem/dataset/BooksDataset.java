package recommendationSystem.dataset;

import java.io.FileReader;
import java.io.IOException;

import play.Logger;
import models.Book;
import models.rating.BookRating;
import au.com.bytecode.opencsv.CSVReader;

public class BooksDataset {
	private static final String BOOKS_RATING = "data/rating/books-rating.csv";
	private static final String BOOKS = "data/initial-data/books.csv";

	public static void init() {
		try {
			importBooksToDB(BOOKS);
			importBookRating(BOOKS_RATING);
		} catch (IOException e) {
			Logger.info("Cannot import CSV files");
		}		
	}

	private static void importBooksToDB(String path) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			Long id = Long.parseLong(nextLine[0]);
			String title = nextLine[1];
			String author = nextLine[2];
			String year = nextLine[3];
			String publisher = nextLine[4];
			String imageUrl = nextLine[5];

			Book book = new Book.BookBuilder()
							.id(id).title(title)
					.author(author).year(year).publisher(publisher)
					.image(imageUrl).build();
			book.save();
		}
		reader.close();
	}

	private static void importBookRating(String path) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			long userID = Long.parseLong(nextLine[0]);
			long bookID = Long.parseLong(nextLine[1]);
			double rating = Double.parseDouble(nextLine[2]);

			BookRating bookRating = new BookRating.Builder()
					.userID(userID).bookID(bookID).rating(rating).build();
			bookRating.save();
		}
		reader.close();
	}

}
