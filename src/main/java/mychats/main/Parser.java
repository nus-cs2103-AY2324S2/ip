package mychats.main;

import mychats.command.*;
import mychats.command.UnmarkCommand;
import mychats.exception.MyChatsException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Parses user input and makes sense of user commands.
 */
public class Parser {

     static Command parse(String userInput) throws MyChatsException {
        if (userInput.equals("bye")) {
            return parseExitCommand();
        } else if (userInput.equals("list")) {
            return parseListCommand();
        } else if (userInput.startsWith("mark")) {
            return parseMarkCommand(userInput);
        } else if (userInput.startsWith("unmark")) {
            return parseUnmarkCommand(userInput);
        } else if (userInput.startsWith("todo")) {
            return parseTodoCommand(userInput);
        } else if (userInput.startsWith("deadline")) {
            return parseDeadlineCommand(userInput);
        } else if (userInput.startsWith("event")) {
            return parseEventCommand(userInput);
        } else if(userInput.startsWith("delete")) {
            return parseDeleteCommand(userInput);
        } else if (userInput.startsWith("view")) {
            return parseViewCommand(userInput);
        } else if (userInput.startsWith("find")) {
            return parseFindCommand(userInput);
        } else if (userInput.startsWith("undo")) {
            return parseUndoCommand();
        } else {
            throw new MyChatsException("Error! I don't know what that means.");
        }
    }

    private static MarkCommand parseMarkCommand(String input) throws MyChatsException {
        int num = parseTaskNumber(input, "mark");
        return new MarkCommand(num);
    }

    private static UndoCommand parseUndoCommand() {
        return new UndoCommand();
    }

    private static ExitCommand parseExitCommand() {
        return new ExitCommand();
    }

    private static ListCommand parseListCommand() {
        return new ListCommand();
    }

    private static UnmarkCommand parseUnmarkCommand(String input) throws MyChatsException {
        int num = parseTaskNumber(input, "unmark");
        return new UnmarkCommand(num);
    }

    private static ToDoCommand parseTodoCommand(String input) throws MyChatsException {
        String todo = input.replace("todo", "").trim();
        processEmptyDescription(todo, "todo");
        return new ToDoCommand(todo);
    }

    private static DeadlineCommand parseDeadlineCommand(String input) throws MyChatsException {
        String[] deadline = parseDeadlineInput(input, "deadline");
        return new DeadlineCommand(deadline[0], deadline[1]);
    }

    private static EventCommand parseEventCommand(String input) throws MyChatsException {
        String[] event = parseEventInput(input, "event");
        return new EventCommand(event[0], event[1], event[2]);
    }

    private static DeleteCommand parseDeleteCommand(String input) throws MyChatsException {
        int num = parseTaskNumber(input, "delete");
        return new DeleteCommand(num);
    }

    private static ViewCommand parseViewCommand(String input) throws MyChatsException {
        String dateInput = input.replace("view", "").trim();
        processEmptyDescription(dateInput, "view");
        LocalDate targetDate = parseOnDateTime(dateInput);
        return new ViewCommand(targetDate);
    }

    private static FindCommand parseFindCommand(String input) throws MyChatsException {
        String findWord = input.replace("find", "").trim();
        processEmptyDescription(findWord, "find");
        return new FindCommand(findWord);
    }

    /**
     * Extracts the task number from the user input, which is a command, and validates it.
     *
     * @param input User input, which is a command containing the task number.
     * @param command Type of task.
     * @return Parsed and validated one-indexed task number.
     * @throws MyChatsException If the task number is empty or invalid.
     */
    private static int parseTaskNumber(String input, String command) throws MyChatsException {
        String taskNumString = input.replace(command, "").trim();
        boolean isStringEmpty = taskNumString.isEmpty();
        boolean isInvalidTaskNumber = !taskNumString.matches("\\d+");
        if (isStringEmpty || isInvalidTaskNumber) {
            throw new MyChatsException("Error! Please provide a valid task number after '" + command + "'.");
        }
        int taskNumber = Integer.parseInt(taskNumString);
        return taskNumber;
    }

    /**
     * Processes and validates that the description is not empty.
     *
     * @param description Description to be checked.
     * @param task Type of task.
     * @throws MyChatsException If the description is empty.
     */
    private static void processEmptyDescription(String description, String task) throws MyChatsException {
        if (description.isEmpty()) {
            throw new MyChatsException("Error! The description of a " + task + " cannot be empty.");
        }
    }

    private static LocalDate parseOnDateTime(String dateInput) throws MyChatsException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return LocalDate.parse(dateInput, formatter);
        } catch (DateTimeParseException e) {
            throw new MyChatsException("Error! Please provide a valid date format (MMM dd yyyy).");
        }
    }

    /**
     * Parses a string input of format "yyyy-MM-dd HHmm" into a LocalDateTime object.
     *
     * @param input String input representing a date and time in the format "yyyy-MM-dd HHmm".
     * @return LocalDateTime object parsed from the input.
     * @throws MyChatsException If the input date and time is not in the format "yyyy-MM-dd HHmm".
     */
    private static LocalDateTime parseDateTimeInput(String input) throws MyChatsException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new MyChatsException("Error! Please provide a valid date and time format (yyyy-MM-dd HHmm).");
        }
    }

    /**
     * Parses and validates the user input for deadline tasks.
     *
     * @param input User input containing the description and deadline.
     * @param command Type of task.
     * @return Array containing the parsed description and deadline.
     * @throws MyChatsException If the input is invalid or has wrong formatting.
     */
    private static String[] parseDeadlineInput(String input, String command) throws MyChatsException {
        String[] deadlineInput = input.replace(command, "").trim().split(" /by ");

        if (deadlineInput.length != 2) {
            throw new MyChatsException("Error! Please provide a valid description and deadline after '" + command + "'.");
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
     * @throws MyChatsException If the input is invalid or has wrong formatting.
     */
    private static String[] parseEventInput(String input, String command) throws MyChatsException {
        String[] eventInput = input.replace(command, "").trim().split(" /from ");

        if (eventInput.length != 2) {
            throw new MyChatsException("Error! Please provide a valid description, start time, and end time after '" + command + "'.");
        }

        String description = eventInput[0].trim();
        String[] timeInput = eventInput[1].split(" /to ");

        if (timeInput.length != 2) {
            throw new MyChatsException("Error! Please provide a valid start time and end time after '/from' and '/to'.");
        }

        LocalDateTime startTime = parseDateTimeInput(timeInput[0].trim());
        LocalDateTime endTime = parseDateTimeInput(timeInput[1].trim());

        processEmptyDescription(description, "event");

        return new String[]{description, startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))};
    }
}
