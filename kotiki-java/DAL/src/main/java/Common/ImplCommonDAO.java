package Common;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class ImplCommonDAO<E extends CommonEntity> implements CommonDAO<E>{
    @SuppressWarnings("unchecked")
    protected Class<E> persistClass = (Class<E>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

    protected SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    @Override
    public E find(Long id) {
        Transaction transaction = null;
        E result;
        try(var session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            var criteria = session.createCriteria(persistClass);
            criteria.add(Restrictions.idEq(id));
            result = (E) criteria.uniqueResult();
            transaction.commit();
        }
        catch (Exception exception){
            if (transaction != null){
                transaction.rollback();
            }
            throw exception;
        }
        return result;
    }


    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        E result;
        try(var session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            var criteria = session.createCriteria(persistClass);
            criteria.add(Restrictions.idEq(id));
            result = (E) criteria.uniqueResult();
            session.delete(result);
            transaction.commit();
        }
        catch (Exception exception){
            if (transaction != null){
                transaction.rollback();
            }
            throw exception;
        }
    }

    @Override
    public List findAll() {
        Transaction transaction = null;
        List<E> result;
        try(var session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(persistClass);
            result = criteria.list();
        } catch(Exception exception) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw exception;
        }
        return result;
    }

    @Override
    public void save(CommonEntity entity) {
        Transaction transaction = null;
        try(var session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch(Exception exception) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw exception;
        }


    }

    @Override
    public E merge(E entity) {
        Transaction transaction = null;
        E result = null;
        try (var session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = (E) session.merge(entity);
            transaction.commit();
        } catch(Exception exception) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw exception;
        }
        return result;
    }

}