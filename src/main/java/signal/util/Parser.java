package signal.util;

//import signal.DukeException;
import signal.task.Task;

import java.util.ArrayList;

/**
 * Parses user input and executes commands based on the input.
 * This class handles the interpretation of user commands, creating tasks,
 * and managing user interactions through the Ui class.
 */
public class Parser {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructs a Parser with references to the current task list and the UI for user interaction.
     *
     * @param taskList The current list of tasks.
     * @param ui    The UI instance for displaying messages to the user.
     */
    public Parser(ArrayList<Task> taskList, Ui ui) {
        this.tasks = taskList;
        this.ui = ui;
    }

    /**
     * Reads the input from the user and implement the corresponding method.
     *
     * @param userInput The string of input.
     */
    public void read(String userInput) {
        String[] inputParts = userInput.split(" ");
        if (userInput.equals("")) {
            // input is blank
            ui.emptyInput();
        } else if (userInput.startsWith("mark")) {
            // mark item as done
            ui.markTask(inputParts);
        } else if (ui.isPermutationMatch(inputParts[0], "mark")) {
            markTypo(inputParts);
        } else if (userInput.startsWith("unmark")) {
            // mark item as undone
            ui.unMarkTask(inputParts);
        } else if (ui.isPermutationMatch(inputParts[0], "unmark")) {
            unmarkTypo(inputParts);
        } else if (userInput.equals("list")) {
            // show list of tasks
            ui.commandList();
        } else if (ui.isPermutationMatch(userInput, "list")) {
            // check if user made a typo of 'list'
            listTypo(userInput);
        } else if (userInput.startsWith("delete")) {
            // remove a task
            listDelete(inputParts);
        } else if (userInput.startsWith("find")) {
            // find a keyword
            ui.commandFind(inputParts);
        } else if (ui.isPermutationMatch(inputParts[0], "find")) {

        } else if (userInput.equals("help")) {
            // show help message
            ui.commandHelp();
        } else {
            taskCommands(userInput);
        }
    }

    /**
     * Handles the case where the user input may be a typo of 'mark'.
     *
     * @param inputParts The string array of input.
     */
    public void markTypo(String[] inputParts) {
        if (ui.checkCommandTypo(inputParts[0], "mark")) {
            ui.markTask(inputParts);
        } else {
            ui.signalSays("What else can I help you with?");
        }
    }

    /**
     * Handles the case where the user input may be a typo of 'unmark'.
     *
     * @param inputParts The string array of input.
     */
    public void unmarkTypo(String[] inputParts) {
        if (ui.checkCommandTypo(inputParts[0], "unmark")) {
            ui.unMarkTask(inputParts);
        } else {
            ui.signalSays("What else can I help you with?");
        }
    }

    /**
     * Handles the case where the user input may be a typo of 'mark'.
     *
     * @param userInput The string of input.
     */
    public void listTypo(String userInput) {
        if (ui.checkCommandTypo(userInput, "list")) {
            ui.commandList();
        } else {
            ui.signalSays("What else can I help you with?");

//            ui.signalSays("Do you want to add " + userInput + "? (y/n)");
//            String addCommandCheck = ui.scan();
//            if (addCommandCheck.equals("n")) {
//                ui.signalSays("What else can I help you with?");
//            } else if(addCommandCheck.equals("y")) {
//                taskCommands(userInput);
//            }
        }
    }

    public void findTypo(String[] inputParts) {
        if (ui.checkCommandTypo(inputParts[0], "find")) {
            ui.commandFind(inputParts);
        } else {
            ui.signalSays("What else can I help you with?");
        }
    }

    /**
     * Handles the command for deleting a task.
     *
     * @param inputParts The string array of input.
     */
    public void listDelete(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        ui.commandDelete(index);
    }

    /**
     * Creates a task of the corresponding type as the input.
     *
     * @param userInput The string of input.
     */
    public void taskCommands(String userInput) {
        // create tasks
        String[] inputParts = userInput.split(" ");
        if (userInput.startsWith("todo")) {
            ui.commandToDo(inputParts);
        } else if (userInput.startsWith("deadline")) {
            ui.commandDeadline(inputParts);
        } else if (userInput.startsWith("event")) {
            ui.commandEvent(inputParts);
        } else {
            otherInputs(userInput);
        }

    }

    /**
     * Handles cases where the input is not in the list of commands.
     *
     * @param userInput The string of input.
     */
    public void otherInputs(String userInput) {
        if (userInput.equals("blah")) {
            ui.commandBlah();
//            try {
//                ui.commandBlah();
//            } catch (DukeException e) {
//                ui.signalSays(e.getMessage());
//            }
        } else if (userInput.equals("something else")) {
            ui.commandSomethingelse();
//            try {
//                ui.commandSomethingelse();
//            } catch (DukeException e) {
//                ui.signalSays(e.getMessage());
//            }
        } else {
            ui.signalSays("Sorry, I don't know what you're talking about. " +
                    "Enter 'help' to see what commands you can use!");
        }
    }
}
