package project;

import java.io.Serializable;
import java.util.ArrayList;

public class Doubleroom implements Serializable {

    String name1, contact1, gender1;
    String name2, contact2, gender2;

    ArrayList<Food> food = new ArrayList<>();

    Doubleroom(String n1, String c1, String g1,
               String n2, String c2, String g2) {

        name1 = n1;
        contact1 = c1;
        gender1 = g1;

        name2 = n2;
        contact2 = c2;
        gender2 = g2;
    }

    public float getFoodBill() {

        float total = 0;

        for(Food f : food)
            total += f.getPrice();

        return total;
    }
}
