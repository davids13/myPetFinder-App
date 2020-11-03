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
import java.util.Optional;

@Path("")
@Stateful
public class PetResource {

    @Inject
    private PetService petService;

    @GET
    @Path("/owners")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOwners() {
        final Optional<List<Owner>> owners = Optional.ofNullable(petService.findAll());
        if (!owners.isPresent())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(owners.get()).build();
    }

    @GET
    @Path("/owner/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwner(@PathParam("id") Integer id) {
        final Optional<Owner> owner = Optional.ofNullable(petService.find(id));
        if (!owner.isPresent())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.status(Response.Status.OK).entity(owner.get()).build();
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
        final Optional<List<Pet>> pets = Optional.ofNullable(petService.findAllPets());
        if (!pets.isPresent())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.status(Response.Status.OK).entity(pets.get()).build();
    }

    @POST
    @Path("/owner/{id}/createPet")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPet(@PathParam("id") Integer id, final Pet pet) {
        final Optional<Owner> ownerOptional = Optional.ofNullable(petService.find(id));
        if (!ownerOptional.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Owner owner = ownerOptional.get();
        pet.setOwner(owner);
        petService.create(owner);
        return Response.status(Response.Status.CREATED).entity(owner).build();
    }
}
