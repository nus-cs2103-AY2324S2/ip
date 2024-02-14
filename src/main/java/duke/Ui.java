package duke;

/**
 * Handles user interaction
 */

public class Ui {
    private static String borderline = "____________________________________________________________";

    /**
     * Displays greeting to the user upon start
     */

    public void greeting() {
        System.out.println(borderline);
        System.out.println("Hey! I'm Hari!");
        System.out.println("How may I be of service today?");
        System.out.println(borderline);
    }

    public void displayLoadError() {
        System.out.println("Error loading data from file! File may be corrupted or has format issues.");
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greeting();
    }

}
