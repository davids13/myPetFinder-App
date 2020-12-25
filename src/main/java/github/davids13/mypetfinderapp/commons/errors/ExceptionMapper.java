package github.davids13.mypetfinderapp.commons.errors;

import javax.ws.rs.core.Response;

public interface ExceptionMapper<E extends Throwable> {

    Response toResponse(E exception);
}
