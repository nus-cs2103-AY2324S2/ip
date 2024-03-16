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
    private String[] prevCommand;

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

    public Parser() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
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
        } else if (isPermutationMatch(inputParts[0], "mark")) {
            reply = checkTypo("inputParts[0]", "mark");
        } else if (userInput.startsWith("unmark")) {
            // mark item as undone
            reply = ui.unMarkTask(inputParts);
        } else if (isPermutationMatch(inputParts[0], "unmark")) {
            reply = checkTypo("inputParts[0]", "unmark");
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
        } else if (isPermutationMatch(userInput, "list")) {
            // check if user made a typo of 'list'
            reply = checkTypo("inputParts[0]", "list");
        } else if (userInput.startsWith("delete")) {
            // remove a task
            reply = listDelete(inputParts);
        } else if (userInput.startsWith("find")) {
            // find a keyword
            reply = ui.commandFind(inputParts);
        } else if (isPermutationMatch(inputParts[0], "find")) {
            reply = checkTypo("inputParts[0]", "find");
        } else if ((userInput.equals("yes") || userInput.equals("no")) && checkPrev()) {
            reply = handleIsTypo(userInput);
        } else if (userInput.equals("help")) {
            // show help message
            reply = ui.commandHelp();
        } else {
            // create a new task or other commands
            reply = taskCommands(userInput);
        }
        this.prevCommand = inputParts;
        ui.signalSays(reply);
        assert reply.length() != 0 : "Reply can't be empty!";
        return reply;
    }

    /**
     * Check if the input is a permutation of the original.
     *
     * @param input Input collected from the user.
     * @param original String to compare the input to.
     * @return True if input is a permutation of original.
     */
    public boolean isPermutationMatch(String input, String original) {
        // Check if user input is a permutation match
        char[] userInputArray = input.toCharArray();
        char[] originalArray = original.toCharArray();

        // Sort the arrays to compare
        java.util.Arrays.sort(userInputArray);
        java.util.Arrays.sort(originalArray);

        return java.util.Arrays.equals(userInputArray, originalArray);
    }


    /**
     * Checks if the previous command is a typo of commands mark, unmark, list or find.
     *
     * @return True if it is a typo of commands mark, unmark, list or find.
     */
    public boolean checkPrev() {
        if (isPermutationMatch(prevCommand[0], "mark")
                || isPermutationMatch(prevCommand[0], "unmark")
                || isPermutationMatch(prevCommand[0], "list")
                || isPermutationMatch(prevCommand[0], "find")) {
            return true;
        }
        return false;
    }

    /**
     * Confirms with the user if they intended to type commands mark, unmark, list or find.
     *
     * @param input The user's input.
     * @param command The command which the input is a permutation.
     * @return A string that contains the command which the input is a permutation.
     */
    public String checkTypo(String input, String command) {
        String reply = "";
        if(!input.equals(command)) {
            reply = "Did you mean '"+ command + "'? (yes/no)";
        }
        return reply;
    }

    /**
     * Handles the cases if the user has made a typo or not.
     *
     * @param userInput The input from the user, either yes or no.
     * @return The output of the user's intended command.
     */
    public String handleIsTypo(String userInput) {
        String reply = "";
        if (userInput.equals("yes")) {
            reply = handleWhichTypo(this.prevCommand);
        }
        if (userInput.equals("no")) {
            reply = "What else can I help you with?";
        }
        return reply;
    }

    /**
     * Handles the different cases of command typos, mark, unmark, list or find.
     *
     * @param prevCommand The command that the user previously input.
     * @return The output of the user's intended command.
     */
    public String handleWhichTypo(String[] prevCommand) {
        String reply = "";
        String command = prevCommand[0];
        try {
            if (isPermutationMatch(prevCommand[0], "mark")) {
                reply = ui.markTask(prevCommand);
            } else if (isPermutationMatch(prevCommand[0], "unmark")) {
                reply = ui.unMarkTask(prevCommand);
            } else if (isPermutationMatch(prevCommand[0], "list")) {
                reply = ui.commandList();
            } else if (isPermutationMatch(prevCommand[0], "find")) {
                reply = ui.commandFind(prevCommand);
            } else {
                reply = "Oops, I'm not sure what command you meant. Please repeat?";
            }
        } catch (SignalException e) {
            reply = e.getMessage();
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
