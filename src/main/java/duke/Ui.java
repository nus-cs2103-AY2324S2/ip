package duke;

/**
 * Deals with interactions from the user
 */
public class Ui {
    private static String border = "____________________________________________________________";

    /**
     * Prints a greeting for the user upon bot startup
     */
    public String greet() {
        return "What's up! I'm steve\nWhat do you want to do?";
    }
    public void showLoadingError() {
        System.out.println("Failed to read tasks file!");
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greet();
    }

}
