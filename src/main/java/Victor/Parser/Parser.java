package victor.parser;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import victor.command.Command;
import victor.storage.Storage;
import victor.tasklist.TaskList;
import victor.ui.Ui;


/**
 * The Parser class is the portion of the code that takes in the user command,
 * interprets what the user wants to do from the command and does it.
 *
 * @author Dominic Fu Ming Jun
 */
public class Parser {

    /**
     * Storage class that is used to load and store data into data file
     */
    private static final Storage storage =
            new Storage("data/victor.txt");

    /**
     * The currentTasks variable is used to hold the current data from the Victor.txt data file.
     */
    private final TaskList currentTasks;

    /**
     * The Parser constructor takes in the ui and currentTasks
     *
     * @param ui           The ui class that is needed to perform ui methods.
     * @param currentTasks The currentTasks that is used to get the current task list.
     */
    public Parser(Ui ui, TaskList currentTasks) {
        /**
         * Ui class that is used to display certain information for this class.
         */
        this.currentTasks = currentTasks;
    }

    /**
     * The parse method is used to decode the commandLine String and perform
     * the actions that is indicated in the commandLine.
     *
     *
     * @param commandLine The commandLine that is to be decoded to determine what task to perform.
     * @return A string of the information of the command
     * @throws IndexOutOfBoundsException Normally used if the command line tries to access an item
     *                                   position outside the range of the ArrayList.
     * @throws NumberFormatException     Used for when the commandLine action requires a number,
     *                                   but has something else instead
     * @throws DateTimeParseException    Used for deadline, when the input for the by variable is unable
     *                                   to be converted from String to a LocalDate,
     *                                   indicating that it is in the wrong format.
     *
     *
     */
    public String parse(String commandLine) throws IOException {
        String[] inputList = commandLine.split(" ", 2);
        String returnString = "";
        Command currentCommand;
        switch (inputList[0]) {
        case "list" -> {
            currentCommand = Command.LIST;
            returnString = currentCommand.execute(currentTasks, null);
        }
        case "mark" -> {
            currentCommand = Command.MARK;
            returnString = currentCommand.execute(currentTasks, inputList[1]);
        }
        case "unmark" -> {
            currentCommand = Command.UNMARK;
            returnString = currentCommand.execute(currentTasks, inputList[1]);
        }
        case "todo" -> {
            currentCommand = Command.TODO;
            returnString = currentCommand.execute(currentTasks, inputList[1]);
        }
        case "deadline" -> {
            currentCommand = Command.DEADLINE;
            returnString = currentCommand.execute(currentTasks, inputList[1]);
        }
        case "event" -> {
            currentCommand = Command.EVENT;
            returnString = currentCommand.execute(currentTasks, inputList[1]);
        }
        case "delete" -> {
            currentCommand = Command.DELETE;
            returnString = currentCommand.execute(currentTasks, inputList[1]);
        }
        case "find" -> {
            currentCommand = Command.FIND;
            returnString = currentCommand.execute(currentTasks, inputList[1]);
        }
        case "bye" -> {
            currentCommand = Command.BYE;
            returnString = currentCommand.execute(currentTasks, null);
        }
        default -> {
            returnString = "Command not recognized. Please try again.";
        }
        }
        return returnString;
    }
}
