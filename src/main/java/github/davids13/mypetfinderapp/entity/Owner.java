package github.davids13.mypetfinderapp.entity;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "owners")
public class Owner extends AbstractEntity implements Serializable {

    public static final String OWNER_FIND_ALL = "Owner.findAll";
    public static final String OWNER_FIND_ALL_QUERY = "SELECT o FROM Owner o";

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    @Email
    private String email;
    @Column
    private String phone;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(firstName, owner.firstName) &&
                Objects.equals(lastName, owner.lastName) &&
                Objects.equals(email, owner.email) &&
                Objects.equals(phone, owner.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phone);
    }

    @Override
    public String toString() {
        return String.format("Owner{firstName='%s', lastName='%s', email='%s', phone='%s'}", firstName, lastName, email, phone);
    }
}
