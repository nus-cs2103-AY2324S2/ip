package duke;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * The main class representing the chatbot.
 * It is a chatbot that allows users to manage tasks,
 * including adding, marking as done, saving and loading tasks to
 * and from a file.
 * It handles user input, parsing, executing and storage operations.
 */
public class Duke {
    private Storage storage;
    private MyList myList;
    private Ui ui;

    /**
     * Constructs a Duke instance with the specified file path for storage.
     *
     * @param filePath The file path for storage.
     */
    public Duke(String filePath) {
        ui = new Ui(System.in);
        storage = new Storage(filePath);
        try {
            myList = new MyList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not Found");
            myList = new MyList();
        } catch (DukeException e) {
            System.out.println("Error: " + e.getMsg());
            myList = new MyList();
        } catch (IOException e) {
            System.err.println("Error reading task from file: " + e.getMessage());
            myList = new MyList();
        }
    }


    /**
     * Runs the Duke application, displaying a welcome message, processing user commands,
     * and saving the task list to a file before exiting.
     */
    public void run() {
        ui.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
        storage.save(myList);
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        Parser parser = new Parser();
        Parser.Request request;
        do {
            String userInput = ui.getUserRequest();
            request = parser.getRequest(userInput);
            String result = parser.parseCommand(myList, userInput);
            ui.showResultToUser(result);
        } while (request != Parser.Request.BYE);
    }

    public static void main(String[] args) {
        new Duke("src/main/java/data/duke.txt").run();
    }
}
