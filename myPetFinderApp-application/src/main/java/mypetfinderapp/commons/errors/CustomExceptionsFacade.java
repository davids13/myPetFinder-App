package github.davids13.mypetfinderapp.commons.errors;

import github.davids13.mypetfinderapp.commons.errors.type.PetErrorCode;
import github.davids13.mypetfinderapp.commons.errors.type.PetErrorDescription;
import github.davids13.mypetfinderapp.control.config.PetConfig;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CustomExceptionsFacade {

    public static final String REGEX = "[?]";

    @Inject
    private PetConfig petConfig;
    /**
     * Use uriInfo to get current context path and to build HATEOAS links
     */
    @Context
    private UriInfo uriInfo;

    public static String getREGEX() {
        return REGEX;
    }

    public CustomException getCustomException(final Integer id) {
        return new CustomException(currentDateAndTime(), Response.Status.NOT_FOUND.getStatusCode(), PetErrorCode.NOT_FOUND.getLabel(), PetErrorDescription.PET_ERROR_1.getLabel().replaceFirst(REGEX, String.valueOf(id)), petConfig.getErrorLinkDocumentation(), uriInfo.getAbsolutePath().toString());
    }

    public CustomException getCustomException() {
        return new CustomException(currentDateAndTime(), Response.Status.BAD_REQUEST.getStatusCode(), PetErrorCode.MISSING_FIELDS.getLabel(), PetErrorDescription.PET_ERROR_2.getLabel(), petConfig.getErrorLinkDocumentation(), uriInfo.getAbsolutePath().toString());
    }

    private String currentDateAndTime() {
        final Date date = new Date();
        final String FORMAT_DATE = "yyyy.MM.d 'at' HH:mm:ss z";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);

        return simpleDateFormat.format(date);
    }

    public PetConfig getPetConfig() {
        return petConfig;
    }

    public void setPetConfig(PetConfig petConfig) {
        this.petConfig = petConfig;
    }

    public UriInfo getUriInfo() {
        return uriInfo;
    }

    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomExceptionsFacade that = (CustomExceptionsFacade) o;
        return Objects.equals(petConfig, that.petConfig) && Objects.equals(uriInfo, that.uriInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petConfig, uriInfo);
    }

    @Override
    public String toString() {
        return String.format("CustomExceptionsFacade{petConfig=%s, uriInfo=%s}", petConfig, uriInfo);
    }
}
