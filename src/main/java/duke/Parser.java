package duke;

import duke.command.*;
import duke.exception.CommandInvalidException;

public class Parser {
    public static boolean valid(String n){ //check if the given command is valid //throw something
        String[] inputs = n.split(" ");
        for (Commands com : Commands.values()) {
            if (com.name().equalsIgnoreCase(inputs[0])) {
                return true;
            }
        }
        return false;
    }

    public static Command parse(String command) throws CommandInvalidException {
        if (valid(command)) {
            String[] inputs = command.split(" ");
            if (command.equals("bye")) { //if the user use bye command
                return new ByeCommand();
            } else if (inputs[0].equals("mark") || inputs[0].equals("unmark")) {
                //if the user use mark or unmark command
                if (inputs[0].equals("mark")) {
                    return new MarkCommand(command);
                } else if (inputs[0].equals("unmark")) {
                    return new UnmarkCommand(command);
                }
            } else if (command.equals("list")) { //if the user use list command
                return new ListCommand();
            } else if (inputs[0].equals("delete")) { //if the user use delete command
                return new DeleteCommand(command);
            } else {
                return new AddCommand(command);
            }
        }
        throw new CommandInvalidException("Invalid command -_-, please use the available commands!!");
    }

}
