package github.davids13.mypetfinderapp.commons.config;

import github.davids13.mypetfinderapp.commons.errors.CustomExceptionMapper;
import github.davids13.mypetfinderapp.commons.filterandinterceptors.RequestLoggingFilter;
import github.davids13.mypetfinderapp.commons.filterandinterceptors.ResponseLoggingFilter;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@OpenAPIDefinition(
        info = @Info(
                title = "My Pet Finder App - API",
                description = "An app to localize your lovely pet",
                contact = @Contact(
                        name = "davids13",
                        url = "https://github.com/davids13/myPetFinder-App"),
                version = "v1.0.0",
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html")
        ),
        tags = {
                @Tag(name = "pet"),
                @Tag(name = "dog"),
                @Tag(name = "cat")
        },
        servers = {@Server(
                url = "http://localhost:8080/myPetFinderApp",
                description = "PROD environment URl")
        },
        externalDocs = @ExternalDocumentation(url = "http://localhost:8080/openapi", description = "OpenAPI of the app")
)
@ApplicationPath("api")
public class JaxRSApplication extends Application {

    // add provider classes here
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(CustomExceptionMapper.class);
        classes.add(RequestLoggingFilter.class);
        classes.add(ResponseLoggingFilter.class);
        return classes;
    }

}
