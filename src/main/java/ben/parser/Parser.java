package ben.parser;

import ben.commands.*;
import ben.exceptions.BenException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input to create corresponding Command objects for the Ben task management application.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param fullCommand The full user input.
     * @return A Command object based on the parsed input.
     * @throws BenException If an error occurs during parsing or if the command is not recognized.
     */
    public static Command parse(String fullCommand) throws BenException {
        String[] tokens = fullCommand.split(" ", 2);
        String command = tokens[0];

        switch (command) {
            case "bye":
                // Exit command
                return new ExitCommand();

            case "list":
                // List command
                return new ListCommand();

            case "mark": {
                // Mark command

                // empty field
                if (tokens.length < 2) {
                    throw new BenException("   Key in a value!");
                }

                // obtain index of task within Ben.tasks.TaskList
                int index = Integer.parseInt(tokens[1]) - 1;

                return new MarkCommand(index);
            }

            case "unmark": {
                // Unmark command

                // empty field
                if (tokens.length < 2) {
                    throw new BenException("   Key in a value!");
                }

                // obtain index of task within Ben.tasks.TaskList
                int index = Integer.parseInt(tokens[1]) - 1;

                return new UnmarkCommand(index);
            }

            case "todo": {
                // Todo command

                // empty to-do
                if (tokens.length < 2) {
                    throw new BenException("   OOPS!!! The description of a todo cannot be empty.");
                }

                String description = tokens[1];

                return new TodoCommand(description);
            }

            case "deadline": {
                // Deadline command

                // empty deadline
                if (tokens.length < 2) {
                    throw new BenException("   OOPS!!! The description of a deadline cannot be empty.");
                }

                // delimiting string
                String information = tokens[1];
                String[] descTokens = information.split(" /by ");
                String description = descTokens[0];
                String by = descTokens[1];

                try {
                    LocalDate deadline = LocalDate.parse(by);
                    return new DeadlineCommand(description, deadline);
                } catch (DateTimeParseException e) {
                    throw new BenException("Invalid deadline format");
                }
            }

            case "event": {
                // Event command

                // empty event
                if (tokens.length < 2) {
                    throw new BenException("   OOPS!!! The description of an event cannot be empty.");
                }

                // delimiting string
                String information = tokens[1];
                String[] descTokens = information.split(" /from ");
                String description = descTokens[0];
                String dates = descTokens[1];
                String[] dateTokens = dates.split(" /to ");
                String startDate = dateTokens[0];
                String endDate = dateTokens[1];

                try {
                    LocalDate dateFormattedStartDate = LocalDate.parse(startDate);
                    LocalDate dateFormattedEndDate = LocalDate.parse(endDate);
                    return new EventCommand(description, dateFormattedStartDate, dateFormattedEndDate);
                } catch (DateTimeParseException e) {
                    throw new BenException("Invalid deadline format");
                }
            }

            case "delete": {
                // Delete command

                // empty field
                if (tokens.length < 2) {
                    throw new BenException("   Key in a value");
                }

                int index = Integer.parseInt(tokens[1]) - 1;

                return new DeleteCommand(index);
            }

            case "find": {
                // Find command

                // empty field
                if (tokens.length < 2) {
                    throw new BenException("   Key in a value");
                }

                String keyword = tokens[1];

                return new FindCommand(keyword);
            }

            default:
                // Unrecognised command
                throw new BenException("   OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
