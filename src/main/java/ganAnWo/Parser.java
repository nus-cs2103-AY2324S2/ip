package ganAnWo;

import ganAnWo.command.AddCommand;
import ganAnWo.command.ByeCommand;
import ganAnWo.command.Command;
import ganAnWo.command.Commands;
import ganAnWo.command.DeleteCommand;
import ganAnWo.command.FindCommand;
import ganAnWo.command.ListCommand;
import ganAnWo.command.MarkCommand;
import ganAnWo.command.UnmarkCommand;
import ganAnWo.exception.CommandInvalidException;

/**
 * Class to convert text to command.
 *
 */
public class Parser {

    /**
     * Returns validity whether the command given.
     * If the command is valid, return true.
     * If the command is invalid, return false.
     *
     * @param no given command.
     * @return validity of the command.
     *
     */
    public static boolean isValid(String no) { //check if the given command is valid //throw something
        String[] inputs = no.split(" ");
        for (Commands com : Commands.values()) {
            if (com.name().equalsIgnoreCase(inputs[0])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an instatiate of Command for the given command.
     *
     * @param command given command.
     * @return an instatiate of Command.
     * @throws CommandInvalidException If command is invalid.
     */
    public static Command parse(String command) throws CommandInvalidException {
        if (!isValid(command)) {
            throw new CommandInvalidException("Invalid command -_-, please use the available commands!!!");
        }
        String[] inputs = command.split(" ");
        if (command.equals("bye")) { //if the user use bye command
            return new ByeCommand();
        } else if (inputs[0].equals("mark")) {
            return new MarkCommand(command);
        } else if (inputs[0].equals("unmark")) {
            return new UnmarkCommand(command);
        } else if (command.equals("list")) { //if the user use list command
            return new ListCommand();
        } else if (inputs[0].equals("delete")) { //if the user use delete command
            return new DeleteCommand(command);
        } else if (inputs[0].equals("find")) {
            return new FindCommand(command);
        } else {
            return new AddCommand(command);
        }
    }

}
