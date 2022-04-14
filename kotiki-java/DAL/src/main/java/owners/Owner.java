package owners;

import Common.CommonEntity;
import cats.Cat;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Table(name = "owner")

public class Owner extends CommonEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "DateOfBirth")
    @NotNull
    private Calendar dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<Cat> cats = new ArrayList<>();

    public List<Cat> getCats() {
        return cats;
    }

    public Owner() {
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long Id) {
        id = Id;

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
}
