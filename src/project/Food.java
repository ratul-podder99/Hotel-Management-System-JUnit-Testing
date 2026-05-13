package project;

import java.io.Serializable;
import java.util.Scanner;

public class Food implements Serializable {

    int itemno;
    int quantity;
    float price;

    Food() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n========== Food Menu (TK) ==========");
        System.out.println("1. Tea       - 20 TK");
        System.out.println("2. Coffee    - 40 TK");
        System.out.println("3. Sandwich  - 50 TK");
        System.out.println("4. Pasta     - 60 TK");
        System.out.println("5. Noodles   - 70 TK");
        System.out.println("6. Coke      - 30 TK");

        System.out.print("Enter item number: ");
        itemno = sc.nextInt();

        System.out.print("Enter quantity: ");
        quantity = sc.nextInt();

        switch(itemno) {

            case 1:
                price = quantity * 20;
                break;

            case 2:
                price = quantity * 40;
                break;

            case 3:
                price = quantity * 50;
                break;

            case 4:
                price = quantity * 60;
                break;

            case 5:
                price = quantity * 70;
                break;

            case 6:
                price = quantity * 30;
                break;

            default:
                System.out.println("Invalid item selected.");
                price = 0;
        }
    }

    public float getPrice() {

        return price;
    }
}
