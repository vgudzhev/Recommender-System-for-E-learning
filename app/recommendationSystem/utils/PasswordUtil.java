package recommendationSystem.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordUtil {
	
	public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
		return PasswordHash.createHash(password);
	}
	
	public static boolean compareHashedPasswords(String expected, String actual) throws NoSuchAlgorithmException, InvalidKeySpecException{
		String hashedExpected = hashPassword(expected);
		if (hashedExpected.equals(actual)) {
			return true;
		}
		return false;
	}
}
