package ru.dekrement.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dekrement.dao.UserDAO;
import ru.dekrement.model.User;

import org.slf4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.util.List;

/**
 * Created by web on 06.03.2017.
 */
@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private SessionFactory sf;

    @Transactional
    public User getUserById(Integer id) {
        Session session = sf.getCurrentSession();
        User user = (User) session.load(User.class, id);
        logger.info("user loaded: " + user);
        return user;
    }

    @Transactional
    public void deleteUser(Integer id) {
        sf.getCurrentSession().delete(getUserById(id));
    }

    @Transactional
    public void addUser(User user) {
        Session session = sf.getCurrentSession();
        user.setCreateDate(new Date(new java.util.Date().getTime()));
        session.persist(user);
        logger.info("User saved successfully, User details: " + user);

    }

    @Transactional
    @SuppressWarnings("unchecked")
    public synchronized List<User> listUsers(String name, Integer offset, Integer maxResult) {
        synchronized (UserDAOImpl.class) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            logger.info("start listUsers");
            CriteriaBuilder criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot);
            criteriaQuery.distinct(true);

            if (name != null) {
                criteriaQuery.where(criteriaBuilder.equal(userRoot.<String>get("name"), name));
            }

            criteriaQuery.select(userRoot);
            criteriaQuery.orderBy(criteriaBuilder.desc(userRoot.<Long>get("id")));

            logger.info("end listUsers");
            List<User> result = entityManager.createQuery(criteriaQuery)
                    .setFirstResult(offset)
                    .setMaxResults(maxResult)
                    .getResultList();
            return result;
        }
    }

    @Transactional
    public Long count(String name) {
        synchronized (UserDAOImpl.class) {
            logger.info("user count");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(criteriaBuilder.count(userRoot));
            if (name != null) {
                criteriaQuery.where(criteriaBuilder.equal(userRoot.<String>get("name"), name));
            }

            Long result = entityManager.createQuery(criteriaQuery).getResultList().get(0);
            return result;
        }
    }


    public void removeUser(int id) {

    }

    @Transactional
    public void updateUser(User user) {
        Session session = sf.getCurrentSession();
        User userOriginal = getUserById(user.getId());
        userOriginal.setAdmin(user.isAdmin());
        userOriginal.setAge(user.getAge());
        userOriginal.setName(user.getName());
        session.update(userOriginal);
    }
}
