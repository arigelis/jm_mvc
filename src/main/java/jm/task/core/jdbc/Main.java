package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // JDBC{
//        // реализуйте алгоритм здесь
//        UserService userService = new UserServiceImpl();
//        //Создание таблицы
//        userService.createUsersTable();
//        //Создание леммингов
//        userService.saveUser("Jhon", "Starsk", (byte) 35);
//        System.out.println("Jhon добавлен в базу");
//        userService.saveUser("Steve", "Mius", (byte) 15);
//        System.out.println("Steve добавлен в базу");
//        userService.saveUser("Shinie", "Corsa", (byte) 29);
//        System.out.println("Shinie добавлен в базу");
//        userService.saveUser("Rick", "Pickles", (byte) 41);
//        System.out.println("Rick добавлен в базу");
//
//        //Вывод на консоль
//        userService.getAllUsers().stream().forEach(User::toString);
//        //Очистка таблицы
//        userService.cleanUsersTable();
//        //Удаление таблицы
//        userService.dropUsersTable();
        // JDBC}

        // HIBERNATE{
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("Viewing entity " + o);
                }
            }
        } finally {
            session.close();
        }
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        List<User> userList = dao.getAllUsers();
        System.out.println("Users size " + userList.size());
        // HIBERNATE}
    }
}
