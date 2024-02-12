package duke;

/**
 * Deals with interactions from the user
 */
public class Ui {
    private static String border = "____________________________________________________________";

    /**
     * Prints a greeting for the user upon bot startup
     */
    public void greet() {
        System.out.println(border);
        System.out.println("Hey! I'm Hari!");
        System.out.println("How may I be of service today?");
        System.out.println(border);
    }
    public void showLoadingError() {
        System.out.println("Error loading data from file. File may be corrupted.");
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greet();
    }

}
