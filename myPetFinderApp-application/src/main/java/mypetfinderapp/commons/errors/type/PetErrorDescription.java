package github.davids13.mypetfinderapp.commons.errors.type;

public enum PetErrorDescription {

    //PET_ERROR_1("Owner with this specific id=[null] was not found"),
    PET_ERROR_1("Owner with this specific id=? was not found"),
    PET_ERROR_2("It was not possible to create an Owner because some properties was not filled"),
    PET_ERROR_3("Pet with this specific id=? was not found"),
    PET_ERROR_4("You don't have the permission to delete this resource");

    public final String errorDescription;

    PetErrorDescription(String description) {
        this.errorDescription = description;
    }

    public String getLabel() {
        return errorDescription;
    }

    @Override
    public String toString() {
        return String.format("PetErrorDescription{label='%s'}", errorDescription);
    }
}
