package duke;

/**
 * Handles user interaction
 */

public class Ui {
    private static String borderline = "____________________________________________________________";

    /**
     * Displays greeting to the user upon start
     */

    public String greeting() {
        return "Hey! I'm Hari!\nHow may I be of service today?";
    }

    public void displayLoadError() {
        System.out.println("Error loading data from file! File may be corrupted or has format issues.");
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greeting();
    }

}
