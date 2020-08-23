package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        //Создание таблицы
        userService.createUsersTable();
        //Создание леммингов
        userService.saveUser("Jhon", "Starsk", (byte) 35);
        System.out.println("Jhon добавлен в базу");
        userService.saveUser("Steve", "Mius", (byte) 15);
        System.out.println("Steve добавлен в базу");
        userService.saveUser("Shinie", "Corsa", (byte) 29);
        System.out.println("Shinie добавлен в базу");
        userService.saveUser("Rick", "Pickles", (byte) 41);
        System.out.println("Rick добавлен в базу");

        //Вывод на консоль
        userService.getAllUsers().stream().forEach(User::toString);
        //Очистка таблицы
        userService.cleanUsersTable();
        //Удаление таблицы
        userService.dropUsersTable();
    }
}
