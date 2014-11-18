package recommendationSystem.dataset;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import play.Logger;
import models.User;
import au.com.bytecode.opencsv.CSVReader;

public class UserDataset {
	private static final String USERS = "data/initial-data/users.csv";
	public static void init(){
		try {
			importFakeUsers(USERS);
		} catch (FileNotFoundException e) {
			Logger.info("Cannot import User CSV files");
		}
	}
	
	private static void importFakeUsers(String path) throws FileNotFoundException {
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				Long userID = Long.parseLong(nextLine[0]);
				String email = nextLine[1];
				String password = nextLine[2];
				String name = email.substring(0, email.indexOf("@"));

				User user = new User.UserBuilder().id(userID).email(email).password(password).name(name).build(); 
				user.save();

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
