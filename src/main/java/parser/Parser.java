package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkTaskCommand;
import commands.UnmarkTaskCommand;
import exceptions.IncorrectDateError;
import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * <p>
 *  Handles the understanding of user input
 * </p>
 */
public class Parser {

    /**
     * Parses the user input into a specific command to be executed.
     * <p>
     * This method takes a string of user input, interprets the intended command,
     * and returns the corresponding Command object. Supported commands include
     * 'bye', 'list', 'mark', 'unmark', 'todo', 'deadline', 'event', and 'delete'.
     * Commands not matching these are considered invalid.
     * </p>
     *
     * @param userInput The user input string to be parsed.
     * @return The specific Command object representing the intended action.
     * @throws InvalidCommandException If the command is not recognised.
     * @throws IncorrectDateError If the date for deadline or event commands is in an incorrect format.
     * @throws InvalidInputException If the input for the commands is invalid or incomplete.
     */
    public Command parse(String userInput) {
        assert !userInput.trim().isEmpty() : "input should not be empty";
        try {
            String[] splitInput = userInput.split("\\s+");
            String firstWord = splitInput[0];
            if (firstWord.equalsIgnoreCase("bye")) {
                return new ByeCommand();
            } else if (firstWord.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else if (firstWord.equalsIgnoreCase("mark")) {
                try {
                    int number = Integer.parseInt(splitInput[1]);
                    return new MarkTaskCommand(number);
                } catch (NumberFormatException e) {
                    return new InvalidCommand(e.getMessage() + "\n");
                }
            } else if (firstWord.equalsIgnoreCase("unmark")) {
                try {
                    int number = Integer.parseInt(splitInput[1]);
                    return new UnmarkTaskCommand(number);
                } catch (NumberFormatException e) {
                    return new InvalidCommand(e.getMessage() + "\n");
                }
            } else if (firstWord.equalsIgnoreCase("todo")) {
                String description = String.join(" ", java.util.Arrays.copyOfRange(
                        splitInput, 1, splitInput.length));
                if (description.isEmpty()) {
                    throw new InvalidInputException("Invalid input! Proper usage is \n"
                            + "todo {task description}\n");
                }
                ToDo task = new ToDo(description);
                return new AddTodoCommand(task);
            } else if (firstWord.equalsIgnoreCase("deadline")) {
                String deadlineText = String.join(" ", java.util.Arrays.copyOfRange(
                        splitInput, 1, splitInput.length));
                Deadline task = getDeadlines(deadlineText);
                return new AddDeadlineCommand(task);
            } else if (firstWord.equalsIgnoreCase("event")) {
                String eventText = String.join(" ", java.util.Arrays.copyOfRange(
                        splitInput, 1, splitInput.length));

                Event task = getEvents(eventText);
                return new AddEventCommand(task);
            } else if (firstWord.equalsIgnoreCase("delete")) {
                try {
                    int number = Integer.parseInt(splitInput[1]);
                    return new DeleteCommand(number);
                } catch (NumberFormatException e) {
                    return new InvalidCommand(e.getMessage() + "\n");
                }
            } else if (firstWord.equalsIgnoreCase("find")) {
                String wordToSearch = String.join(" ", java.util.Arrays.copyOfRange(
                        splitInput, 1, splitInput.length));
                return new FindCommand(wordToSearch);
            } else {
                throw new InvalidCommandException("I don't know what that means :( Valid commands are: \n"
                        + "list, todo, deadline, event, mark, unmark, bye\n");
            }
        } catch (InvalidCommandException | IncorrectDateError | InvalidInputException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * helper function that splits userInput into the parameters needed to create a Deadline task
     */
    private static Deadline getDeadlines(String deadlineText) throws InvalidInputException, IncorrectDateError {
        String deadlineTextLowerCase = deadlineText.toLowerCase();

        int byIndex = deadlineTextLowerCase.indexOf(" /by ");
        int byAltIndex = deadlineTextLowerCase.indexOf(" by ");

        int indexToUse = (byIndex != -1) ? byIndex : byAltIndex;
        int lengthToSkip = (byIndex != -1) ? 5 : 4;
        String description = "";
        String date = "";
        if (indexToUse == -1) {
            throw new InvalidInputException("Invalid input! Proper usage is \n"
                    + "deadline {task description} by {date}\n");
        }
        description = deadlineText.substring(0, indexToUse).trim();
        date = deadlineText.substring(indexToUse + lengthToSkip).trim();
        String dateTime = checkValidDate(date);
        if (dateTime == null) {
            throw new IncorrectDateError("Valid dates are of forms d/M/yyyy HHmm, yyyy-MM-dd HH:mm "
                    + "or dd MMM yyyy h:mm a\n");
        }
        return new Deadline(description, dateTime);
    }

    /**
     * helper function that splits userInput into the parameters needed to create an Event task
     */
    private static Event getEvents(String eventText) throws InvalidInputException, IncorrectDateError {
        String eventTextLowerCase = eventText.toLowerCase();

        int fromIndex = eventTextLowerCase.indexOf(" /from ");
        int fromAltIndex = eventTextLowerCase.indexOf(" from ");
        int fromIndexToUse = (fromIndex != -1) ? fromIndex : fromAltIndex;
        int fromLengthToSkip = (fromIndex != -1) ? 7 : 6;

        int toIndex = eventTextLowerCase.indexOf(" /to ");
        int toAltIndex = eventTextLowerCase.indexOf(" to ");
        int toIndexToUse = (toIndex != -1) ? toIndex : toAltIndex;
        int toLengthToSkip = (toIndex != -1) ? 5 : 4;
        String description = "";
        String startDate = "";
        String endDate = "";
        if (fromIndexToUse == -1 || toIndexToUse == -1) {
            throw new InvalidInputException("Invalid input! Proper usage is \n"
                    + "event {task description} from {start date} to {end date}\n");
        }
        description = eventText.substring(0, fromIndexToUse).trim();
        startDate = eventText.substring(fromIndexToUse + fromLengthToSkip, toIndexToUse);
        endDate = eventText.substring(toIndexToUse + toLengthToSkip).trim();
        String startDateTime = checkValidDate(startDate);
        String endDateTime = checkValidDate(endDate);
        if (startDateTime == null || endDateTime == null) {
            throw new IncorrectDateError("Valid dates are of forms d/M/yyyy HHmm, yyyy-MM-dd HH:mm "
                    + "or dd MMM yyyy h:mm a");
        }
        return new Event(description, startDateTime, endDateTime);
    }

    /**
     * helper function that checks if the data entered by user is of the correct format
     * @param date String of date to check validiity for
     * @throws IncorrectDateError if Date is invalid
     */
    private static String checkValidDate(String date) throws IncorrectDateError {
        DateTimeFormatter[] formatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a")
        };
        for (DateTimeFormatter formatter: formatters) {
            try {
                return LocalDateTime.parse(date, formatter).format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
            } catch (DateTimeParseException e) {
                throw new IncorrectDateError("Valid dates are of forms d/M/yyyy HHmm, yyyy-MM-dd HH:mm "
                        + "or dd MMM yyyy h:mm a");
            }
        }
        return null;
    }
}
