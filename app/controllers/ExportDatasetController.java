package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import recommendationSystem.dataset.ExportUtils.DatasetExporter;
import views.html.*;

public class ExportDatasetController extends Controller{
	private static final String JSON = "json";
	private static final String CSV = "csv";
	
	public static Result exportAbstractItemsRating(String type) {
		switch (type) {
		case CSV:

			Chunks<String> chunks = DatasetExporter.getCSVExporter()
					.exportAbstractItemsRating();

			response().setContentType("text/csv");
			response().setHeader("Content-disposition",
					"attachment; filename=" + "abstractItemsRating.csv");
			return ok(chunks);

		case JSON:
			return DatasetExporter.getJSONExporter()
					.exportAbstractItemsRating();

		default:
			return badRequest(notFound.render());
		}

	}

	public static Result exportCourseRating(String type) {
		switch (type) {
		case CSV:
			Chunks<String> chunks = DatasetExporter.getCSVExporter()
					.exportCourseRating();

			response().setContentType("text/csv");
			response().setHeader("Content-disposition",
					"attachment; filename=" + "courseRating.csv");
			return ok(chunks);

		case JSON:
			return DatasetExporter.getJSONExporter().exportCourseRating();

		default:
			return badRequest(notFound.render());
		}
	}

	public static Result exportVideoRating(String type) {
		switch (type) {
		case CSV:
			Chunks<String> chunks = DatasetExporter.getCSVExporter()
					.exportVideoRating();

			response().setContentType("text/csv");
			response().setHeader("Content-disposition",
					"attachment; filename=" + "videoRating.csv");
			return ok(chunks);

		case JSON:
			return DatasetExporter.getJSONExporter().exportVideoRating();

		default:
			return badRequest(notFound.render());
		}

	}

	public static Result exportBookRating(String type) {
		switch (type) {
		case CSV:
			Chunks<String> chunks = DatasetExporter.getCSVExporter()
					.exportBookRating();

			response().setContentType("text/csv");
			response().setHeader("Content-disposition",
					"attachment; filename=" + "bookRating.csv");
			return ok(chunks);
		case JSON:
			return DatasetExporter.getJSONExporter().exportBookRating();

		default:
			return badRequest(notFound.render());
		}
	}

	public static Result exportUsers(String type) {
		switch (type) {
		case CSV:
			Chunks<String> chunks = DatasetExporter.getCSVExporter()
					.exportUsers();
			response().setContentType("text/csv");
			response().setHeader("Content-disposition",
					"attachment; filename=" + "users.csv");
			return ok(chunks);

		case JSON:
			return DatasetExporter.getJSONExporter().exportUsers();
		default:
			return badRequest(notFound.render());
		}
	}

	public static Result exportBooks(String type) {
		switch (type) {
		case CSV:
			Chunks<String> chunks = DatasetExporter.getCSVExporter()
					.exportBooks();
			response().setContentType("text/csv");
			response().setHeader("Content-disposition",
					"attachment; filename=" + "books.csv");
			return ok(chunks);

		case JSON:
			return DatasetExporter.getJSONExporter().exportBooks();
		default:
			return badRequest(notFound.render());
		}
	}

	public static Result exportVideos(String type) {
		switch (type) {
		case CSV:
			Chunks<String> chunks = DatasetExporter.getCSVExporter()
					.exportVideos();
			response().setContentType("text/csv");
			response().setHeader("Content-disposition",
					"attachment; filename=" + "videos.csv");
			return ok(chunks);

		case JSON:
			return DatasetExporter.getJSONExporter().exportVideos();
		default:
			return badRequest(notFound.render());
		}
	}

	public static Result exportCourses(String type) {
		switch (type) {
		case CSV:
			Chunks<String> chunks = DatasetExporter.getCSVExporter()
					.exportCourses();
			response().setContentType("text/csv");
			response().setHeader("Content-disposition",
					"attachment; filename=" + "courses.csv");
			return ok(chunks);

		case JSON:
			return DatasetExporter.getJSONExporter().exportCourses();
		default:
			return badRequest(notFound.render());
		}
	}

	public static Result exportAbstractItems(String type) {
		switch (type) {
		case CSV:
			Chunks<String> chunks = DatasetExporter.getCSVExporter()
					.exportAbstractItems();
			response().setContentType("text/csv");
			response().setHeader("Content-disposition",
					"attachment; filename=" + "abstractItems.csv");
			return ok(chunks);

		case JSON:
			return DatasetExporter.getJSONExporter().exportAbstractItems();
		default:
			return badRequest(notFound.render());
		}
	}
}
