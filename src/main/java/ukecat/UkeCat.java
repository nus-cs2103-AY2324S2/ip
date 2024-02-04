package ukecat;

/**
 *
 * The main class representing the UkeCat application.
 * UkeCat is a simple program that interacts with users to manage tasks.
 * This class initializes the user interface, loads tasks from storage,
 * and handles user input through the Parser class.
 */
public class UkeCat {
    private final Ui ui;

    public UkeCat() {
        ui = new Ui();
    }

    /**
     * Runs the UkeCat application.
     * Displays a welcome message, loads tasks, and continuously
     * processes user input until the "bye" command is entered.
     */
    public void run() {
        ui.welcome();
        FileManager.loadTasks();
        try {
            // Read user input
            while (true) {
                Parser.parse();
                if (Storage.words[0].equals("bye")) {
                    ui.bye();
                    Parser.closeScanner();
                    System.exit(0);
                }
                ui.respond(Storage.words);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * The main method to start the UkeCat application.
     * Creates an instance of UkeCat and calls the run method.
     *
     * @param args The command-line arguments (not used for UkeCat)
     */
    public static void main(String[] args) {
        new UkeCat().run();
    }
}
