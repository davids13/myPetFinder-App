package github.davids13.mypetfinderapp.commons.filterandinterceptors;

import org.apache.log4j.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.Objects;

@Provider
public class RequestLoggingFilter implements ContainerRequestFilter {
    // https://quarkus.io/guides/rest-json#http-filters-and-interceptors

    private static final Logger LOG = Logger.getLogger(RequestLoggingFilter.class);

    @Context
    UriInfo info;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        final String method = containerRequestContext.getMethod();
        final String path = info.getPath();

        LOG.info("Request: " + method + " /" + path);
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
        RequestLoggingFilter that = (RequestLoggingFilter) o;
        return Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }

    @Override
    public String toString() {
        return String.format("RequestLoggingFilter{info=%s}", info);
    }
}
