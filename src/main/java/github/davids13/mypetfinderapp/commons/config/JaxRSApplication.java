package github.davids13.mypetfinderapp.commons.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class JaxRSApplication extends Application {

    @Override
    public String toString() {
        return String.format("JaxRSApplication{} %s", super.toString());
    }
}
