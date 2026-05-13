package project;

import java.util.Scanner;

public class Hotel {

    // ✅ LOAD saved data instead of new holder
    static holder h = FileSystem.load();

    static Scanner sc = new Scanner(System.in);

    // Convert real room number → array index
    static int getIndex(int type, int roomNo) {

        if(type == 1 && roomNo >= 101 && roomNo <= 110)
            return roomNo - 101;

        if(type == 2 && roomNo >= 111 && roomNo <= 130)
            return roomNo - 111;

        if(type == 3 && roomNo >= 131 && roomNo <= 140)
            return roomNo - 131;

        if(type == 4 && roomNo >= 141 && roomNo <= 150)
            return roomNo - 141;

        return -1;
    }

 // Display room details (FULL FORMAT)
    public static void displayRoomDetails() {

        System.out.println("\nChoose room type:");
        System.out.println("1. Luxury Double Room");
        System.out.println("2. Deluxe Double Room");
        System.out.println("3. Luxury Single Room");
        System.out.println("4. Deluxe Single Room");

        System.out.print("\nEnter room type: ");
        int type = sc.nextInt();

        switch(type) {

            case 1:
                System.out.println("\nLuxury Double Room (AC)");
                System.out.println("Room Numbers : 101 - 110");
                System.out.println("Number of double beds : 1");
                System.out.println("AC : Yes");
                System.out.println("Free breakfast : Yes");
                System.out.println("Charge per day : 4000 TK");
                break;

            case 2:
                System.out.println("\nDeluxe Double Room (Non-AC)");
                System.out.println("Room Numbers : 111 - 130");
                System.out.println("Number of double beds : 1");
                System.out.println("AC : No");
                System.out.println("Free breakfast : Yes");
                System.out.println("Charge per day : 3000 TK");
                break;

            case 3:
                System.out.println("\nLuxury Single Room (AC)");
                System.out.println("Room Numbers : 131 - 140");
                System.out.println("Number of single beds : 1");
                System.out.println("AC : Yes");
                System.out.println("Free breakfast : Yes");
                System.out.println("Charge per day : 2200 TK");
                break;

            case 4:
                System.out.println("\nDeluxe Single Room (Non-AC)");
                System.out.println("Room Numbers : 141 - 150");
                System.out.println("Number of single beds : 1");
                System.out.println("AC : No");
                System.out.println("Free breakfast : Yes");
                System.out.println("Charge per day : 1200 TK");
                break;

            default:
                System.out.println("Invalid room type!");
        }
    }


    // Display availability
    public static void displayAvailability() {

        System.out.println("\nLuxury Double Available: " + countAvailable(h.luxuryDouble));
        System.out.println("Deluxe Double Available: " + countAvailable(h.deluxeDouble));
        System.out.println("Luxury Single Available: " + countAvailable(h.luxurySingle));
        System.out.println("Deluxe Single Available: " + countAvailable(h.deluxeSingle));
    }

    // Count empty rooms
    public static int countAvailable(Object rooms[]) {

        int count = 0;

        for(Object r : rooms)
            if(r == null)
                count++;

        return count;
    }

    // VALIDATED INPUT METHODS

    static String inputName(String msg) {

        while(true) {

            System.out.print(msg);
            String name = sc.nextLine();

            if(Validator.isValidName(name))
                return name;

            System.out.println("Invalid name! Letters only.");
        }
    }

    static String inputPhone(String msg) {

        while(true) {

            System.out.print(msg);
            String phone = sc.nextLine();

            if(Validator.isValidPhone(phone))
                return phone;

            System.out.println("Invalid BD phone! Example: 017XXXXXXXX");
        }
    }

    static String inputGender(String msg) {

        while(true) {

            System.out.print(msg);
            String gender = sc.nextLine();

            if(Validator.isValidGender(gender))
                return gender;

            System.out.println("Invalid gender! Enter Male or Female.");
        }
    }

    // Book room
    public static void bookRoom() {

        System.out.println("\nRoom Type:");
        System.out.println("1. Luxury Double (101–110)");
        System.out.println("2. Deluxe Double (111–130)");
        System.out.println("3. Luxury Single (131–140)");
        System.out.println("4. Deluxe Single (141–150)");

        System.out.print("Enter room type: ");
        int type = sc.nextInt();

        System.out.print("Enter room number: ");
        int roomNo = sc.nextInt();

        int num = getIndex(type, roomNo);

        if(num == -1) {
            System.out.println("Invalid room number!");
            return;
        }

        sc.nextLine();

        try {

            if(type == 1 || type == 2) {

                String n1 = inputName("Enter name1: ");
                String c1 = inputPhone("Enter contact1: ");
                String g1 = inputGender("Enter gender1: ");

                String n2 = inputName("Enter name2: ");
                String c2 = inputPhone("Enter contact2: ");
                String g2 = inputGender("Enter gender2: ");

                Doubleroom d = new Doubleroom(n1,c1,g1,n2,c2,g2);

                if(type == 1)
                    h.luxuryDouble[num] = d;
                else
                    h.deluxeDouble[num] = d;
            }

            else {

                String n = inputName("Enter name: ");
                String c = inputPhone("Enter contact: ");
                String g = inputGender("Enter gender: ");

                Singleroom s = new Singleroom(n,c,g);

                if(type == 3)
                    h.luxurySingle[num] = s;
                else
                    h.deluxeSingle[num] = s;
            }

            FileSystem.save(h);

            System.out.println("Room Booked Successfully!");

        }
        catch(Exception e) {
            System.out.println("Error booking room!");
        }
    }

    // Order food
    public static void orderFood() {

        System.out.print("Enter room type: ");
        int type = sc.nextInt();

        System.out.print("Enter room number: ");
        int roomNo = sc.nextInt();

        int num = getIndex(type, roomNo);

        if(num == -1) {
            System.out.println("Invalid room number!");
            return;
        }

        boolean booked = false;

        if(type==1 && h.luxuryDouble[num]!=null) booked = true;
        if(type==2 && h.deluxeDouble[num]!=null) booked = true;
        if(type==3 && h.luxurySingle[num]!=null) booked = true;
        if(type==4 && h.deluxeSingle[num]!=null) booked = true;

        if(!booked) {
            System.out.println("Room not booked!");
            return;
        }

        Food f = new Food();

        if(type==1) h.luxuryDouble[num].food.add(f);
        if(type==2) h.deluxeDouble[num].food.add(f);
        if(type==3) h.luxurySingle[num].food.add(f);
        if(type==4) h.deluxeSingle[num].food.add(f);

        FileSystem.save(h);

        System.out.println("Food ordered successfully!");
    }

    // Checkout
    public static void checkout() {

        System.out.print("Enter room type: ");
        int type = sc.nextInt();

        System.out.print("Enter room number: ");
        int roomNo = sc.nextInt();

        int num = getIndex(type, roomNo);

        if(num == -1) {
            System.out.println("Invalid room number!");
            return;
        }

        try {

            float total = Bill.calculate(type, num, h);

            FileSystem.save(h);

            System.out.println("Checkout successful!");
            System.out.println("Total Bill: " + total + " TK");

        }
        catch(Exception e) {
            System.out.println("Room not booked!");
        }
    }

    // Cancel booking
    public static void cancelBooking() {

        System.out.print("Enter room type: ");
        int type = sc.nextInt();

        System.out.print("Enter room number: ");
        int roomNo = sc.nextInt();

        int num = getIndex(type, roomNo);

        if(num == -1) {
            System.out.println("Invalid room number!");
            return;
        }

        try {

            if(type==1) h.luxuryDouble[num] = null;
            if(type==2) h.deluxeDouble[num] = null;
            if(type==3) h.luxurySingle[num] = null;
            if(type==4) h.deluxeSingle[num] = null;

            FileSystem.save(h);

            System.out.println("Booking cancelled successfully!");

        }
        catch(Exception e) {
            System.out.println("Error cancelling booking!");
        }
    }
}
