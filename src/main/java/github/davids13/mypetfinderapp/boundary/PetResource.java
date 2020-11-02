package github.davids13.mypetfinderapp.boundary;

import github.davids13.mypetfinderapp.control.service.PetService;
import github.davids13.mypetfinderapp.entity.Owner;
import github.davids13.mypetfinderapp.entity.Pet;

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

    @GET
    @Path("/owners")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOwners() {
        final List<Owner> ownerList = petService.findAll();
        if (ownerList.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(ownerList).build();
    }

    @GET
    @Path("/owner/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwner(@PathParam("id") Integer id) {
        Owner owner;
        if (id == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            owner = petService.find(id);
        }

        return Response.status(Response.Status.OK).entity(owner).build();
    }

    @POST
    @Path("/createOwner")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOwner(final Owner owner) {
        if (owner.getFirstName().isEmpty() || owner.getLastName().isEmpty() || owner.getEmail().isEmpty() || owner.getPhone().isEmpty())
            throw new WebApplicationException((Response.status(Response.Status.NOT_ACCEPTABLE).build()));

        petService.create(owner);

        return Response.status(Response.Status.CREATED).entity(owner).build();
    }

    @GET
    @Path("/pets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPets() {
        final List<Pet> pets = petService.findAllPets();
        if (pets.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(pets).build();
    }
}
