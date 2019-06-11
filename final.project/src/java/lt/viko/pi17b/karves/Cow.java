/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.pi17b.karves;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Simonas
 */
@Entity
@Table(name = "cow")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cow.findAll", query = "SELECT c FROM Cow c"),
    @NamedQuery(name = "Cow.findById", query = "SELECT c FROM Cow c WHERE c.id = :id"),
    @NamedQuery(name = "Cow.findByName", query = "SELECT c FROM Cow c WHERE c.name = :name"),
    @NamedQuery(name = "Cow.findByAnimalCode", query = "SELECT c FROM Cow c WHERE c.animalCode = :animalCode"),
    @NamedQuery(name = "Cow.findByBreed", query = "SELECT c FROM Cow c WHERE c.breed = :breed"),
    @NamedQuery(name = "Cow.findByCountry", query = "SELECT c FROM Cow c WHERE c.country = :country"),
    @NamedQuery(name = "Cow.findByAge", query = "SELECT c FROM Cow c WHERE c.age = :age"),
    @NamedQuery(name = "Cow.findByWeight", query = "SELECT c FROM Cow c WHERE c.weight = :weight")})
public class Cow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "animalCode")
    private int animalCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "breed")
    private String breed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weight")
    private double weight;

    public Cow() {
    }

    public Cow(Integer id) {
        this.id = id;
    }

    public Cow(Integer id, String name, int animalCode, String breed, String country, int age, double weight) {
        this.id = id;
        this.name = name;
        this.animalCode = animalCode;
        this.breed = breed;
        this.country = country;
        this.age = age;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnimalCode() {
        return animalCode;
    }

    public void setAnimalCode(int animalCode) {
        this.animalCode = animalCode;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cow)) {
            return false;
        }
        Cow other = (Cow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lt.viko.pi17b.karves.Cow[ id=" + id + " ]";
    }
    
}
