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
                    ");");
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
            session.createSQLQuery("DROP TABLE IF EXISTS users");
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
            session.createSQLQuery(String.format("INSERT INTO users (firstname, lastname ,age) VALUES (%s,%s,%s)", "'" + name + "'", "'" + lastName + "'", "'" + age + "'"));
        } catch (Exception e) {
            e.printStackTrace();
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
            session = Util.openSession();
            session.createSQLQuery(String.format("delete from users where id = " + id));
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
            users = session.createCriteria(User.class).list();
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
            session.createSQLQuery("TRUNCATE TABLE users");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
