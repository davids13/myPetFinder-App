package github.davids13.mypetfinderapp.commons.filterandinterceptors;

import org.apache.log4j.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

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
}
