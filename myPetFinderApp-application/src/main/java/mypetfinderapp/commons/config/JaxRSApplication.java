package mypetfinderapp;

import mypetfinderapp.commons.errors.CustomExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
@ApplicationScoped
public class JaxRSApplication extends Application {

    // add provider classes here
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(CustomExceptionMapper.class);
//        classes.add(RequestLoggingFilter.class);
//        classes.add(ResponseLoggingFilter.class);
        return classes;
    }

}
