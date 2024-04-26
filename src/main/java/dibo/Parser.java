package dibo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import dibo.command.AddDeadlineCommand;
import dibo.command.AddDoAfterCommand;
import dibo.command.AddEventCommand;
import dibo.command.AddToDoCommand;
import dibo.command.ByeCommand;
import dibo.command.Command;
import dibo.command.DeleteCommand;
import dibo.command.FindCommand;
import dibo.command.ListCommand;
import dibo.command.MarkCommand;
import dibo.command.UnmarkCommand;
import dibo.exception.DiboException;

/**
 * The Parser class represents the parser to make sense of the user input.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Returns a Command object which can be executed to provide desired result and output.
     *
     * @param fullCommand The String representation of the user input/command.
     * @throws DiboException If an error occurs when parsing.
     */
    public static Command parse(String fullCommand) throws DiboException {
        String[] commandDetails = fullCommand.split(" ");
        switch (commandDetails[0]) {
        case "list":
            return parseListCommand();
        case "unmark":
            return parseUnmarkCommand(commandDetails);
        case "mark":
            return parseMarkCommand(commandDetails);
        case "delete":
            return parseDeleteCommand(commandDetails);
        case "find":
            return parseFindCommand(commandDetails);
        case "bye":
            return parseByeCommand();
        case "todo":
            return parseAddToDoCommand(fullCommand);
        case "deadline":
            return parseAddDeadlineCommand(fullCommand);
        case "do-after":
            return parseAddDoAfterCommand(fullCommand);
        case "event":
            return parseAddEventCommand(fullCommand);
        default:
            throw new DiboException("Oh no sir! There is no such task type :(");
        }
    }

    private static ListCommand parseListCommand() {
        return new ListCommand();
    }


    private static UnmarkCommand parseUnmarkCommand(String[] commandDetails) throws DiboException {
        try {
            int taskId = Integer.parseInt(commandDetails[1]);
            return new UnmarkCommand(taskId);
        } catch (NumberFormatException e) {
            throw new DiboException("Oh no sir! You have to unmark the items based on their index.\n"
                    + "If you are not sure of the index, enter 'list' to check it out :D");
        }
    }

    private static MarkCommand parseMarkCommand(String[] commandDetails) throws DiboException {
        try {
            int taskId = Integer.parseInt(commandDetails[1]);
            return new MarkCommand(taskId);
        } catch (NumberFormatException e) {
            throw new DiboException("Oh no sir! You have to mark the items based on their index.\n"
                    + "If you are not sure of the index, enter 'list' to check it out :D");
        }
    }

    private static DeleteCommand parseDeleteCommand(String[] commandDetails) throws DiboException {
        try {
            int taskId = Integer.parseInt(commandDetails[1]);
            return new DeleteCommand(taskId);
        } catch (NumberFormatException e) {
            throw new DiboException("Oh no sir! You have to delete the items based on their index."
                    + "If you are not sure of the index, enter 'list' to check it out :D");
        }
    }

    private static FindCommand parseFindCommand(String[] commandDetails) throws DiboException {
        if (commandDetails.length < 1) {
            throw new DiboException("Oh no sir! We need at least a keyword to search.");
        }
        ArrayList<String> keywordsArrayList = new ArrayList<>();
        for (int i = 1; i < commandDetails.length; ++i) {
            String keyword = commandDetails[i];
            keywordsArrayList.add(keyword);
        }

        String[] keywordsArray = keywordsArrayList.toArray(new String[0]);
        return new FindCommand(keywordsArray);
    }

    private static ByeCommand parseByeCommand() {
        return new ByeCommand();
    }

    private static AddToDoCommand parseAddToDoCommand(String fullCommand) throws DiboException {
        if (!hasDescription(fullCommand)) {
            throw new DiboException("Oh no sir! We need a description for your task. "
                    + "This will enable us to better keep track of your tasks.");
        }
        String descriptionToDo = getDescription(fullCommand, "todo");
        return new AddToDoCommand(descriptionToDo);
    }

    private static AddDeadlineCommand parseAddDeadlineCommand(String fullCommand) throws DiboException {
        if (!hasDescription(fullCommand)) {
            throw new DiboException("Oh no sir! We need a description for your task. "
                    + "This will enable us to better keep track of your tasks.");
        }

        try {
            String descriptionDeadline = getDescription(fullCommand, "deadline");
            LocalDate byDate = getByDate(fullCommand);
            return new AddDeadlineCommand(descriptionDeadline, byDate);
        } catch (IndexOutOfBoundsException e) {
            throw new DiboException("Oh no sir! Please state the deadline of the task :D");
        } catch (DateTimeParseException e) {
            throw new DiboException("Oh no sir! Please state the deadline of the task "
                    + "in this format: yyyy-mm-dd");
        }
    }

    private static AddDoAfterCommand parseAddDoAfterCommand(String fullCommand) throws DiboException {
        if (!hasDescription(fullCommand)) {
            throw new DiboException("Oh no sir! We need a description for your task. "
                    + "This will enable us to better keep track of your tasks.");
        }

        try {
            String descriptionDoAfter = getDescription(fullCommand, "do-after");
            LocalDate afterDate = getAfterDate(fullCommand);
            return new AddDoAfterCommand(descriptionDoAfter, afterDate);
        } catch (IndexOutOfBoundsException e) {
            throw new DiboException("Oh no sir! Please state the date this task needs to be done after :D");
        } catch (DateTimeParseException e) {
            throw new DiboException("Oh no sir! Please state the date this task needs to be done after "
                    + "in this format: yyyy-mm-dd");
        }
    }

    private static AddEventCommand parseAddEventCommand(String fullCommand) throws DiboException {
        if (!hasDescription(fullCommand)) {
            throw new DiboException("Oh no sir! We need a description for your task. "
                    + "This will enable us to better keep track of your tasks.");
        }
        try {
            String descriptionEvent = getDescription(fullCommand, "event");
            LocalDate startDate = getStartDate(fullCommand);
            LocalDate endDate = getEndDate(fullCommand);
            if (startDate.isAfter(endDate)) {
                throw new DiboException("Oh no sir! Your start date must be before your end date :(");
            }
            return new AddEventCommand(descriptionEvent, startDate, endDate);
        } catch (IndexOutOfBoundsException e) {
            throw new DiboException("Oh no sir! Please state the start and end of the task :D");
        } catch (DateTimeParseException e) {
            throw new DiboException("Oh no sir! Please state the deadline of the task "
                    + "in this format: yyyy-mm-dd");
        }
    }

    private static boolean hasDescription(String fullCommand) {
        String[] details = fullCommand.split("/");
        String[] typeAndDescription = details[0].split(" ");
        return typeAndDescription.length >= 2;
    }

    private static String getDescription(String fullCommand, String type) {
        String[] details = fullCommand.split("/");
        return details[0].substring(type.length()).trim();
    }

    private static LocalDate getByDate(String fullCommand)
            throws DateTimeParseException, IndexOutOfBoundsException {
        String[] details = fullCommand.split("/");
        int lengthOfBy = 2;
        String byString = details[1].substring(lengthOfBy).trim();
        return LocalDate.parse(byString, Parser.INPUT_FORMAT);
    }

    private static LocalDate getAfterDate(String fullCommand)
            throws DateTimeParseException, IndexOutOfBoundsException {
        String[] details = fullCommand.split("/");
        int lengthOfAfter = 5;
        String afterString = details[1].substring(lengthOfAfter).trim();
        return LocalDate.parse(afterString, Parser.INPUT_FORMAT);
    }
    private static LocalDate getStartDate(String fullCommand)
            throws DateTimeParseException, IndexOutOfBoundsException {
        String[] details = fullCommand.split("/");
        int lengthOfFrom = 4;
        String startString = details[1].substring(lengthOfFrom).trim();
        return LocalDate.parse(startString, Parser.INPUT_FORMAT);
    }

    private static LocalDate getEndDate(String fullCommand)
            throws DateTimeParseException, IndexOutOfBoundsException {
        String[] details = fullCommand.split("/");
        int lengthOfTo = 2;
        String endString = details[2].substring(lengthOfTo).trim();
        return LocalDate.parse(endString, Parser.INPUT_FORMAT);
    }
}
