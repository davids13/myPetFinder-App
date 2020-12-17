package github.davids13.mypetfinderapp.entity;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
@NamedQuery(name = Owner.OWNER_FIND_ALL, query = Owner.OWNER_FIND_ALL_QUERY)
public class Owner extends AbstractEntity {
    /*
        - ONE owner could have MANY pets
        - this class is a Non-Owner of the relationship (doesnt have the FK)
        - mappedBy is required for bidirectional associations on the Non-Owner
    */

    public static final String OWNER_FIND_ALL = "Owner.findAll";
    public static final String OWNER_FIND_ALL_QUERY = "SELECT o FROM Owner o";
    public static final String OWNER_PET_FIND_ALL_QUERY = "SELECT o FROM Owner o JOIN Pet p ON o.id=p.id";

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;
    @Column
    private String phone;
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Pet> pet = new ArrayList<>();

    public Owner() {
    }

    /*
        Helper method for bidirectional associations:
            We still need to have both sides in sync as otherwise,
            we break the Domain Model relationship consistency,
            and the entity state transitions are not guaranteed to work unless both sides are properly synchronized.
    */
    // utility method to update both sides of the association
    public void addPet(final Pet pets) {
        pets.setOwner(this);
        //this.getPet().add(pets);

        // Update the pet entity instance to refer to this owner
        pet.add(pets);
    }

    public void removePet(final Pet pets) {
        pet.remove(pets);
        this.setPet(null);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Pet> getPet() {
        return pet;
    }

    public void setPet(List<Pet> pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return String.format("Owner{firstName='%s', lastName='%s', email='%s', phone='%s', pet=%s}", firstName, lastName, email, phone, pet);
    }
}
