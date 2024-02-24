package seiki.parser;

import java.util.ArrayList;
import java.util.Arrays;

import seiki.commands.ByeCommand;
import seiki.commands.Command;
import seiki.commands.DeadlineCommand;
import seiki.commands.DeleteCommand;
import seiki.commands.EventCommand;
import seiki.commands.FindCommand;
import seiki.commands.ListCommand;
import seiki.commands.MarkCommand;
import seiki.commands.ToDoCommand;
import seiki.commands.UnmarkCommand;
import seiki.data.exception.SeikiException;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     * @param input full user input string
     * @return the command based on the user input
     * @throws SeikiException
     */
    public static Command parse(String input) throws SeikiException {
        try {
            ArrayList<String> args = new ArrayList<>(Arrays.asList(input.split(" ")));
            assert !args.isEmpty() : "Argument should not be empty";
            String cmd = args.remove(0);

            switch (cmd) {
            case ListCommand.COMMAND_WORD:
                if (!args.isEmpty()) {
                    throw new SeikiException("Additional inputs have been detected.\n"
                            + "Please only type 'list' to view your list.");
                }
                return new ListCommand();
            case MarkCommand.COMMAND_WORD:
                if (args.isEmpty()) {
                    throw new SeikiException("Please enter a task number.");
                } else if (args.size() != 1) {
                    throw new SeikiException("Multiple inputs have been detected.\n"
                            + "Please follow the format: mark [task number]");
                }
                return new MarkCommand(args);
            case UnmarkCommand.COMMAND_WORD:
                if (args.isEmpty()) {
                    throw new SeikiException("Please enter a task number.");
                } else if (args.size() != 1) {
                    throw new SeikiException("Multiple inputs have been detected.\n"
                            + "Please follow the format: unmark [task number]");
                }
                return new UnmarkCommand(args);
            case ToDoCommand.COMMAND_WORD:
                if (args.isEmpty()) {
                    throw new SeikiException("The task title is missing.\n"
                            + "Please use the following format: todo [task title]");
                }
                return new ToDoCommand(args);
            case DeadlineCommand.COMMAND_WORD:
                if (args.isEmpty() || (args.subList(args.indexOf("/by") + 1, args.size()).isEmpty()
                        && args.subList(0, args.indexOf("/by")).isEmpty())) {
                    throw new SeikiException("The task title and date time for the task is missing.\n"
                            + "Please use the following format: deadline [task title] /by [datetime]");
                }
                return new DeadlineCommand(args);
            case EventCommand.COMMAND_WORD:
                if (args.isEmpty()) {
                    throw new SeikiException("The task title, start and end date time for the task is missing.\n"
                            + "Please use the following format:"
                            + "event [task title] /from [startdatetime] /to [enddatetime]");
                }
                return new EventCommand(args);
            case DeleteCommand.COMMAND_WORD:
                if (args.isEmpty()) {
                    throw new SeikiException("Please enter a task number.");
                } else if (args.size() != 1) {
                    throw new SeikiException("Multiple inputs have been detected.\n"
                            + "Please follow the format: delete [task number]");
                }
                return new DeleteCommand(args);
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();
            case FindCommand.COMMAND_WORD:
                if (args.isEmpty()) {
                    throw new SeikiException("Please enter a keyword.");
                } else if (args.size() != 1) {
                    throw new SeikiException("Please enter only 1 keyword.");
                }
                return new FindCommand(args);
            default:
                throw new SeikiException("I'm sorry, I didn't quite understand that.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new SeikiException("I'm sorry, I didn't quite understand that.");
        }

    }
}
