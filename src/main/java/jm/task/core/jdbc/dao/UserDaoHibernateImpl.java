package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.HibernateUtil;
import jm.task.core.jdbc.util.UsersEntity;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = null;
        try{
            User user = new User();
            session = HibernateUtil.openSession();
            session.delete(user);
        }
        finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
//        userService.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
//        userService.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try{
            User user = new User();
            user.setId(id);
            session = HibernateUtil.openSession();
            session.delete(user);
        }
        finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            //session.createQuery("from UsersEntity");
            users = session.createCriteria(UsersEntity.class).list();
        }
        finally {
            if (session != null){
                session.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
//        userService.cleanUsersTable();
    }
}
