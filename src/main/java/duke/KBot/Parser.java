package duke.kbot;

import duke.actions.AddTask;
import duke.actions.Command;
import duke.actions.DeleteTask;
import duke.actions.FindTask;
import duke.actions.Help;
import duke.actions.ListTask;
import duke.actions.MarkTask;
import duke.actions.UnMarkTask;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidInputException;

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
        if (inputs.length > 1) { // for ins with parameter
            switch (instruction) { // correct ins but wrong params handled by each method call
                case "mark":
                    int indexToMark = Integer.parseInt(inputs[1]);
                    return new MarkTask(indexToMark - 1);
                case "unmark":
                    int indexToUnmark = Integer.parseInt(inputs[1]);
                    return new UnMarkTask(indexToUnmark - 1);
                case "delete":
                    int indexToDelete = Integer.parseInt(inputs[1]);
                    return new DeleteTask(indexToDelete - 1);
                case "todo":
                case "deadline":
                case "event":
                    String parameter = inputs[1];
                    return new AddTask(instruction, parameter);
                case "find":
                    String key = inputs[1];
                    return new FindTask(key);
                default: // incorrect ins with incorrect params handled here
                    throw new InvalidCommandException("Invalid command: " + instruction
                            + "\nPlease input the correct commands. Input help to see list of commands.");
            }
        } else { // for ins with no parameter
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
                case "event": // correct ins but no param handled
                    throw new InvalidInputException("Error: " + "Invalid parameters for " + instruction);
                default: // incorrect ins and no param handled here
                    throw new InvalidCommandException("Invalid command: " + instruction
                            + "\nPlease input the correct commands. Input help to see list of commands.");
            }
        }
    }
}
