package github.davids13.mypetfinderapp.control.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;

public class PetConfig {

    @Inject
    @ConfigProperty(name = "pet.api.def.open_api.path")
    private String petOpenApiPath;
}
