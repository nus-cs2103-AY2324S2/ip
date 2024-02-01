package dibo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dibo.command.AddDeadlineCommand;
import dibo.command.AddEventCommand;
import dibo.command.AddToDoCommand;
import dibo.command.ByeCommand;
import dibo.command.Command;
import dibo.command.DeleteCommand;
import dibo.command.ListCommand;
import dibo.command.MarkCommand;
import dibo.command.UnmarkCommand;
import dibo.exception.DiboException;

/**
 * The class to make sense of the commands given by the user.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Takes in the command and tries to make sense of it.
     * Returns a Command object which can be run at the end.
     *
     * @return A Command object to be run.
     * @throws DiboException If an error occurs when parsing.
     */
    public static Command parse(String fullCommand) throws DiboException {
        String[] commandDetails = fullCommand.split(" ");
        switch (commandDetails[0]) {
        case "list":
            return new ListCommand();
        case "unmark":
            try {
                int taskId = Integer.parseInt(commandDetails[1]);
                return new UnmarkCommand(taskId);
            } catch (NumberFormatException e) {
                throw new DiboException("Oh no sir! You have to unmark the items based on their index.\n"
                        + "If you are not sure of the index, enter 'list' to check it out :D");
            }
        case "mark":
            try {
                int taskId = Integer.parseInt(commandDetails[1]);
                return new MarkCommand(taskId);
            } catch (NumberFormatException e) {
                throw new DiboException("Oh no sir! You have to Mark the items based on their index.\n"
                        + "If you are not sure of the index, enter 'list' to check it out :D");
            }
        case "delete":
            try {
                int taskId = Integer.parseInt(commandDetails[1]);
                return new DeleteCommand(taskId);
            } catch (NumberFormatException e) {
                throw new DiboException("Oh no sir! You have to delete the items based on their index."
                        + "If you are not sure of the index, enter 'list' to check it out :D");
            }
        case "bye":
            return new ByeCommand();
        case "todo":
            if (commandDetails.length < 2) {
                throw new DiboException("Oh no sir! We need a description for your task. "
                        + "This will enable us to better keep track of your tasks.");
            }
            String descriptionToDo = getDescription(fullCommand, "todo");
            return new AddToDoCommand(descriptionToDo);
        case "deadline":
            if (commandDetails.length < 2) {
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
        case "event":
            if (commandDetails.length < 2) {
                throw new DiboException("Oh no sir! We need a description for your task. "
                        + "This will enable us to better keep track of your tasks.");
            }
            try {
                String descriptionEvent = getDescription(fullCommand, "event");
                LocalDate startDate = getStartDate(fullCommand);
                LocalDate endDate = getEndDate(fullCommand);
                return new AddEventCommand(descriptionEvent, startDate, endDate);
            } catch (IndexOutOfBoundsException e) {
                throw new DiboException("Oh no sir! Please state the start and end of the task :D");
            } catch (DateTimeParseException e) {
                throw new DiboException("Oh no sir! Please state the deadline of the task "
                        + "in this format: yyyy-mm-dd");
            }
        default:
            throw new DiboException("Oh no sir! There is no such task type :(");
        }
    }

    private static String getDescription(String fullCommand, String type) {
        String[] details = fullCommand.split("/");
        return details[0].substring(type.length()).trim();
    }

    private static LocalDate getByDate(String fullCommand)
            throws DateTimeParseException, IndexOutOfBoundsException {
        String[] details = fullCommand.split("/");
        String byString = details[1].substring(2).trim();
        return LocalDate.parse(byString, Parser.INPUT_FORMAT);
    }
    private static LocalDate getStartDate(String fullCommand)
            throws DateTimeParseException, IndexOutOfBoundsException {
        String[] details = fullCommand.split("/");
        String startString = details[1].substring(4).trim();
        return LocalDate.parse(startString, Parser.INPUT_FORMAT);
    }

    private static LocalDate getEndDate(String fullCommand)
            throws DateTimeParseException, IndexOutOfBoundsException {
        String[] details = fullCommand.split("/");
        String endString = details[2].substring(2).trim();
        return LocalDate.parse(endString, Parser.INPUT_FORMAT);
    }
}
