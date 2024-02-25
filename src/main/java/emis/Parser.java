package emis;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.MarkCommand;
import command.PrintCommand;
import command.ToDoCommand;
import command.UnmarkCommand;
import exceptions.EmisException;
import exceptions.DeadlineException;
import exceptions.EventException;

/**
 * The Parser class deals with making sense of user commands in the EMIS application.
 * It parses the user input and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user command and returns the corresponding Command object.
     * 
     * @param fullCommand The full user command to be parsed.
     * @return The Command object corresponding to the parsed user command.
     * @throws EmisException If the user command is invalid or cannot be parsed.
     */
    public static Command parse(String fullCommand) throws EmisException {
        Integer spaceIndex = fullCommand.indexOf(" ");

        if (spaceIndex == -1) {
            if (fullCommand.equals("bye")) {
                return new ExitCommand();
            } else if (fullCommand.equals("list")) {
                return new PrintCommand();
            } else if (fullCommand.equals("help")) {
                return new HelpCommand();
            }else {
                throw new EmisException("Meow? Emis doesn't understand.\n Please send 'help' if needed.");
            }
        } else {
            String action = fullCommand.substring(0, spaceIndex);
            String prospectiveCommand = fullCommand.substring(spaceIndex + 1);
            if (action.equals("todo")) {
                return new ToDoCommand(prospectiveCommand);

            } else if (action.equals("deadline")) {
                return parseDeadline(prospectiveCommand);

            } else if (action.equals("event")) {
                return parseEvent(prospectiveCommand);

            } else if (action.equals("mark")) {
                Integer taskNo = Integer.valueOf(fullCommand.substring(spaceIndex + 1).trim());
                return new MarkCommand(taskNo);

            } else if (action.equals("unmark")) {
                Integer taskNo = Integer.valueOf(fullCommand.substring(spaceIndex + 1).trim());
                return new UnmarkCommand(taskNo);

            } else if (action.equals("delete")) {
                Integer taskNo = Integer.valueOf(fullCommand.substring(spaceIndex + 1).trim());
                return new DeleteCommand(taskNo);

            } else if (action.equals("find")) {
                String taskToFind = fullCommand.substring(spaceIndex + 1);
                return new FindCommand(taskToFind);

            } else {
                throw new EmisException("Invalid command!");
            }
        }
    }

    /**
     * Parses a deadline command and returns the corresponding Command object.
     *
     * @param input The input string containing the deadline command.
     * @return The DeadlineCommand object corresponding to the parsed command.
     * @throws DeadlineException If the deadline command cannot be parsed.
     */
    public static Command parseDeadline(String input) throws DeadlineException {
        int slashIndex = input.indexOf("/by");
        if (slashIndex == -1) {
            throw new DeadlineException("Please enter a deadline using '/by' after the task.");
        } else {
            String description = input.substring(0, slashIndex);
            String by = input.substring(slashIndex + 3);
            return new DeadlineCommand(description, by);
        }
    }

    /**
     * Parses an event command and returns the corresponding Command object.
     *
     * @param input The input string containing the event command.
     * @return The EventCommand object corresponding to the parsed command.
     * @throws EventException If the event command cannot be parsed.
     */
    public static Command parseEvent(String input) throws EventException {
        int slashIndexFrom = input.indexOf("/from");
        int slashIndexTo = input.indexOf("/to");
        if (slashIndexFrom == -1 || slashIndexTo == -1) {
            throw new EventException("Please enter event in this format: event /from (from date) /to (to date or time) regardless of if you have a from or to.");
        } else {
            String description = input.substring(0, slashIndexFrom);
            String from = input.substring(slashIndexFrom + 5, slashIndexTo);
            String to = input.substring(slashIndexTo + 3);
            return new EventCommand(description, from, to);
        }
    }
}
