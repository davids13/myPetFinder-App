package github.davids13.mypetfinderapp.boundary;

import github.davids13.mypetfinderapp.commons.errors.CustomException;
import github.davids13.mypetfinderapp.commons.errors.type.PetErrorCode;
import github.davids13.mypetfinderapp.commons.errors.type.PetErrorDescription;
import github.davids13.mypetfinderapp.control.config.PetConfig;
import github.davids13.mypetfinderapp.control.mapping.MyPetFinderMapper;
import github.davids13.mypetfinderapp.control.service.IPetService;
import github.davids13.mypetfinderapp.entity.Owner;
import github.davids13.mypetfinderapp.entity.Pet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Path("")
//@Stateful
@ApplicationScoped
public class PetResource {

    // TODO: create a helper method to perform the validations // add page size // work on custom exception handler // entities hashcode error // security // cache // http content negotiations
    // TODO: https://trello.com/b/y1DjpRyO/davids-kb
    @Inject
    private IPetService iPetService;

    @Inject
    private MyPetFinderMapper myPetFinderMapper;

    @Inject
    private PetConfig petConfig;

    /**
     * Use uriInfo to get current context path and to build HATEOAS links
     */
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("owners")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllOwners() {
        final Optional<List<Owner>> owners = Optional.ofNullable(iPetService.findAllOwners());
        if (owners.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.ok(owners.get()).build();
    }

    @GET
    @Path("owners/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveOwner(@PathParam("id") final Integer id/*, @CookieParam("id") final Cookie id*/) {
        //final Optional<Owner> owner = Optional.ofNullable(petService.findOwner(id));
        final Owner owner = iPetService.findOwner(id);

        // Caching
        final CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(300);
        cacheControl.setPrivate(true);
        cacheControl.setNoStore(false);
        final Response.ResponseBuilder responseBuilder = Response.ok(owner, MediaType.APPLICATION_JSON);


        //if (owner.isEmpty())
        //return Response.status(Response.Status.NOT_FOUND).build();
        if (owner == null) {
            throw new CustomException("RESOURCE:", currentDateAndTime(), Response.Status.NOT_FOUND.getStatusCode(), PetErrorCode.NOT_FOUND.getLabel(), PetErrorDescription.PET_ERROR_1.getLabel(), petConfig.getErrorLinkDocumentation());
            //throw new WebApplicationException(Response.Status.NOT_FOUND);

        }

        //return Response.status(Response.Status.OK).entity(owner.get()).build();
        //return Response.status(Response.Status.OK).entity(owner).build();
        return responseBuilder.build();
    }

    @POST
    @Path("owners")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOwner(final Owner owner) {
        if (owner.getFirstName().isEmpty() || owner.getLastName().isEmpty() || owner.getEmail().isEmpty() || owner.getPhone().isEmpty())
            throw new WebApplicationException((Response.status(Response.Status.NOT_ACCEPTABLE).build()));

        iPetService.create(owner);
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

        Optional<Owner> ownerOptional = Optional.ofNullable(iPetService.findOwner(id));
        if (ownerOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        final Owner ownerUpdated = myPetFinderMapper.mappingUpdate(owner, id);

        iPetService.modify(ownerUpdated);

        return Response.created(uri).build();
        // return Response.created(URI.create("/customers/"
        //+ customer.getId())).build();
    }

    @DELETE
    @Path("owners/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOwner(@PathParam("id") final Integer id) {
        if (id == null)
            throw new CustomException("RESOURCE:", currentDateAndTime(), Response.Status.NOT_FOUND.getStatusCode(), PetErrorCode.NOT_FOUND.getLabel(), PetErrorDescription.PET_ERROR_1.getLabel(), "https://app.nuclino.com/illuminati-geeks/Illuminati-wrk-space/Error-Description-cf9f0f2e-7a38-4dc5-8c1e-25aae4b51fba");

        final Optional<Owner> owner = Optional.ofNullable(iPetService.findOwner(id));

        if (owner.isEmpty())
            throw new CustomException("RESOURCE:", currentDateAndTime(), Response.Status.NOT_FOUND.getStatusCode(), PetErrorCode.NOT_FOUND.getLabel(), PetErrorDescription.PET_ERROR_1.getLabel(), "https://app.nuclino.com/illuminati-geeks/Illuminati-wrk-space/Error-Description-cf9f0f2e-7a38-4dc5-8c1e-25aae4b51fba");

        iPetService.remove(owner.get());
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("pets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPets() {
        final Optional<List<Pet>> pets = Optional.ofNullable(iPetService.findAllPets());
        if (pets.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.status(Response.Status.OK).entity(pets.get()).build();
    }

    @GET
    @Path("pets/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePet(@PathParam("id") final Integer id) {
        final Optional<Pet> pet = Optional.ofNullable(iPetService.findPet(id));
        if (pet.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.status(Response.Status.OK).entity(pet.get()).build();
    }

    @POST
    @Path("owners/{id}/pets")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPetByOwner(@PathParam("id") Integer id, final Pet pet) {
        final Optional<Owner> ownerOptional = Optional.ofNullable(iPetService.findOwner(id));
        if (ownerOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Owner owner = ownerOptional.get();
        //pet.setOwner(owner);
        //owner.setPet(Arrays.asList(pet));
        owner.addPet(pet);
        iPetService.create(pet);
        return Response.status(Response.Status.CREATED).entity(pet).build();
    }

    @PUT
    @Path("pets/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePet(@PathParam("id") final Integer id, final Pet pet) {

        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("pets/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePet(@PathParam("id") final Integer id) {
        if (id == null)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        final Optional<Pet> pet = Optional.ofNullable(iPetService.findPet(id));

        if (pet.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        iPetService.remove(pet.get());
        return Response.status(Response.Status.OK).build();
    }

    private String currentDateAndTime() {
        final Date date = new Date();
        final String FORMAT_DATE = "yyyy.M.d 'at' HH:mm:ss z";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);

        return simpleDateFormat.format(date);
    }
}
