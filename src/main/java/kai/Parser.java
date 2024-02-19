package kai;

import java.util.ArrayList;

/**
 * Deals with parsing and interpreting user commands for task management.
 * Parses the user input and executes corresponding actions on the task list.
 */


public class Parser {

    private String userInput;
    private ArrayList<Task> myList;
    private Ui ui;


    /**
     * Constructs a Parser with the specified user input and task list.
     *
     * @param userInput The user input to be parsed.
     * @param myList    The list of tasks to be managed.
     */

    public Parser(String userInput, ArrayList<Task> myList) {
        this.userInput = userInput;
        this.myList = myList;
        this.ui = new Ui();
    }


    /**
     * Parses and executes the user command based on the provided input.
     *
     * @return The corresponding string based on the command.
     */
    public String parseCommand() {
        // Assumption: User input should not be empty
        assert userInput != null : "User input must not be empty";

        // Split input into "command" and "parameters"
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];
        String restOfInputs = parts.length > 1 ? parts[1] : "";
        TaskList taskList = new TaskList(myList);

        if (command.equals("list")) {
            return taskList.list();

        } else if (command.equals("bye")) {

        } else if (command.equals("find")) {
            return taskList.find(parts);

        } else if (command.equals("help-me")) {
            return taskList.help();

        } else if (parts.length == 1) {
            return ui.displayErrorMessage();

        } else if (command.equals("unmark")) {
            return taskList.unmarkList(parts);

        } else if (command.equals("mark")) {
            return taskList.markList(parts);

        } else if (command.equals("delete")) {
            return taskList.remove(parts);

        } else {
            return taskList.add(command, restOfInputs);
        }
        return ui.blank();
    }
}
