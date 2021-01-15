package github.davids13.mypetfinderapp.control.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;

public class PetConfig {

    @Inject
    @ConfigProperty(name = "pet.api.def.open_api.path")
    private String petOpenApiPath;

    @Inject
    @ConfigProperty(name = "error.link.documentation")
    private String errorLinkDocumentation;

    public String getPetOpenApiPath() {
        return petOpenApiPath;
    }

    public String getErrorLinkDocumentation() {
        return errorLinkDocumentation;
    }
}
