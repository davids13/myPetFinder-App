package github.davids13.mypetfinderapp.entity;

import github.davids13.mypetfinderapp.commons.jpa.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "localizations")
@NamedQuery(name = Localization.LOCALIZATION_FIND_ALL, query = Localization.LOCALIZATION_FIND_ALL_QUERY)
public class Localization extends AbstractEntity implements Serializable {

    public static final String LOCALIZATION_FIND_ALL = "Localization.findAll";
    public static final String LOCALIZATION_FIND_ALL_QUERY = "SELECT l FROM Localization l";

    @Column
    private String country;
    @Column
    private String city;
    @Column(name = "lost_zone")
    private String zone;
    @Column(name = "lost_date")
    @Temporal(TemporalType.DATE)
    private Date lostDate;

    public Localization() {
    }

    public Localization(Integer id, String country, String city, String zone, Date lostDate) {
        super(id);
        this.country = country;
        this.city = city;
        this.zone = zone;
        this.lostDate = lostDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Date getLostDate() {
        return lostDate;
    }

    public void setLostDate(Date lostDate) {
        this.lostDate = lostDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Localization that = (Localization) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(city, that.city) &&
                Objects.equals(zone, that.zone) &&
                Objects.equals(lostDate, that.lostDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country, city, zone, lostDate);
    }

    @Override
    public String toString() {
        return String.format("Localization{country='%s', city='%s', zone='%s', lostDate=%s}", country, city, zone, lostDate);
    }
}
