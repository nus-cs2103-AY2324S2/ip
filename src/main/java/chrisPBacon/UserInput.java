package chrisPBacon;

import exceptions.ChrisPBaconException;
import exceptions.InvalidTaskNameException;
import task.TaskList;

import java.util.Scanner;

/**
 * This class stores the user input and scans for the next input.
 * If input is "bye", the scanner will be closed.
 */
public class UserInput {
    private final Scanner scan;
    private String userInput;

    /**
     * Constructor for UserInput.
     */
    public UserInput() {
        this.scan = new Scanner(System.in);
        this.userInput = scan.nextLine();
    }

    /**
     * Processes the userInput and scans for the next input.
     *
     * @param ui Ui management.
     * @param tasks The task list.
     */
    public void processInput(Ui ui, TaskList tasks) {
        try {
            String firstWord = this.userInput.indexOf(' ') < 0
                    ? this.userInput
                    : this.userInput.substring(0, userInput.indexOf(' '));

            switch (firstWord) {
            case "help":
                ui.printHelp();
                break;
            case "list":
                ui.printList(tasks);
                break;
            case "mark":
                ui.printMark(this.userInput, tasks);
                break;
            case "unmark":
                ui.printUnmark(this.userInput, tasks);
                break;
            case "delete":
                ui.printDelete(this.userInput, tasks);
                break;
            case "todo":
                ui.printTodo(this.userInput, tasks);
                break;
            case "deadline":
                ui.printDeadline(this.userInput, tasks);
                break;
            case "event":
                ui.printEvent(this.userInput, tasks);
                break;
            default:
                // if user entered input that cannot be recognised.
                throw new ChrisPBaconException("Ooink oink! I'm sorry, I don't understand.\n"
                        + "Type 'help' for command info!\n");
            }
        } catch (ChrisPBaconException | InvalidTaskNameException e) {
            ui.printError(e.getMessage());
        }
        userInput = scan.nextLine(); // Scan for next input.
    }

    /**
     * Checks if user input is "bye".
     *
     * @return true if input is bye and close the scanner.
     */
    public boolean isInputBye() {
        boolean isBye = userInput.equals("bye");
        if (isBye) {
            // Close scanner if input == bye.
            this.scan.close();
        }
        return isBye;
    }
}
