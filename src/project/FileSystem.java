package project;

import java.io.*;

public class FileSystem {

    private static final String FILE_NAME = "hotel.dat";

    // SAVE DATA
    public static void save(holder h) {

        try {

            FileOutputStream file = new FileOutputStream(FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(h);

            out.close();
            file.close();

        }
        catch(IOException e) {

            System.out.println("Error saving data!");
        }
    }

    // LOAD DATA
    public static holder load() {

        holder h = null;

        try {

            FileInputStream file = new FileInputStream(FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(file);

            h = (holder) in.readObject();

            in.close();
            file.close();

        }
        catch(Exception e) {

            h = new holder(); // create new if file not exist
        }

        return h;
    }
}
