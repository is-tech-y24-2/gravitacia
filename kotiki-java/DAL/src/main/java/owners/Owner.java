package owners;

import Common.CommonEntity;
import cats.Cat;
import cats.ImplCatDAO;
import com.sun.istack.NotNull;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Table(name = "owners", uniqueConstraints = {@UniqueConstraint(columnNames = {"Name"})})

public class Owner extends CommonEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Name", unique = true)
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

    private ImplOwnerDAO implOwnerDAO;

    public Owner() {

    }


    protected SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }


    public Owner(String name){
        this.name = name;
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

    public void addCats(Cat cat) throws Exception {

        if (cats.contains(cat)){
            throw new Exception("Warning! This cat is already exist");
        }
        else{
            cats.add(cat);
        }
    }
}
