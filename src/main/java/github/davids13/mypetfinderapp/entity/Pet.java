package github.davids13.mypetfinderapp.entity;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pets")
@NamedQuery(name = Pet.PET_FIND_ALL, query = Pet.PET_FIND_ALL_QUERY)
public class Pet extends AbstractEntity implements Serializable {
    /*
        - MANY pets could be associated to ONE owner
        - this class has the relationship  (has the FK)
    */

    public static final String PET_FIND_ALL = "Pet.findAll";
    public static final String PET_FIND_ALL_QUERY = "SELECT p FROM Pet p";
    public static final String PET_OWNER_FIND_ALL_QUERY = "SELECT p FROM Pet p JOIN Owner o ON p.id=o.id";

    @Column(name = "pet_name")
    private String petName;
    @Column(name = "pet_description")
    private String petDescription;
    @ManyToOne
    @JoinColumn(name = "ownerid", nullable = false)
    private Owner owner;
    @ManyToMany
    @JoinTable(
            name = "pet_localization",
            joinColumns = @JoinColumn(name = "pet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "localization_id", referencedColumnName = "id")
    )
    private List<Localization> localization;

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

    public List<Localization> getLocalization() {
        return localization;
    }

    public void setLocalization(List<Localization> localization) {
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
        return Objects.hash(super.hashCode(), petDescription, owner, localization);
    }

    @Override
    public String toString() {
        return String.format("Pet{petName='%s', petDescription='%s', owner=%s, localization=%s}", petName, petDescription, owner, localization);
    }
}