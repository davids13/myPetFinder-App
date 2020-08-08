package github.davids13.mypetfinderapp.boundary;

import github.davids13.mypetfinderapp.control.service.PetService;
import github.davids13.mypetfinderapp.entity.Owner;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.*;
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

    @POST
    @Path("/saveOwner")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveOwner(final Owner owner) {
        if (owner.getFirstName().isEmpty() || owner.getLastName().isEmpty() || owner.getEmail().isEmpty() || owner.getPhone().isEmpty())
            throw new WebApplicationException((Response.status(Response.Status.BAD_REQUEST).build()));

        petService.saveOwner(owner);

        return Response.status(Response.Status.CREATED).entity(owner).build();
    }

}
