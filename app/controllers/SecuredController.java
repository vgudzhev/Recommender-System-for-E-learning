package controllers;

import play.mvc.*;
import play.mvc.Http.*;

/**
 * The class provide user authentication control. All methods which are using
 * this class by annotation can be used only by authenticated users. Note that
 * all pages except INFO pages should be secured.
 * @author VGudzhev
 *
 */
public class SecuredController extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("user");
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect("/login");
	}
}