package Entities;


import Entities.Specification.CatColour;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cat_id")
    private Long id;

    @Column(name = "Name", unique = true)
    @NotNull
    private String name;

    @Column(name = "DateOfBirth")
    @NotNull
    private Calendar dateOfBirth;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "colour")
    @Enumerated(EnumType.STRING)
    private CatColour colour;

    @ManyToMany(cascade=CascadeType.ALL)
    private Set<Cat> friends = new HashSet<>();
    

    public Cat(){

    }

    public Cat(String name){
        this.name = name;
    }

    public Cat(String name, Calendar dateOfBirth, CatColour colour) {
            this.name = name;
            this.dateOfBirth = dateOfBirth;
            this.colour = colour;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Calendar getBirth() {
        return this.dateOfBirth;
    }

    public Set<Cat> getFriends(){
        return this.friends;
    }

    public void setFriends(Cat cat){
        cat.getFriends().add(this);
        friends.add(cat);
    }

    public CatColour getColour(){
        return this.colour;
    }

    public void setColour(CatColour catColour){
        this.colour = colour;
    }

}
