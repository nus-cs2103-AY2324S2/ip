import exceptions.HarperInvalidCommandException;
import exceptions.HarperInvalidIndexException;
import exceptions.HarperInvalidDateTimeException;
import exceptions.HarperInvalidDeadlineException;
import exceptions.HarperInvalidEventException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");

    /**
     * Parses the command.
     *
     * @param command Command entered by user.
     * @return Different type of Command based on user's input.
     */
    public static Command parse(String command) {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("todo ")) {
            return handleToDo(command);
        } else if (command.startsWith("deadline ")) {
            return handleDeadline(command);
        } else if (command.startsWith("event ")) {
            return handleEvent(command);
        } else if (command.startsWith("delete ")) {
            return handleDelete(command);
        } else if (command.startsWith("mark ") || command.startsWith("unmark ")) {
            return handleMark(command);
        } else {
            throw new HarperInvalidCommandException();
        }
    }

    /**
     * Parses the todo command.
     *
     * @param command Todo command entered by user.
     * @return AddCommand that adds the todo task into the list.
     */
    private static Command handleToDo(String command) {
        String taskDescription = command.substring("todo".length()).trim();
        Task newToDo = new ToDo(taskDescription, false);
        return new AddCommand(newToDo);
    }

    /**
     * Parses the deadline command
     *
     * @param command Deadline command entered by user.
     * @return AddCommand that adds the deadline task into the list.
     */
    private static Command handleDeadline(String command) {
        String taskDesciptionAndDeadline = command.substring("deadline".length()).trim();
        String[] parts = taskDesciptionAndDeadline.split("/by", 2);
        if (parts.length != 2) {
            throw new HarperInvalidDeadlineException();
        }
        String description = parts[0].trim();
        String deadline = parts[1].trim();
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new HarperInvalidDeadlineException();
        }
        try {
            LocalDateTime deadlineFormatted = LocalDateTime.parse(deadline, DATE_TIME_FORMATTER);
            Task newDeadline = new Deadline(description, false, deadlineFormatted);
            return new AddCommand(newDeadline);
        } catch (DateTimeParseException e) {
            throw new HarperInvalidDateTimeException();
        }
    }

    /**
     * Parses the event command.
     *
     * @param command Event command entered by user.
     * @return AddCommand that adds the event task into the list.
     */
    private static Command handleEvent(String command) {
        String taskDescriptionAndStartEnd = command.substring("event".length()).trim();
        String[] parts = taskDescriptionAndStartEnd.split("/from", 2);
        if (parts.length != 2) {
            throw new HarperInvalidEventException();
        }
        String description = parts[0].trim();
        String[] startAndEnd = parts[1].trim().split("/to", 2);
        if (startAndEnd.length != 2 || description.isEmpty()) {
            throw new HarperInvalidEventException();
        }
        String start = startAndEnd[0].trim();
        String end = startAndEnd[1].trim();
        if (start.isEmpty() || end.isEmpty()) {
            throw new HarperInvalidEventException();
        }
        try {
            LocalDateTime startFormatted = LocalDateTime.parse(start, DATE_TIME_FORMATTER);
            LocalDateTime endFormatted = LocalDateTime.parse(end, DATE_TIME_FORMATTER);
            Task newEvent = new Event(description, false, startFormatted, endFormatted);
            return new AddCommand(newEvent);
        } catch (DateTimeParseException e) {
            throw new HarperInvalidDateTimeException();
        }
    }

    /**
     * Parses the delete command.
     *
     * @param command Delete command entered by user.
     * @return DeleteCommand that delete the task from the list.
     */
    private static Command handleDelete(String command) {
        String[] commands = command.split(" ", 2);
        try {
            int taskIndex = Integer.parseInt(commands[1].trim()) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new HarperInvalidIndexException();
        }
    }

    /**
     * Parses the mark or unmark command.
     *
     * @param command Mark or unmark command entered by user.
     * @return MarkCommand that marks or unmarks the task.
     */
    private static Command handleMark(String command) {
        String[] commands = command.split(" ", 2);
        try {
            int taskIndex = Integer.parseInt(commands[1].trim()) - 1;
            boolean isMarked = commands[0].equals("mark");
            return new MarkCommand(taskIndex, isMarked);
        } catch (NumberFormatException e) {
            throw new HarperInvalidIndexException();
        }
    }
}
