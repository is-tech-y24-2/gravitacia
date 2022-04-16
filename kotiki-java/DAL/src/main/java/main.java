import cats.Cat;
import owners.Owner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static cats.CatColour.white;

public class main {


    Calendar calendar = new GregorianCalendar(2017, Calendar.JANUARY , 25);
    Owner owner = new Owner();
    Cat cat1 = new Cat("Purp", calendar, white);
    Cat cat2 = new Cat("Purp", calendar, white);
    List<Cat> cats = new ArrayList<>();

    public void AddCats() throws Exception {
        owner.setName("Jon");
        cat1.setFriends(cat2);
        owner.addCats(cat1);
        owner.addCats(cat2);
    }


}
