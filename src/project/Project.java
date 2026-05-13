package project;

import java.util.Scanner;

public class Project {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        while (true) {

            System.out.println("\n========= HOTEL MANAGEMENT SYSTEM =========");
            System.out.println("1. Display Room Details");
            System.out.println("2. Display Room Availability");
            System.out.println("3. Book Room");
            System.out.println("4. Order Food");
            System.out.println("5. Checkout");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    Hotel.displayRoomDetails();
                    break;

                case 2:
                    Hotel.displayAvailability();
                    break;

                case 3:
                    Hotel.bookRoom();
                    break;

                case 4:
                    Hotel.orderFood();
                    break;

                case 5:
                    Hotel.checkout();
                    break;

                case 6:
                    Hotel.cancelBooking();
                    break;

                case 7:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
