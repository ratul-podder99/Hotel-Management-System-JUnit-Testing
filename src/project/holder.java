package project;

import java.io.Serializable;

public class holder implements Serializable {

    private static final long serialVersionUID = 1L;

    // Luxury Double Rooms → Room 101–110
    Doubleroom luxuryDouble[] = new Doubleroom[10];

    // Deluxe Double Rooms → Room 111–130
    Doubleroom deluxeDouble[] = new Doubleroom[20];

    // Luxury Single Rooms → Room 131–140
    Singleroom luxurySingle[] = new Singleroom[10];

    // Deluxe Single Rooms → Room 141–150
    Singleroom deluxeSingle[] = new Singleroom[10];
}
