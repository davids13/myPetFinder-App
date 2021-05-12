package github.davids13.mypetfinderapp.commons.filterandinterceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Provider
public class ResponseLoggingFilter implements ContainerResponseFilter {

//    private static final Logger LOG = LoggerFactory.getLogger(ResponseLoggingFilter.class);

    @Context
    UriInfo info;

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) {
        final Object object = containerResponseContext.getEntity();
        final int status = containerResponseContext.getStatus();

        checkStatus(status, object);
    }

    private void checkStatus(final int status, final Object object) {
        final Set<Integer> statusCode = new HashSet<>();
        statusCode.add(400);
        statusCode.add(404);

        if (statusCode.contains(status)) {
            System.out.println("fixme");
        }
//            LOG.error("Response: " + object.toString());
    }

    public UriInfo getInfo() {
        return info;
    }

    public void setInfo(UriInfo info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseLoggingFilter that = (ResponseLoggingFilter) o;
        return Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }

    @Override
    public String toString() {
        return String.format("ResponseLoggingFilter{info=%s}", info);
    }
}
