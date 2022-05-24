package Entities;

import Entities.Specification.CatColour;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name= "cat")
public class Cat {
    @Id
    @GeneratedValue
    @Column(name="CAT_ID")
    private long id;

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "DateOfBirth")
    @NotNull
    private Calendar dateOfBirth;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "Breed")
    @Enumerated(EnumType.STRING)
    private CatColour breed;

    @ManyToMany
    private Set<Cat> friends = new HashSet<>();
    public Cat() {

    }
    public Cat(String name) {
        this();
        this.name = name;
    }
    public Cat(String name, Calendar dateOfBirth,Owner owner, CatColour breed) {
        this();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.owner = owner;
        this.breed = breed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        this.owner.addCat(this);
    }

    public Owner getOwner() {
        return owner;
    }

    public CatColour getBreed() {
        return breed;
    }

    public void setBreed(CatColour breed) {
        this.breed = breed;
    }

    public Set<Cat> getFriends() {
        return friends;
    }

    public void setFriends(Cat friend) {
        friend.getFriends().add(this);
        friends.add(friend);
    }

    @Override
    public String toString() {
        return name;
    }
}