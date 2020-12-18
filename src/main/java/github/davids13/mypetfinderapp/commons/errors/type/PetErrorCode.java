package github.davids13.mypetfinderapp.commons.errors.type;

public enum PetErrorCode {

    NOT_FOUND("PET-01");

    public final String label;

    PetErrorCode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return String.format("PetErrorCode{label='%s'}", label);
    }
}
