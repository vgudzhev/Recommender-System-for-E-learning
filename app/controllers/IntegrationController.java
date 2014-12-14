package controllers;

import java.util.Iterator;

import models.Course;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;
import play.libs.ws.*;
import play.libs.F.Function;
import play.libs.F.Promise;
import views.html.admin.integrationStatus;

/**
 * This class is used by administrators to create connections between the
 * web-app and other services.
 * 
 * @author VGudzhev
 */
public class IntegrationController extends Controller {

	public static String COURSERA_API = "https://api.coursera.org/api/catalog.v1/courses?fields=name,shortDescription";
	public static String UDACITY_API = "https://www.udacity.com/public-api/v0/courses";

	public static Promise<Result> getFromUdacity() {
		final Promise<Result> resultPromise = WS.url(UDACITY_API)
				.setTimeout(5000).get().map(new Function<WSResponse, Result>() {
					public Result apply(WSResponse response) {

						JsonNode root = response.asJson();
						if (root == null) {
							return badRequest("Expecting Json data");
						}

						JsonNode courses = root.get("courses");
						Iterator<JsonNode> allCourses = courses.elements();
						long counter = 0;
						while (allCourses.hasNext()) {
							JsonNode temp = allCourses.next();
							String title = temp.get("title").asText();
							String description = temp.get("short_summary")
									.asText();
							if (description.length() > 255) {
								description = description.substring(0, 255);
							}

							try {
								Course course = new Course.CourseBuilder()
										.title(title).description(description)
										.build();
								if (Course.find.where().eq("title", title)
										.findUnique() == null) {
									course.save();
									counter++;
								}
							} catch (Exception e) {
								return ok(integrationStatus
										.render("Something bad happened "
												+ e.getMessage()));
							}

						}
						String message;
						if (counter == 0) {
							message = "No new items found";
						} else {
							message = "Number of uploaded courses: " + counter;
						}

						return ok(integrationStatus.render(message));
					}
				});
		return resultPromise;
	}

	public static Promise<Result> getFromCoursera() {
		final Promise<Result> resultPromise = WS.url(COURSERA_API)
				.setTimeout(5000).get().map(new Function<WSResponse, Result>() {
					public Result apply(WSResponse response) {
						JsonNode root = response.asJson();
						if (root == null) {
							return badRequest("Expecting Json data");
						}
						JsonNode courses = root.get("elements");
						Iterator<JsonNode> allCourses = courses.elements();
						int counter = 0;
						while (allCourses.hasNext()) {
							JsonNode temp = allCourses.next();

							String title = temp.get("name").asText();
							String description = temp.get("shortDescription")
									.asText();
							if (description.length() > 255) {
								description = description.substring(0, 255);
							}

							try {
								Course course = new Course.CourseBuilder()
										.title(title).description(description)
										.build();
								if (Course.find.where().eq("title", title)
										.findUnique() == null) {
									course.save();
									counter++;
								}

							} catch (Exception e) {
								return ok(integrationStatus
										.render("Something bad happened "
												+ e.getMessage()));
							}

						}
						String message;
						if (counter == 0) {
							message = "No new items found";
						} else {
							message = "Number of uploaded courses: " + counter;
						}
						return ok(integrationStatus.render(message));
					}
				});
		return resultPromise;
	}

}
