package github.davids13.mypetfinderapp.commons.errors;

import java.util.Objects;

public class PetErrorMessage {

    private String requestDateTime;
    private int statusCode;
    private String petErrorCode;
    private String petErrorDescription;
    private String link;
    private String requestUrl;

    public PetErrorMessage(String requestDateTime, int statusCode, String petErrorCode, String petErrorDescription, String link, String requestUrl) {
        this.requestDateTime = requestDateTime;
        this.statusCode = statusCode;
        this.petErrorCode = petErrorCode;
        this.petErrorDescription = petErrorDescription;
        this.link = link;
        this.requestUrl = requestUrl;
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

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetErrorMessage that = (PetErrorMessage) o;
        return statusCode == that.statusCode && Objects.equals(requestDateTime, that.requestDateTime) && Objects.equals(petErrorCode, that.petErrorCode) && Objects.equals(petErrorDescription, that.petErrorDescription) && Objects.equals(link, that.link) && Objects.equals(requestUrl, that.requestUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDateTime, statusCode, petErrorCode, petErrorDescription, link, requestUrl);
    }

    @Override
    public String toString() {
        return String.format("PetErrorMessage{requestDateTime='%s', statusCode=%d, petErrorCode='%s', petErrorDescription='%s', link='%s', requestUrl='%s'}", requestDateTime, statusCode, petErrorCode, petErrorDescription, link, requestUrl);
    }
}
