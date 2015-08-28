package com.mx.srk.ivs.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("journeys")
public class JourneyResource {

	@GET
	@Produces("application/json")
	public String getList() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8081/esb/journeys");
		String response = target.request().get(String.class);

		return response;
	}

	@PUT
	@Consumes("application/json")
	public int update(String json) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8081/esb/journeys");

		Response response = target.request().put(Entity.entity(json, MediaType.APPLICATION_JSON));

		return response.getStatus();
	}

}
