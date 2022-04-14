import cats.Cat;
import owners.Owner;

import java.util.ArrayList;
import java.util.List;

public class main {

    Owner owner = new Owner();
    Cat cat1 = new Cat();
    Cat cat2 = new Cat();
    List<Cat> cats = new ArrayList<>();

    public void AddCats(){
        owner.setName("Jon");
        cat1.setName("Purp");
        cat2.setName("Lurp");
        cat1.setFriends(cat2);
    }
}
