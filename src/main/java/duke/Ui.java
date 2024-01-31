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
        String logo =
                "     _                 \n"
                        + " ___| |_ _____   _____ \n"
                        + "/ __| __/ _ \\ \\ / / _ \\\n"
                        + "\\__ \\ ||  __/\\ V /  __/\n"
                        + "|___/\\__\\___| \\_/ \\___|\n";
        System.out.println(border);
        System.out.println("What's up! I'm steve");
        System.out.println("What do you want to do?");
        System.out.println(border);
    }
    public void showLoadingError() {
        System.out.println("Failed to read tasks file!");
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greet();
    }

}
