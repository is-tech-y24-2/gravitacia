package cats;


import Common.CommonEntity;
import com.sun.istack.NotNull;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.cfg.Configuration;
import owners.Owner;

import javax.persistence.*;
import java.awt.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cats")
public class Cat extends CommonEntity {
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

    @Override
    public String getName() {
        return this.name;
    }



    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
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
}
