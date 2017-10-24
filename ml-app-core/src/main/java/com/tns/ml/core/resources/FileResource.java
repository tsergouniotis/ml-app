package com.tns.ml.core.resources;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tns.ml.core.mdb.QService;

@Path("/file")
// @JWTTokenNeeded
public class FileResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileResource.class);

	@Inject
	private QService oedService;

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void upload(@Suspended AsyncResponse asyncResponse, Map<String, InputStream> input) {

		InputStream is = Optional.ofNullable(input.get("attachment"))
				.orElseThrow(() -> new BadRequestException("No attachment provided"));

		CompletableFuture.runAsync(() -> {

			try {

				asyncResponse.resume("CSV File processed successfully");
			} catch (Exception e) {
				LOGGER.error("Error during processing the attchment", e);
				asyncResponse.resume("CSV File processing has errors");
			}

		});

	}

	@GET
	@Path("/download/flavors")
	@Produces(MediaType.TEXT_PLAIN)
	public Response downloadFile() {
		try {

			Response response = Response.ok().header("Content-Disposition", "attachment; filename=flavors.csv").build();
			return response;
		} catch (Exception e) {
			LOGGER.error("Error during downloading the flavors as csv.", e);
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/download/designs/{designId}")
	@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response download(@PathParam("designId") Long designId) {
		try {

			Response response = Response.ok().header("Content-Disposition", "attachment; filename=design.csv").build();
			return response;
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

}
