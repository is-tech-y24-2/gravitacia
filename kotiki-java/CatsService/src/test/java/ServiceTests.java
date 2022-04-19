import Entities.Cat;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import Entities.Owner;


import java.util.*;

import static Entities.Specification.CatColour.white;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class ServiceTests {
    private static ImplOwnerDAO _implOwnerDAO;
    private static ImplCatDAO _implCatDAO;

    @BeforeClass
    public static void setup() {
        _implCatDAO = Mockito.mock(ImplCatDAO.class);
        _implOwnerDAO = Mockito.mock(ImplOwnerDAO.class);
    }

    @Test
    public void FindAllCats(){
        List<Cat> cats = new ArrayList<>();

        Owner first = new Owner();
        Owner second = new Owner();
        first.setName("Alex");
        second.setName("Jhon");

        Cat firstCat = new Cat();
        Cat secondCat = new Cat();

        firstCat.setName("Purp");
        secondCat.setName("Glurp");

        cats.add(firstCat);
        cats.add(secondCat);

        given(_implCatDAO.findAll()).willReturn(cats);
        List<Cat> expected = _implCatDAO.findAll();

        assertEquals(expected, cats);
    }

    @Test
    public void ReturnOwnerById(){
        Owner first = new Owner();
        first.setName("Alex");
        Long Id = first.getId();

        given(_implOwnerDAO.find(Id)).willReturn(first);
        Owner expected = _implOwnerDAO.find(Id);
        assertEquals(expected, first);
    }

    @Test
    public void ReturnAllFriends(){
        Cat cat = new Cat("Purp");
        Cat cat1 = new Cat("Glurp");
        Cat cat2 = new Cat("Smurp");
        Cat cat3 = new Cat("Kurp");
        Cat cat4 = new Cat("Durp");

        cat.setFriends(cat1);
        cat.setFriends(cat2);
        cat.setFriends(cat3);
        cat.setFriends(cat4);

        Long Id = cat.getId();

        given(_implCatDAO.find(Id)).willReturn(cat);

        Set<Cat> friends = new HashSet<>();
        friends.add(cat1);
        friends.add(cat2);
        friends.add(cat3);
        friends.add(cat4);

        Set<Cat> expected = _implCatDAO.find(Id).getFriends();
        Set<Cat> expected1 = new HashSet<>();
        expected1.add(cat);
        Set<Cat> expected2 = new HashSet<>();
        expected2.add(cat);
        assertEquals(expected, friends);
        assertEquals(expected1, cat1.getFriends());
        assertEquals(expected2, cat2.getFriends());
    }

    @Test
    public void TwoSimilarCats() throws Exception{

        Calendar calendar = new GregorianCalendar(2017, Calendar.JANUARY , 25);

        Owner owner1 = new Owner("Max");
        Owner owner2 = new Owner("Max");
        Cat cat1 = new Cat("Purp", calendar, white);
        Cat cat2 = new Cat("Purp", calendar, white);
        owner1.addCats(cat1);
        owner1.addCats(cat2);

    }
}