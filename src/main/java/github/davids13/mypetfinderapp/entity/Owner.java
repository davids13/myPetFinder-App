package github.davids13.mypetfinderapp.entity;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "owners")
@NamedQuery(name = Owner.OWNER_FIND_ALL, query = Owner.OWNER_FIND_ALL_QUERY)
public class Owner extends AbstractEntity implements Serializable {
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
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "owner")
    private List<Pet> pet;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(firstName, owner.firstName) &&
                Objects.equals(lastName, owner.lastName) &&
                Objects.equals(email, owner.email) &&
                Objects.equals(phone, owner.phone) &&
                Objects.equals(pet, owner.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, phone, pet);
    }

    @Override
    public String toString() {
        return String.format("Owner{firstName='%s', lastName='%s', email='%s', phone='%s', pet=%s}", firstName, lastName, email, phone, pet);
    }
}
