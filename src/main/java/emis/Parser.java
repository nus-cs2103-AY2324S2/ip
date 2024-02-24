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
                throw new EmisException("Meow? Emis doesn't understand.");
            }
        } else {
            String action = fullCommand.substring(0, spaceIndex);
            if (action.equals("todo")) {
                String description = fullCommand.substring(spaceIndex + 1);
                return new ToDoCommand(description);

            } else if (action.equals("deadline")) {
                String deadline = fullCommand.substring(spaceIndex + 1);
                int slashIndex = deadline.indexOf("/by");
                String description = deadline.substring(0, slashIndex);
                String by = deadline.substring(slashIndex + 3);
                return new DeadlineCommand(description, by);

            } else if (action.equals("event")) {
                String event = fullCommand.substring(spaceIndex + 1);
                int slashIndexFrom = event.indexOf("/from");
                int slashIndexTo = event.indexOf("/to");
                String description = event.substring(0, slashIndexFrom);
                String from = event.substring(slashIndexFrom + 5, slashIndexTo);
                String to = event.substring(slashIndexTo + 3);
                return new EventCommand(description, from, to);

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
}