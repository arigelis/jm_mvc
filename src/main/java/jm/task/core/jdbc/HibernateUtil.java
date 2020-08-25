package jm.task.core.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    /** The session factory. */
    private static SessionFactory sessionFactory = null;

    /**
     * Open session.
     *
     * @return the session
     */
    public static Session openSession() {
        if (sessionFactory == null) {
            final Configuration configuration = new Configuration();

            sessionFactory = configuration.buildSessionFactory( new StandardServiceRegistryBuilder().build() );
        }
        return sessionFactory.openSession();
       /* try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();

            return sessionFactory.openSession();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }*/
    }
}
