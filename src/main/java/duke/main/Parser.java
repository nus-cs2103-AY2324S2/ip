package duke.main;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.ToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.command.ViewCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.FindCommand;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input and makes sense of user commands.
 */
public class Parser {

     static Command parse(String userInput) throws DukeException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark")) {
            int num = parseTaskNumber(userInput, "mark");
            return new MarkCommand(num);
        } else if (userInput.startsWith("unmark")) {
            int num = parseTaskNumber(userInput, "unmark");
            return new UnMarkCommand(num);
        } else if (userInput.startsWith("todo")) {
            String todo = userInput.replace("todo", "").trim();
            processEmptyDescription(todo, "todo");
            return new ToDoCommand(todo);
        } else if (userInput.startsWith("deadline")) {
            String[] deadline = parseDeadlineInput(userInput, "deadline");
            return new DeadlineCommand(deadline[0], deadline[1]);
        } else if (userInput.startsWith("event")) {
            String[] event = parseEventInput(userInput, "event");
            return new EventCommand(event[0], event[1], event[2]);
        } else if(userInput.startsWith("delete")) {
            int num = parseTaskNumber(userInput, "delete");
            return new DeleteCommand(num);
        } else if (userInput.startsWith("view")) {
            String dateInput = userInput.replace("view", "").trim();
            processEmptyDescription(dateInput, "view");
            LocalDate targetDate = parseOnDateTime(dateInput);
            return new ViewCommand(targetDate);
        } else if (userInput.startsWith("find")) {
            String findWord = userInput.replace("find", "").trim();
            processEmptyDescription(findWord, "find");
            return new FindCommand(findWord);
        } else {
            throw new DukeException("Error! I don't know what that means.");
        }
    }

    /**
     * Extracts the task number from the user input, which is a command, and validates it.
     *
     * @param input User input, which is a command containing the task number.
     * @param command Type of task.
     * @return Parsed and validated one-indexed task number.
     * @throws DukeException If the task number is empty or invalid.
     */
    private static int parseTaskNumber(String input, String command) throws DukeException {
        String taskNumString = input.replace(command, "").trim();
        boolean isStringEmpty = taskNumString.isEmpty();
        boolean isInvalidTaskNumber = !taskNumString.matches("\\d+");
        if (isStringEmpty || isInvalidTaskNumber) {
            throw new DukeException("Error! Please provide a valid task number after '" + command + "'.");
        }
        int taskNumber = Integer.parseInt(taskNumString);
        return taskNumber;
    }

    /**
     * Processes and validates that the description is not empty.
     *
     * @param description Description to be checked.
     * @param task Type of task.
     * @throws DukeException If the description is empty.
     */
    private static void processEmptyDescription(String description, String task) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("Error! The description of a " + task + " cannot be empty.");
        }
    }

    private static LocalDate parseOnDateTime(String dateInput) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return LocalDate.parse(dateInput, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Error! Please provide a valid date format (MMM dd yyyy).");
        }
    }

    /**
     * Parses a string input of format "yyyy-MM-dd HHmm" into a LocalDateTime object.
     *
     * @param input String input representing a date and time in the format "yyyy-MM-dd HHmm".
     * @return LocalDateTime object parsed from the input.
     * @throws DukeException If the input date and time is not in the format "yyyy-MM-dd HHmm".
     */
    private static LocalDateTime parseDateTimeInput(String input) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Error! Please provide a valid date and time format (yyyy-MM-dd HHmm).");
        }
    }

    /**
     * Parses and validates the user input for deadline tasks.
     *
     * @param input User input containing the description and deadline.
     * @param command Type of task.
     * @return Array containing the parsed description and deadline.
     * @throws DukeException If the input is invalid or has wrong formatting.
     */
    private static String[] parseDeadlineInput(String input, String command) throws DukeException {
        String[] deadlineInput = input.replace(command, "").trim().split(" /by ");

        if (deadlineInput.length != 2) {
            throw new DukeException("Error! Please provide a valid description and deadline after '" + command + "'.");
        }

        String description = deadlineInput[0].trim();
        LocalDateTime by = parseDateTimeInput(deadlineInput[1].trim());

        processEmptyDescription(description, "deadline");

        return new String[]{description, by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))};
    }

    /**
     * Parses and validates the user input for event tasks.
     *
     * @param input User input for event tasks.
     * @param command Type of task.
     * @return Array containing the parsed description, start time, and end time of the event.
     * @throws DukeException If the input is invalid or has wrong formatting.
     */
    private static String[] parseEventInput(String input, String command) throws DukeException {
        String[] eventInput = input.replace(command, "").trim().split(" /from ");

        if (eventInput.length != 2) {
            throw new DukeException("Error! Please provide a valid description, start time, and end time after '" + command + "'.");
        }

        String description = eventInput[0].trim();
        String[] timeInput = eventInput[1].split(" /to ");

        if (timeInput.length != 2) {
            throw new DukeException("Error! Please provide a valid start time and end time after '/from' and '/to'.");
        }

        LocalDateTime startTime = parseDateTimeInput(timeInput[0].trim());
        LocalDateTime endTime = parseDateTimeInput(timeInput[1].trim());

        processEmptyDescription(description, "event");

        return new String[]{description, startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))};
    }
}
