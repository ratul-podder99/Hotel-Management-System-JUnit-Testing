package project;

public class Validator {

    // Name validation
    public static boolean isValidName(String name) {

        return name.matches("[a-zA-Z ]+");
    }

    // Bangladesh phone validation
    public static boolean isValidPhone(String phone) {

        return phone.matches("01[3-9][0-9]{8}");
    }

    // Gender validation
    public static boolean isValidGender(String gender) {

        return gender.equalsIgnoreCase("male")
            || gender.equalsIgnoreCase("female");
    }
}
