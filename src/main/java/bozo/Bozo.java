package bozo;

import java.util.Scanner;

import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * The Bozo class is the main class for the Bozo chatbot.
 * It contains the main method and the run method.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Bozo {
    private static TaskList list;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bozo = new Image(this.getClass().getResourceAsStream("/images/DaBozo.png"));

    /**
     * Constructor for the Bozo class.
     * @param filePath The file path to save the list to.
     */
    public Bozo(String filePath) {
        storage = new Storage(filePath);
        list = new TaskList(storage.loadFile());
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Empty Constructor for the Bozo class.
     */
    public Bozo() {
        storage = new Storage("./data/bozo.txt");
        list = new TaskList(storage.loadFile());
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Runs the Bozo chatbot.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            input = sc.nextLine();
            try {
                parser.parseCommand(input, list);
            } catch (BozoException e) {
                ui.showError(e.getMessage());
            }
        } while (!input.equals("bye"));

        ui.showGoodbye();
        storage.saveList(list);
    }

    public static void main(String[] args) {
        new Bozo("./data/bozo.txt").run();
    }

    /**
     * Gets a response from the chatbot based on the user input.
     */
    public String getResponse(String input) {
        try {
            String output = parser.parseCommand(input, list);
            if (output.equals("Bye. Hope to see you again soon!")) {
                storage.saveList(list);
                Platform.exit();
            }
            return output;
        } catch (BozoException e) {
            // If there's an exception, return the error message
            return e.getMessage();
        }
    }
}
