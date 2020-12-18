package github.davids13.mypetfinderapp.commons.errors.type;

public enum PetErrorDescription {

    PET_ERROR_1("Owner was not found");

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
