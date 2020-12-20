package github.davids13.mypetfinderapp.commons.errors;

import java.util.Objects;

public class CustomException extends RuntimeException {

    private final String requestDateTime;
    private final int statusCode;
    private final String petErrorCode;
    private final String petErrorDescription;
    private final String link;

    public CustomException(String message, String requestDateTime, int statusCode, String petErrorCode, String petErrorDescription, String link) {
        super(message);
        this.requestDateTime = requestDateTime;
        this.statusCode = statusCode;
        this.petErrorCode = petErrorCode;
        this.petErrorDescription = petErrorDescription;
        this.link = link;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getPetErrorCode() {
        return petErrorCode;
    }

    public String getPetErrorDescription() {
        return petErrorDescription;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomException that = (CustomException) o;
        return statusCode == that.statusCode && Objects.equals(requestDateTime, that.requestDateTime) && Objects.equals(petErrorCode, that.petErrorCode) && Objects.equals(petErrorDescription, that.petErrorDescription) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDateTime, statusCode, petErrorCode, petErrorDescription, link);
    }

    @Override
    public String toString() {
        return String.format("CustomException{requestDateTime='%s', statusCode=%d, petErrorCode='%s', petErrorDescription='%s', link='%s'}", requestDateTime, statusCode, petErrorCode, petErrorDescription, link);
    }
}
