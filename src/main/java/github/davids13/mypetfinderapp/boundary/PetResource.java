package github.davids13.mypetfinderapp.boundary;

import github.davids13.mypetfinderapp.control.mapping.MyPetFinderMapper;
import github.davids13.mypetfinderapp.control.service.PetService;
import github.davids13.mypetfinderapp.entity.Owner;
import github.davids13.mypetfinderapp.entity.Pet;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Path("")
@Stateful
public class PetResource {

    // TODO: create a helper method to perform the validations

    @Inject
    private PetService petService;

    @Inject
    private MyPetFinderMapper myPetFinderMapper;

    /**
     * Use uriInfo to get current context path and to build HATEOAS links
     */
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("owners")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllOwners() {
        final Optional<List<Owner>> owners = Optional.ofNullable(petService.findAll());
        if (owners.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(owners.get()).build();
    }

    /*@GET
    @Path("owners/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveOwner(@PathParam("id") final Integer id) throws ExceptionResponse {
        final Optional<Owner> owner = Optional.ofNullable(petService.find(id));
        if (!owner.isPresent())
            //return Response.status(Response.Status.NOT_FOUND).build();
            throw new ExceptionResponse("NOT FOUND THIS MOCK USER", new Date(), Response.Status.BAD_REQUEST.toString());

        return Response.status(Response.Status.OK).entity(owner.get()).build();
    }*/

    @POST
    @Path("owners")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOwner(final Owner owner) {
        if (owner.getFirstName().isEmpty() || owner.getLastName().isEmpty() || owner.getEmail().isEmpty() || owner.getPhone().isEmpty())
            throw new WebApplicationException((Response.status(Response.Status.NOT_ACCEPTABLE).build()));

        petService.create(owner);
        return Response.status(Response.Status.CREATED).entity(owner).build();
    }

    @PUT
    @Path("owners/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOwner(@PathParam("id") final Integer id, final Owner owner) {
        final URI uri = uriInfo.getAbsolutePath();

        // find the respective owner
        // mapping new updated fields to old fields
        // null => not found
        // void => no content,

        Optional<Owner> ownerOptional = Optional.ofNullable(petService.find(id));
        if (ownerOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        final Owner ownerUpdated = myPetFinderMapper.mappingUpdate(owner, id);

        petService.modify(ownerUpdated);

        return Response.created(uri).build();
    }

    @DELETE
    @Path("owners/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOwner(@PathParam("id") final Integer id) {
        if (id == null)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        final Optional<Owner> owner = Optional.ofNullable(petService.find(id));

        if (owner.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        petService.remove(owner.get());
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("pets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPets() {
        final Optional<List<Pet>> pets = Optional.ofNullable(petService.findAllPets());
        if (pets.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.status(Response.Status.OK).entity(pets.get()).build();
    }

    @GET
    @Path("pets/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePet(@PathParam("id") final Integer id) {
        final Optional<Pet> pet = Optional.ofNullable(petService.findPet(id));
        if (pet.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.status(Response.Status.OK).entity(pet.get()).build();
    }

    @POST
    @Path("owners/{id}/pets")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPetByOwner(@PathParam("id") Integer id, final Pet pet) {
        final Optional<Owner> ownerOptional = Optional.ofNullable(petService.find(id));
        if (ownerOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Owner owner = ownerOptional.get();
        //pet.setOwner(owner);
        owner.setPet(Collections.singletonList(pet));
        //owner.addPet(pet);
        petService.create(pet);
        return Response.status(Response.Status.CREATED).entity(pet).build();
    }

    @PUT
    @Path("pets/{id}")
    @Produces
    @Consumes
    public Response updatePet(@PathParam("id") final Integer id, final Pet pet) {

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("pets/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePet(@PathParam("id") final Integer id) {
        if (id == null)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        final Optional<Pet> pet = Optional.ofNullable(petService.findPet(id));

        if (pet.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        petService.remove(pet.get());
        return Response.status(Response.Status.OK).build();
    }
}
