package github.davids13.mypetfinderapp.commons.errors;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider // this tells the JAX-RS runtime that it is a component
public class CustomExceptionMapper implements ExceptionMapper<CustomException> {
    /*
        @Override
        public Response toResponse(final CustomException exception) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomException(exception.getMessage(), exception.getRequestDateTime(), exception.getStatusCode(), exception.getPetErrorCode(), exception.getPetErrorDescription(), exception.getLink()))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    */
    @Override
    public Response toResponse(final CustomException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new PetErrorMessage(
                        exception.getRequestDateTime(),
                        exception.getStatusCode(),
                        exception.getPetErrorCode(),
                        exception.getPetErrorDescription(),
                        exception.getLink()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
