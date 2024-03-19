package kbot.managers;

import kbot.actions.AddTask;
import kbot.actions.Command;
import kbot.actions.DeleteTask;
import kbot.actions.FindTask;
import kbot.actions.Help;
import kbot.actions.ListTask;
import kbot.actions.MarkTask;
import kbot.actions.UnMarkTask;
import kbot.exceptions.InvalidCommandException;
import kbot.exceptions.InvalidInputException;

/**
 * Parse through the commands to return a specific command class.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Parser {

    /**
     * Execute whatever command thrown at the bot by the user
     * 
     * @param userInput String representing command to the bot.
     * @throws InvalidCommandException Exceptions thrown when the command is not
     *                                 valid.
     * @throws InvalidInputException   Exception thrown when command parameters are
     *                                 not valid.
     * @return Returns a Command to be executed.
     */
    public static Command parse(String userInput) throws InvalidCommandException, InvalidInputException {
        assert userInput != null && userInput.length() > 0 : "Cannot parse empty String!";
        String[] inputs = userInput.trim().split(" ", 2);
        String instruction = inputs[0];

        if (inputs.length > 1) { // if the command requires parameters
            // instruction is valid, we pass the parameters into each Command class which handles incorrect parameters
            String params = inputs[1];
            switch (instruction) {
            case "mark":
                return parseMarkTask(params);
            case "unmark":
                return parseUnMarkTask(params);
            case "delete":
                return parseDeleteTask(params);
            case "todo":
            case "deadline":
            case "event":
                return parseAddTask(instruction, params);
            case "find":
                return parseFindTask(params);
            default: // instruction is not valid
                throw new InvalidCommandException("Invalid command: " + instruction
                        + "\nPlease input the correct commands. Input \"help\" to see list of commands.");
            }
        } else { // if instruction does not have any parameters
            switch (instruction) {
            case "list":
                return new ListTask();
            case "help":
                return new Help();
            case "mark":
            case "unmark":
            case "delete":
            case "todo":
            case "deadline":
            case "event": // instruction is valid but no parameters given
                throw new InvalidInputException(
                        "Invalid parameters for " + instruction + "\nType \"help\" if you're unsure.");
            default: // instruction is not valid
                throw new InvalidCommandException("Invalid command: " + instruction
                        + "\nPlease input the correct commands. Input \"help\" to see list of commands.");
            }
        }
    }

    /**
     * Parses parameters for Mark Task Object.
     * @param index The index which the MarkTask will mark within the tasks.
     * @return a new MarkTask.
     */
    public static MarkTask parseMarkTask(String index) {
        int indexToMark = Integer.parseInt(index);
        return new MarkTask(indexToMark - 1);
    }

    /**
     * Parses parameters for UnMark Task Object.
     * @param index The index which the UnMarkTask will unmark within the tasks.
     * @return a new UnMarkTask.
     */
    public static UnMarkTask parseUnMarkTask(String index) {
        int indexToUnmark = Integer.parseInt(index);
        return new UnMarkTask(indexToUnmark - 1);
    }

    /**
     * Parses parameters for Delete Task Object.
     * @param index The index which the DeleteTask will Delete within the tasks.
     * @return a new DeleteTask.
     */
    public static DeleteTask parseDeleteTask(String index) {
        int indexToDelete = Integer.parseInt(index);
        return new DeleteTask(indexToDelete - 1);
    }

    /**
     * Parses parameters for Add Task Object.
     * @param params The parameters for instantiating new Task object by AddTask.
     * @return a new AddTask.
     */
    public static AddTask parseAddTask(String instruction, String params) {
        return new AddTask(instruction, params);
    }

    /**
     * Parses parameters for Find Task Object.
     * @param key The keu which the FindTask will find a match within the tasks.
     * @return a new FindTask.
     */
    public static FindTask parseFindTask(String key) {
        return new FindTask(key);
    }

}
