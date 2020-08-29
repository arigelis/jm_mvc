package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Properties properties;

    public static Connection getConnection() throws ClassNotFoundException, IOException {
        getProperties();

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        Class.forName("com.mysql.cj.jdbc.Driver");

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getProperties() throws IOException {
        properties = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException(propFileName + " not found!");
        }
    }

    public static Session openSession() {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/usersdb?serverTimezone=Europe/Moscow&amp");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "01v891ea");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.addAnnotatedClass(User.class);
//            configuration.getClassMapping("jm.task.core.jdbc.model.User");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return configuration.buildSessionFactory().openSession();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
