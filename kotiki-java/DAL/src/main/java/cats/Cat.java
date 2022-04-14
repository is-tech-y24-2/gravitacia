package cats;


import Common.CommonEntity;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;
import owners.Owner;

import javax.persistence.*;
import java.awt.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Table(name = "cats")

public class Cat extends CommonEntity {

    @Id
    @GeneratedValue
    @Column(name="cat_id")
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
    private Cat owner;

    @Column(name = "colour")
    @Enumerated(EnumType.STRING)
    private CatColour colour;

    @ManyToMany
    private Set<Cat> friends = new HashSet<>();
    public Cat(){
    }

    public Cat(String name){
        this.name = name;
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
