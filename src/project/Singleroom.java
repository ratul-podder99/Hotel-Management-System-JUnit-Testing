package project;

import java.io.Serializable;
import java.util.ArrayList;

public class Singleroom implements Serializable {

    String name;
    String contact;
    String gender;

    ArrayList<Food> food = new ArrayList<>();

    Singleroom(String name, String contact, String gender) {

        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }

    public float getFoodBill() {

        float total = 0;

        for(Food f : food)
            total += f.getPrice();

        return total;
    }
}
