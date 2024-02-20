package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;

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
            ui.showError("Error: File not Found");
            myList = new MyList();
        } catch (DukeException e) {
            ui.showError(e.getMsg());
            myList = new MyList();
        } catch (IOException e) {
            ui.showError("Error reading task from file");
            myList = new MyList();
        }
    }

    /**
     * Returns a string representation of a successful message
     * of the operation determined by the user input.
     *
     * @param userInput A string representation of the operation to be done
     */
    public String getResponse(String userInput) {
        Parser parser = new Parser();
        Command command;
        String response;
        if (userInput.equalsIgnoreCase("bye")) {
            storage.save(myList);
        }
        try {
            ui.clear();
            command = parser.parseCommand(userInput);
            command.execute(myList, ui);
            response = ui.getResponse();
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
        storage.save(myList);
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        Parser parser = new Parser();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.clear();
                String userInput = ui.getUserRequest();
                Command command = parser.parseCommand(userInput);
                command.execute(myList, ui);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMsg());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError("Incorrect format");
            } catch (NumberFormatException e) {
                ui.showError("Please enter a number.");
            } finally {
                ui.showResultToUser();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/java/data/duke.txt").run();
    }
}
