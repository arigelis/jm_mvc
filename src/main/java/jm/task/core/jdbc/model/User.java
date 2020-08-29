package jm.task.core.jdbc.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "usersdb")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {

    }

    //    @Id
//    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //    @Column(name = "firstname")
    public String getName() {
        return firstname;
    }

    public void setName(String name) {
        this.firstname = name;
    }

//    @Basic
//    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    @Basic
//    @Column(name = "age")
    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public User(String name, String lastName, Byte age) {
        this.firstname = name;
        this.lastName = lastName;
        this.age = age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
