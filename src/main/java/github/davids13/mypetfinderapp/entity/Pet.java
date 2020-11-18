package github.davids13.mypetfinderapp.entity;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
@Table(name = "pets")
@NamedQuery(name = Pet.PET_FIND_ALL, query = Pet.PET_FIND_ALL_QUERY)
public class Pet extends AbstractEntity {
    /*
        - MANY pets could be associated to ONE owner
        - this class has the relationship  (has the FK)
    */

    public static final String PET_FIND_ALL = "Pet.findAll";
    public static final String PET_FIND_ALL_QUERY = "SELECT p FROM Pet p";
    public static final String PET_OWNER_FIND_ALL_QUERY = "SELECT p FROM Pet p JOIN Owner o ON p.id=o.id";

    @Column(name = "pet_name")
    @JsonbProperty("the pet name")
    private String petName;
    @Column(name = "pet_description")
    private String petDescription;
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerid", referencedColumnName = "id")
    private Owner owner;

    /*
    @ManyToMany
    @JoinTable(
            name = "pet_localization",
            joinColumns = @JoinColumn(name = "pet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "localization_id", referencedColumnName = "id")
    )
    private List<Localization> localization;
    */

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

    @Override
    public String toString() {
        return String.format("Pet{petName='%s', petDescription='%s', owner=%s}", petName, petDescription, owner);
    }
}