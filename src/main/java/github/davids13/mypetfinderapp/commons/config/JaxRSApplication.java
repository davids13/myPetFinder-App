package github.davids13.mypetfinderapp.commons.config;

import github.davids13.mypetfinderapp.boundary.PetResource;
import github.davids13.mypetfinderapp.commons.errors.CustomExceptionMapper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class JaxRSApplication extends Application {

    private final Set<Object> singletons = new HashSet<Object>();
    private final Set<Class<?>> classes = new HashSet<Class<?>>();

    public JaxRSApplication() {
        singletons.add(new PetResource());
        classes.add(CustomExceptionMapper.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }

    @Override
    public Set<Object> getSingletons() {
        return super.getSingletons();
    }

    @Override
    public String toString() {
        return String.format("JaxRSApplication{} %s", super.toString());
    }
}
