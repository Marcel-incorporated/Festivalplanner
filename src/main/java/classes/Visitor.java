package classes;

import interfaces.Animated;
import net.datafaker.Faker;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * constructor voor een visitor
 */
public class Visitor implements Serializable, Animated {
    private String name;
    private int age;
    private String gender;
    private String email;
    private List<Visitor> visitorList;
    private int animationStatus = 1;
    @Serial
    private static final long serialVersionUID = -8515717981900000714L;


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
    public void update() {
        if (animationStatus < 4) {
            animationStatus++;
        } else {
            animationStatus = 1;
        }
    }

    @Override
    public int getAnimationStatus() {
        return animationStatus;
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
