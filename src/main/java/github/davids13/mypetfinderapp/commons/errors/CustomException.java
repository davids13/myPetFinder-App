package github.davids13.mypetfinderapp.commons.errors;

import github.davids13.mypetfinderapp.commons.errors.type.PetErrorDescription;

public class CustomException extends RuntimeException {

    private final String date;
    private final int status;
    private final String petErrorCode;
    private final PetErrorDescription petErrorDescription;
    private final String link;

    public CustomException(String message, String date, int status, String petErrorCode, PetErrorDescription petErrorDescription, String link) {
        super(message);
        this.date = date;
        this.status = status;
        this.petErrorCode = petErrorCode;
        this.petErrorDescription = petErrorDescription;
        this.link = link;
    }

    @Override
    public String toString() {
        return String.format("CustomException{date=%s, status=%d, petErrorCode=%s, petErrorDescription=%s, link='%s'}", date, status, petErrorCode, petErrorDescription, link);
    }
}
