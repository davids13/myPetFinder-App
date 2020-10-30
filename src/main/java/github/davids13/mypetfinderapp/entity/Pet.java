package github.davids13.mypetfinderapp.entity;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pets")
@NamedQuery(name = Pet.PET_FIND_ALL, query = Pet.PET_FIND_ALL_QUERY)
public class Pet extends AbstractEntity implements Serializable {

    public static final String PET_FIND_ALL = "Pet.findAll";
    public static final String PET_FIND_ALL_QUERY = "SELECT p FROM Pet p";

    @Column(name = "pet_name")
    private String petName;
    @Column(name = "pet_description")
    private String petDescription;
    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private Owner owner;
    @ManyToMany
    @JoinTable(
            name = "pet_localization",
            joinColumns = @JoinColumn(name = "pet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "localization_id", referencedColumnName = "id")
    )
    private Set<Localization> localization;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetDescription() {
        return petDescription;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<Localization> getLocalization() {
        return localization;
    }

    public void setLocalization(Set<Localization> localization) {
        this.localization = localization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pet pet = (Pet) o;
        return Objects.equals(petName, pet.petName) &&
                Objects.equals(petDescription, pet.petDescription) &&
                Objects.equals(owner, pet.owner) &&
                Objects.equals(localization, pet.localization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petName, petDescription, owner, localization);
    }

    @Override
    public String toString() {
        return String.format("Pet{petName='%s', petDescription='%s', owner=%s, localization=%s}", petName, petDescription, owner, localization);
    }
}