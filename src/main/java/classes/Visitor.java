package classes;

import java.io.Serializable;
import java.util.List;
import net.datafaker.Faker;

public class Visitor implements Serializable {
    private String name;
    private int age;
    private String gender;
    private String email;

    private List<Visitor> visitorList;

    public Visitor() {
        Faker faker = new Faker();
        this.name = faker.name().firstName() + " " + faker.name().lastName();
        this.age = ((int) (Math.random() * (100 - 13)) + 13);
        if ((int) (Math.random() * 2) == 1) {
            this.gender = "male";
        } else {
            this.gender = "female";
        }
        //DISCRIMINATIE WANT GENDER VAATWASSER IS ER NIET!!!!!11111
        this.email = faker.internet().emailAddress();
    }

    public void makeVisitor() {

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
