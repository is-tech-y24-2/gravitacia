package cats;

import Common.ImplCommonDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ImplCatDAO extends ImplCommonDAO<Cat> implements CatDAO {

    @Override
    public Cat findByName(String name) {
        Transaction transaction = null;
        Cat result;
        try {
            Session session = null;
            try {
                session = getSessionFactory().getCurrentSession();
            } catch(HibernateException e) {
                session = getSessionFactory().openSession();
            }
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Cat> cr = cb.createQuery(Cat.class);
            Root<Cat> root = cr.from(Cat.class);
            Predicate[] predicates = new Predicate[1];
            predicates[0] = cb.equal(
                    cb.upper(root.get("name")), name.toUpperCase());
            cr.select(root).where(predicates);

            Query<Cat> query = session.createQuery(cr);

            result = query.getSingleResult();
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return result;

    }
}
