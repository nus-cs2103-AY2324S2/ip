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
import commands.HelpCommand;
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
     */
    public Command parse(String userInput) {
        assert !userInput.trim().isEmpty() : "input should not be empty";
        try {
            String[] splitInput = userInput.split("\\s+");
            String firstWord = splitInput[0];
            if (firstWord.equalsIgnoreCase("bye")) {
                return handleBye();
            } else if (firstWord.equalsIgnoreCase("list")) {
                return handleList();
            } else if (firstWord.equalsIgnoreCase("mark")) {
                return handleMark(splitInput);
            } else if (firstWord.equalsIgnoreCase("unmark")) {
                return handleUnmark(splitInput);
            } else if (firstWord.equalsIgnoreCase("todo")) {
                return handleTodo(splitInput);
            } else if (firstWord.equalsIgnoreCase("deadline")) {
                return handleDeadline(splitInput);
            } else if (firstWord.equalsIgnoreCase("event")) {
                return handleEvent(splitInput);
            } else if (firstWord.equalsIgnoreCase("delete")) {
                return handleDelete(splitInput);
            } else if (firstWord.equalsIgnoreCase("find")) {
                return handleFind(splitInput);
            } else if (firstWord.equalsIgnoreCase("help")) {
                return handleHelp(splitInput);
            } else {
                throw new InvalidCommandException("I don't know what that means :( Valid commands are: \n"
                        + "list, todo, deadline, event, mark, unmark, bye\n");
            }
        } catch (InvalidCommandException | IncorrectDateError | InvalidInputException e) {
            return new InvalidCommand(e.getMessage());
        }
    }


    /**
     * Handles the parsing of "Bye" from user input
     * @return A ByeCommand
     */
    public ByeCommand handleBye() {
        return new ByeCommand();
    }

    /**
     * Handles the parsing of "List" from user input
     * @return A ListCommand
     */
    public ListCommand handleList() {
        return new ListCommand();
    }

    /**
     * Handles the parsing of "Mark x" from user input
     * @Param takes in the user input separated by " " to parse the number to mark
     * @return A MarkTaskCommand if number is valid else InvalidCommand
     */
    public Command handleMark(String[] splitInput) {
        try {
            int number = Integer.parseInt(splitInput[1]);
            return new MarkTaskCommand(number);
        } catch (NumberFormatException e) {
            return new InvalidCommand(e.getMessage() + "\n");
        }
    }

    /**
     * Handles the parsing of "Unmark x" from user input
     * @Param takes in the user input separated by " " to parse the number to unmark
     * @return A UnmarkTaskCommand if number is valid else InvalidCommand
     */
    public Command handleUnmark(String[] splitInput) {
        try {
            int number = Integer.parseInt(splitInput[1]);
            return new UnmarkTaskCommand(number);
        } catch (NumberFormatException e) {
            return new InvalidCommand(e.getMessage() + "\n");
        }
    }

    /**
     * Handles the parsing of "Todo x" from user input
     * @Param takes in the user input separated by " " to parse the information to create a Todo task
     * @return A AddTodoCommand
     */
    public AddTodoCommand handleTodo(String[] splitInput) throws InvalidInputException {
        String description = String.join(" ", java.util.Arrays.copyOfRange(
                splitInput, 1, splitInput.length));
        if (description.isEmpty()) {
            throw new InvalidInputException("Invalid input! Proper usage is \n"
                    + "todo {task description}\n");
        }
        ToDo task = new ToDo(description);
        return new AddTodoCommand(task);
    }

    /**
     * Handles the parsing of "Deadline x" from user input
     * @Param takes in the user input separated by " " to parse the information to create a Deadline task
     * @return A AddDeadlineCommand
     */
    public AddDeadlineCommand handleDeadline(String[] splitInput) throws InvalidInputException, IncorrectDateError {
        String deadlineText = String.join(" ", java.util.Arrays.copyOfRange(
                splitInput, 1, splitInput.length));
        Deadline task = getDeadlines(deadlineText);
        return new AddDeadlineCommand(task);
    }

    /**
     * Handles the parsing of "Event x" from user input
     * @Param takes in the user input separated by " " to parse the information to create an Event task
     * @return A AddEventCommand
     */
    public AddEventCommand handleEvent(String[] splitInput) throws InvalidInputException, IncorrectDateError {
        String eventText = String.join(" ", java.util.Arrays.copyOfRange(
                splitInput, 1, splitInput.length));
        Event task = getEvents(eventText);
        return new AddEventCommand(task);
    }

    /**
     * Handles the parsing of "delete x" from user input
     * @Param takes in the user input separated by " " to parse the number to delete
     * @return A DeleteCommand if the number is valid else InvalidCommand
     */
    public Command handleDelete(String[] splitInput) {
        try {
            int number = Integer.parseInt(splitInput[1]);
            return new DeleteCommand(number);
        } catch (NumberFormatException e) {
            return new InvalidCommand(e.getMessage() + "\n");
        }
    }

    /**
     * Handles the parsing of "Find x" from user input
     * @Param takes in the user input separated by " " to parse the search term
     * @return A FindCommand
     */
    public FindCommand handleFind(String[] splitInput) {
        String wordToSearch = String.join(" ", java.util.Arrays.copyOfRange(
                splitInput, 1, splitInput.length));
        return new FindCommand(wordToSearch);
    }

    /**
     * Handles the parsing of "Help x" from user input
     * @Param takes in the user input separated by " " to parse the term to get help from
     * @return A HelpCommand if user input is one of the valid search terms else Invalid Command
     */
    public Command handleHelp(String[] splitInput) {
        if (splitInput.length == 1) {
            return new HelpCommand();
        }
        String searchTerm = splitInput[1];
        if (searchTerm.equalsIgnoreCase("bye")) {
            return new HelpCommand(new ByeCommand());
        } else if (searchTerm.equalsIgnoreCase("list")) {
            return new HelpCommand(new ListCommand());
        } else if (searchTerm.equalsIgnoreCase("mark")) {
            return new HelpCommand(new MarkTaskCommand());
        } else if (searchTerm.equalsIgnoreCase("unmark")) {
            return new HelpCommand(new UnmarkTaskCommand());
        } else if (searchTerm.equalsIgnoreCase("todo")) {
            return new HelpCommand(new AddTodoCommand());
        } else if (searchTerm.equalsIgnoreCase("deadline")) {
            return new HelpCommand(new AddDeadlineCommand());
        } else if (searchTerm.equalsIgnoreCase("event")) {
            return new HelpCommand(new AddEventCommand());
        } else if (searchTerm.equalsIgnoreCase("delete")) {
            return new HelpCommand(new MarkTaskCommand());
        } else if (searchTerm.equalsIgnoreCase("find")) {
            return new HelpCommand(new FindCommand());
        } else {
            return new InvalidCommand("Enter help {term to search for}");
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
     * @param date String of date to check validity for
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
