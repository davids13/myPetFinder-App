package github.davids13.mypetfinderapp.boundary;

import github.davids13.mypetfinderapp.control.service.PetService;
import github.davids13.mypetfinderapp.entity.Owner;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
@Stateful
public class PetResource {

    @Inject
    private PetService petService;

    // Owner
    @GET
    @Path("/owners")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwners() {
        final List<Owner> ownerList = petService.getOwnersList();
        if (ownerList.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.ok(ownerList).build();
    }
}
