package owners;

import Common.ImplCommonDAO;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ImplOwnerDAO extends ImplCommonDAO<Owner> implements OwnerDAO {
    @Override
    public Owner findByName(String name) {
        Transaction transaction = null;
        Owner result;
        try {
            Session session = null;
            try {
                session = getSessionFactory().getCurrentSession();
            } catch(HibernateException e) {
                session = getSessionFactory().openSession();
            }
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Owner> cr = cb.createQuery(Owner.class);
            Root<Owner> root = cr.from(Owner.class);
            Predicate[] predicates = new Predicate[1];
            predicates[0] = cb.equal(
                    cb.upper(root.get("name")), name.toUpperCase());
            cr.select(root).where(predicates);

            Query<Owner> query = session.createQuery(cr);

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
