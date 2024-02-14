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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userInput) {
        Parser parser = new Parser();
        String response;
        if (userInput.equalsIgnoreCase("bye")) {
            try {
                storage.save(myList);
            } catch (IOException e) {
                System.err.println("Error writing task to file: " + e.getMessage());
            }
        }

        try {
            response = parser.parseCommand(myList, userInput);
        } catch (DukeException e) {
            response = e.getMsg();
        } catch (ArrayIndexOutOfBoundsException e) {
            response = "Please enter a valid task format.";
        } catch (NumberFormatException e) {
            response = "Please enter a number.";
        }
        return response;
    }

    /**
     * Runs the Duke application, displaying a welcome message, processing user commands,
     * and saving the task list to a file before exiting.
     */
    public void run() {
        ui.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
        try {
            storage.save(myList);
        } catch (IOException e) {
            System.err.println("Error writing task to file: " + e.getMessage());
        }
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        Parser parser = new Parser();
        Parser.Request request;
        do {
            String userInput = ui.getUserRequest();
            request = parser.getRequest(userInput);
            String result;
            try {
                result = parser.parseCommand(myList, userInput);
            } catch (DukeException e) {
                result = e.getMsg();
            } catch (ArrayIndexOutOfBoundsException e) {
                result = "Incorrect format";
            } catch (NumberFormatException e) {
                result = "Please enter a number.";
            }
            ui.showResultToUser(result);
        } while (request != Parser.Request.BYE);
    }

    public static void main(String[] args) {
        new Duke("src/main/java/data/duke.txt").run();
    }
}
