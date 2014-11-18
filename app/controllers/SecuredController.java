package controllers;

import play.mvc.*;
import play.mvc.Http.*;

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