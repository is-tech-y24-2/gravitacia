import cats.Cat;
import cats.ImplCatDAO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import owners.ImplOwnerDAO;
import owners.Owner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static cats.CatColour.black;
import static cats.CatColour.white;

public class Main {
    private static SessionFactory _sessionFactory;
    public static void main(String[] args) throws Exception {

        ImplCatDAO implCatDAO = new ImplCatDAO();
        ImplOwnerDAO implOwnerDAO = new ImplOwnerDAO();
        Main mn = new Main();
        _sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Calendar calendar = new GregorianCalendar(2014, Calendar.JANUARY, 25);
        Owner owner = new Owner("Alex");
        Cat cat1 = new Cat("Purp", calendar, black);
        Cat cat2 = new Cat("Pup", calendar, black);
        Cat cat3 = new Cat("Lup", calendar, black);
        List<Cat> cats = new ArrayList<>();
        cat1.setFriends(cat2);
        cat1.setFriends(cat3);

        implOwnerDAO.save(owner);
        implCatDAO.save(cat2);
        implCatDAO.save(cat1);
        implCatDAO.save(cat3);

        owner.addCats(cat1);
        owner.addCats(cat2);
        owner.addCats(cat3);


        implCatDAO.merge(cat1);
        implCatDAO.merge(cat2);
        implCatDAO.merge(cat3);
        implOwnerDAO.merge(owner);
    }
}
