package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.UsersEntity;
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
            User user = new User();
            session = Util.openSession();
            session.delete(user);
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
            //session.createQuery("from UsersEntity");
            users = session.createCriteria(UsersEntity.class).list();
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
