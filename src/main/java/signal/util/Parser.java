package signal.util;

import signal.SignalException;
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

    public String parse(String userInput) {
        try {
            return read(userInput);
        } catch (SignalException e) {
            return e.getMessage();
        }
    }

    /**
     * Reads the input from the user and implement the corresponding method.
     *
     * @param userInput The string of input.
     */
    public String read(String userInput) throws SignalException {
        String[] inputParts = userInput.split(" ");
        String reply = "";
        if (userInput.equals("bye")) {
            // show goodbye message
            reply = ui.leave();
        } else if (userInput.equals("")) {
            // input is blank
            reply = ui.emptyInput();
        } else if (userInput.startsWith("mark")) {
            // mark item as done
            reply = ui.markTask(inputParts);
        } else if (ui.isPermutationMatch(inputParts[0], "mark")) {
            reply = markTypo(inputParts);
        } else if (userInput.startsWith("unmark")) {
            // mark item as undone
            reply = ui.unMarkTask(inputParts);
        } else if (ui.isPermutationMatch(inputParts[0], "unmark")) {
            reply = unmarkTypo(inputParts);
        } else if (userInput.equals("notdonelist")) {
            reply = ui.commandNotDoneList();
        } else if (userInput.startsWith("prioritise")) {
            reply = ui.markPriority(inputParts);
        } else if (userInput.startsWith("unprioritise")) {
            reply = ui.markNotPriority(inputParts);
        } else if (userInput.equals("prioritylist")) {
            reply = ui.commandPriorityList();
        } else if (userInput.equals("list")) {
            // show list of tasks
            reply = ui.commandList();
        } else if (ui.isPermutationMatch(userInput, "list")) {
            // check if user made a typo of 'list'
            listTypo(userInput);
        } else if (userInput.startsWith("delete")) {
            // remove a task
            reply = listDelete(inputParts);
        } else if (userInput.startsWith("find")) {
            // find a keyword
            reply = ui.commandFind(inputParts);
        } else if (ui.isPermutationMatch(inputParts[0], "find")) {
            reply = findTypo(inputParts);
        } else if (userInput.equals("help")) {
            // show help message
            reply = ui.commandHelp();
        } else {
            // create a new task or other commands
            reply = taskCommands(userInput);
        }
        ui.signalSays(reply);
        assert reply.length() != 0 : "Reply can't be empty!";
        return reply;
    }




    /**
     * Handles the case where the user input may be a typo of 'mark'.
     *
     * @param inputParts The string array of input.
     */
    public String markTypo(String[] inputParts) {
        String reply = "";
        if (ui.checkCommandTypo(inputParts[0], "mark")) {
            reply = ui.markTask(inputParts);
        } else {
            reply = "What else can I help you with?";
        }
        return reply;
    }

    /**
     * Handles the case where the user input may be a typo of 'unmark'.
     *
     * @param inputParts The string array of input.
     */
    public String unmarkTypo(String[] inputParts) {
        String reply = "";
        if (ui.checkCommandTypo(inputParts[0], "unmark")) {
            reply = ui.unMarkTask(inputParts);
        } else {
            reply = "What else can I help you with?";
        }
        return reply;
    }

    /**
     * Handles the case where the user input may be a typo of 'mark'.
     *
     * @param userInput The string of input.
     */
    public String listTypo(String userInput) {
        String reply = "";
        try {
            if (ui.checkCommandTypo(userInput, "list")) {
                reply = ui.commandList();
            } else {
                reply = "What else can I help you with?";
            }
        } catch (SignalException e) {
            reply = e.getMessage();
        }

        return reply;
    }

    public String findTypo(String[] inputParts) throws SignalException {
        String reply = "";
        if (ui.checkCommandTypo(inputParts[0], "find")) {
            try {
                reply = ui.commandFind(inputParts);
            } catch (SignalException e) {
                reply = e.getMessage();
            }
        } else {
            reply ="What else can I help you with?";
        }
        return reply;
    }

    /**
     * Handles the command for deleting a task.
     *
     * @param inputParts The string array of input.
     */
    public String listDelete(String[] inputParts) {
        int index = Integer.parseInt(inputParts[1]);
        String reply = "";
        try {
            reply = ui.commandDelete(index - 1);
        } catch (SignalException e) {
            reply = e.getMessage();
        }
        return reply;
    }

    /**
     * Creates a task of the corresponding type as the input.
     *
     * @param userInput The string of input.
     */
    public String taskCommands(String userInput) {
        // create tasks
        String[] inputParts = userInput.split(" ");
        String reply = "";
        try {
            if (userInput.startsWith("todo")) {
                // Create a ToDo task
                reply = ui.commandToDo(inputParts);
            } else if (userInput.startsWith("deadline")) {
                // Create a Deadline task
                reply = ui.commandDeadline(inputParts);
            } else if (userInput.startsWith("event")) {
                // Create an Event task
                reply = ui.commandEvent(inputParts);
            } else {
                reply = otherInputs(userInput);
            }
        } catch (SignalException e) {
            reply = e.getMessage();
        }
        return reply;

    }

    /**
     * Handles cases where the input is not in the list of commands.
     *
     * @param userInput The string of input.
     */
    public String otherInputs(String userInput) {
        String reply = "";
        if (userInput.equals("blah")) {
            reply = ui.commandBlah();
        } else if (userInput.equals("something else")) {
            reply = ui.commandSomethingelse();
        } else if (userInput.equals("hi")) {
            reply = ui.commandHi();
        } else {
            reply = ui.unknownInput();
        }
        return reply;

    }
}
