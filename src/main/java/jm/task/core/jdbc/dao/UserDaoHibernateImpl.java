package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = Util.openSession();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (\n" +
                    "    id INTEGER AUTO_INCREMENT PRIMARY KEY, \n" +
                    "    firstname VARCHAR(30), \n" +
                    "    lastname VARCHAR(30), \n" +
                    "    age INTEGER\n" +
                    ");").executeUpdate();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.openSession();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = Util.openSession();
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.flush();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            User user = new User();
            user.setId(id);
            session = Util.openSession();
            session.delete(user);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        Session session = null;
        try {
            session = Util.openSession();
            users = session.createQuery("from User").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = Util.openSession();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
