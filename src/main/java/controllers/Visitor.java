package controllers;

import java.io.Serializable;

public class Visitor implements Serializable {
    private String name;
    private int age;
    private String gender;
    private String email;

    public Visitor(String name, int age, String gender, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    @Override
    public String toString() {
        return "controllers.Visitor{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
