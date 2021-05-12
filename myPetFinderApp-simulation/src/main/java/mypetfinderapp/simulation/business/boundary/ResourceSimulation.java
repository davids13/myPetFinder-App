package simulation.business.boundary;

import org.base.project.simulation.commons.boundary.BaseResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("put/the/url/to/simulate/a/call")
public class ResourceSimulation extends BaseResource {

    /*
        This endpoint is to simulate a external call if for example the microservice isn't ready-
     */

    private final String FULL_HST_DATA = getFileAsString("hst_data.json");

    @GET
    @Path("specific/resource")
    public Response getAllData(@QueryParam("id") final String id) {
        if (checkIfContains(FULL_HST_DATA, "bunos", id)) {
            return Response.status(Response.Status.OK).entity(FULL_HST_DATA).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
