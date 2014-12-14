package controllers;

import static play.data.Form.form;
import java.util.Random;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.Controller;
import play.mvc.Result;
import models.User;
import views.html.authentication.*;

/**
 * The class is responsible for rendering user account control pages and also
 * provide methods for user account control such as login ,logout and view
 * profile.
 * 
 * @author VGudzhev
 */
public class UserController extends Controller {

	public static Result renderLoginPage() {
		return ok(signin.render("", Form.form(UserController.Login.class)));
	}

	public static Result signIn() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String email = requestData.get("email");
		String password = requestData.get("password");

		if (User.authenticate(email, password)) {
			session().clear();
			session("user", email);

			return redirect("/home");
		}
		return badRequest(signin.render("Invalid user name or password",
				Form.form(UserController.Login.class)));
	}

	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect("/login");
	}

	public static Result renderSignUpPage() {
		Random rand = new Random();
		int randomNumber = rand.nextInt(11);
		session("num", randomNumber + "");
		return ok(signup.render("", randomNumber,
				form(UserController.Register.class)));
	}

	public static Result signUp() {
		DynamicForm requestData = Form.form().bindFromRequest();
		int randomNumber = Integer.parseInt(session().get("num"));
		session().remove("sum");

		String number = requestData.get("sum");
		int userSum = Integer.parseInt(number);

		if (userSum != randomNumber + randomNumber) {
			return ok(signup.render("You are robot!", randomNumber,
					Form.form(UserController.Register.class)));
		}

		String email = requestData.get("email");
		String name = requestData.get("fullName");
		String password = requestData.get("password");
		User user = new User.UserBuilder().email(email).name(name)
				.password(password).build();

		if (User.findByEmail(email) == null) {
			long id = User.find.findRowCount();
			user.userID = ++id;
			user.save();
			session("user", user.email);

			return ok(createdUser.render());
		} else {
			return ok(signup.render("This email is already in use",
					randomNumber, Form.form(UserController.Register.class)));
		}
	}

	public static Result showUserProfile() {
		String userEmail = session("user");
		User currentUser = User.findByEmail(userEmail);
		return ok(showUserProfile.render(currentUser));
	}

	public static class Login {
		@Required
		public String email;
		@Required
		public String password;

		public String validate() {
			if (isBlank(email)) {
				return "Email is required";
			}

			if (isBlank(password)) {
				return "Password is required";
			}

			return null;
		}

		private boolean isBlank(String input) {
			return input == null || input.trim().length() == 0;
		}
	}

	public static class Register {
		@Required
		public String fullName;

		@Required
		public String email;

		@Required
		public String password;

		@Required
		public String sum;

		public String validate() {
			if (isBlank(email)) {
				return "Email is required";
			}

			if (isBlank(fullName)) {
				return "Full title is required";
			}

			if (isBlank(password)) {
				return "Password is required";
			}

			if (isBlank(sum)) {
				return "Are you a robot? Prove that!";
			}

			return null;
		}

		private boolean isBlank(String input) {
			return input == null || input.trim().length() == 0;
		}
	}
}
