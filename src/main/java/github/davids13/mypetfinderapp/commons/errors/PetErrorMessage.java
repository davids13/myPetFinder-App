package github.davids13.mypetfinderapp.commons.errors;

import java.util.Objects;

public class PetErrorMessage {

    private String requestDateTime;
    private int statusCode;
    private String petErrorCode;
    private String petErrorDescription;
    private String link;

    public PetErrorMessage(String requestDateTime, int statusCode, String petErrorCode, String petErrorDescription, String link) {
        this.requestDateTime = requestDateTime;
        this.statusCode = statusCode;
        this.petErrorCode = petErrorCode;
        this.petErrorDescription = petErrorDescription;
        this.link = link;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPetErrorCode() {
        return petErrorCode;
    }

    public void setPetErrorCode(String petErrorCode) {
        this.petErrorCode = petErrorCode;
    }

    public String getPetErrorDescription() {
        return petErrorDescription;
    }

    public void setPetErrorDescription(String petErrorDescription) {
        this.petErrorDescription = petErrorDescription;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetErrorMessage that = (PetErrorMessage) o;
        return statusCode == that.statusCode && Objects.equals(requestDateTime, that.requestDateTime) && Objects.equals(petErrorCode, that.petErrorCode) && Objects.equals(petErrorDescription, that.petErrorDescription) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDateTime, statusCode, petErrorCode, petErrorDescription, link);
    }

    @Override
    public String toString() {
        return String.format("ErrorMessage{requestDateTime='%s', statusCode=%d, petErrorCode='%s', petErrorDescription='%s', link='%s'}", requestDateTime, statusCode, petErrorCode, petErrorDescription, link);
    }
}
