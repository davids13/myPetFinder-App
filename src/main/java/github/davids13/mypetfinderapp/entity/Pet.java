package github.davids13.mypetfinderapp.entity;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "pets")
@NamedQuery(name = Pet.PET_FIND_ALL, query = Pet.PET_FIND_ALL_QUERY)
public class Pet extends AbstractEntity implements Serializable {

    public static final String PET_FIND_ALL = "Pet.findAll";
    public static final String PET_FIND_ALL_QUERY = "SELECT p FROM Pet p";

    @Column(name = "pet_name")
    private String petName;
    @Column(name = "pet_height")
    private String petHeight;
    @Column(name = "pet_weight")
    private String petWeight;
    @Column(name = "pet_photo")
    @Lob
    private byte[] petPhoto;
    @Column(name = "pet_description")
    private String petDescription;

    // A no arg constructor and full constructor for the serialization
    public Pet() {
    }

    public Pet(Integer id, String petName, String petHeight, String petWeight, byte[] petPhoto, String petDescription) {
        super(id);
        this.petName = petName;
        this.petHeight = petHeight;
        this.petWeight = petWeight;
        this.petPhoto = petPhoto;
        this.petDescription = petDescription;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetHeight() {
        return petHeight;
    }

    public void setPetHeight(String petHeight) {
        this.petHeight = petHeight;
    }

    public String getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(String petWeight) {
        this.petWeight = petWeight;
    }

    public byte[] getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(byte[] petPhoto) {
        this.petPhoto = petPhoto;
    }

    public String getPetDescription() {
        return petDescription;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pet pet = (Pet) o;
        return Objects.equals(petName, pet.petName) &&
                Objects.equals(petHeight, pet.petHeight) &&
                Objects.equals(petWeight, pet.petWeight) &&
                Arrays.equals(petPhoto, pet.petPhoto) &&
                Objects.equals(petDescription, pet.petDescription);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), petName, petHeight, petWeight, petDescription);
        result = 31 * result + Arrays.hashCode(petPhoto);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Pet{petName='%s', petHeight='%s', petWeight='%s', petPhoto=%s, petDescription='%s'}", petName, petHeight, petWeight, Arrays.toString(petPhoto), petDescription);
    }
}