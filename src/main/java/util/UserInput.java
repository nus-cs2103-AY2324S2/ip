package util;

import exceptions.ChrisPBaconException;
import exceptions.InvalidTaskNameException;
import task.TaskList;

/**
 * This class stores the user input and scans for the next input.
 * If input is "bye", the scanner will be closed.
 */
public class UserInput {
    private final String userInput;

    /**
     * Constructor for UserInput.
     */
    public UserInput(String input) {
        this.userInput = input;
    }

    /**
     * Processes the userInput and returns ChrisP's response.
     *
     * @param ui Ui management.
     * @param tasks The task list.
     * @return ChrisP's response to userInput.
     */
    public String processInput(Ui ui, TaskList tasks) {
        try {
            String firstWord = userInput.indexOf(' ') < 0
                    ? userInput
                    : userInput.substring(0, userInput.indexOf(' '));

            switch (firstWord) {
            case "help":
                return ui.printHelp();
            case "list":
                return ui.printList(tasks);
            case "mark":
                return ui.printMark(userInput, tasks);
            case "unmark":
                return ui.printUnmark(userInput, tasks);
            case "delete":
                return ui.printDelete(userInput, tasks);
            case "find":
                return ui.printFind(userInput, tasks);
            case "todo":
                return ui.printTodo(userInput, tasks);
            case "deadline":
                return ui.printDeadline(userInput, tasks);
            case "event":
                return ui.printEvent(userInput, tasks);
            default:
                // if user entered input that cannot be recognised.
                throw new ChrisPBaconException("Ooink oink! I'm sorry, I don't understand.\n"
                        + "Type 'help' for command info!\n");
            }
        } catch (ChrisPBaconException | InvalidTaskNameException e) {
            return e.getMessage();
        }
    }
}
