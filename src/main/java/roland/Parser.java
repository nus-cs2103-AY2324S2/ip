package roland;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.AddNotesCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import task.Deadlines;
import task.Events;
import task.ToDos;


/**
 * The Parser class is responsible for parsing user input and converting it into executable commands
 * in the task management application. It contains a static method, `parse`, which takes a full command string
 * and returns the corresponding Command object based on the recognized command keywords.
 *
 * @author wolffe88
 */

public class Parser {

    /**
     * Parses the given full command string and returns the corresponding Command object.
     *
     * @param fullCommand The user input representing a command.
     * @return A Command object corresponding to the parsed command.
     * @throws RolandException If the input command is not recognized or lacks necessary details.
     */
    public static Command parse(String fullCommand) throws RolandException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("find")) {
            String keyword = fullCommand.substring(5, fullCommand.length());
            return new FindCommand(keyword);
        } else if (fullCommand.startsWith("mark")) {
            int index = Integer.parseInt(fullCommand.replaceAll("[\\D]", ""));
            return new MarkCommand(index, true);
        } else if (fullCommand.startsWith("unmark")) {
            int index = Integer.parseInt(fullCommand.replaceAll("[\\D]", ""));
            return new MarkCommand(index, false);
        } else if (fullCommand.startsWith("note")) {
            int index = Integer.parseInt(fullCommand.replaceAll("[\\D]", ""));
            String[] split = fullCommand.split(" /");
            String notes = split[1];
            return new AddNotesCommand(index, notes);
        } else if (fullCommand.startsWith("delete")) {

            int index = Integer.parseInt(fullCommand.replaceAll("[\\D]", ""));
            return new DeleteCommand(index);
        } else if (fullCommand.startsWith("todo")) {
            if (fullCommand.length() <= 5) {
                throw new RolandException("Please provide description for todo");
            }
            assert fullCommand.length() > 5 : "Please provide description for todo";
            String description = fullCommand.substring(5, fullCommand.length());
            return new AddCommand(new ToDos(description));

        } else if (fullCommand.startsWith("deadline")) {
            try {
                if (fullCommand.length() <= 9) {
                    throw new RolandException("Please provide description for deadline");
                }
                if (fullCommand.split("/").length < 2) {
                    throw new RolandException("Please include when is the deadline by with /by <YYYY-mm-dd>");
                }
                assert fullCommand.length() > 9 : "Please provide description for deadline";
                assert fullCommand.split("/").length >= 2
                        : "Please include when is the deadline by with /by <YYYY-mm-dd>";
                String[] split = fullCommand.split(" /");
                String description = split[0].substring(9, split[0].length());
                String by = split[1].substring(3, split[1].length());
                LocalDate date = LocalDate.parse(by);
                return new AddCommand(new Deadlines(description, date));
            } catch (DateTimeParseException e) {
                throw new RolandException("Please include when is the deadline with /by <YYYY-mm-dd>");
            }
        } else if (fullCommand.startsWith("event")) {
            if (fullCommand.length() <= 6) {
                throw new RolandException("Please provide description for event");
            }
            if (fullCommand.split("/").length != 3) {
                throw new RolandException(
                    "Please include when is the start and end of the event with /from <start> /to <end>");
            }
            assert fullCommand.length() > 6 : "Please provide description for event";
            assert fullCommand.split("/").length == 3
                    : "Please include when is the start and end of the event with /from <start> /to <end>";
            String[] split = fullCommand.split(" /");
            String description = split[0].substring(6, split[0].length());
            String from = split[1].substring(5, split[1].length());
            String to = split[2].substring(4, split[2].length());
            return new AddCommand(new Events(description, from, to));

        } else {
            throw new RolandException("I do not understand you :(.");
        }
    }


}
