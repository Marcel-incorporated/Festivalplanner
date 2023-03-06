package classes;

import java.io.Serializable;
import java.util.List;
import net.datafaker.Faker;

public class Visitor implements Serializable {
    private String name;
    private int age;
    private String gender;
    private String email;

    public Visitor() {
        Faker faker = new Faker();
        this.name = faker.name().firstName() + " " + faker.name().lastName();
        this.age = ((int) (Math.random() * (100 - 13)) + 13);
        if ((int) (Math.random() * 2) == 1) {
            this.gender = "male";
        } else {
            this.gender = "female";
        }
        this.email = faker.internet().emailAddress();
    }

    @Override
    public String toString() {
        return "classes.Visitor{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
