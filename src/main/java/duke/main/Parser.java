package duke.main;

import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.ToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.command.OnCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//implement find
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
        } else if (userInput.startsWith("on")) {
            String dateInput = userInput.replace("on", "").trim();
            LocalDate targetDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return new OnCommand(targetDate);
        } else if (userInput.startsWith("find")) {
            String findWord = userInput.replace("find", "").trim();
            processEmptyDescription(findWord, "find");
            return new FindCommand(findWord);
        } else {
            throw new DukeException("\nError! I don't know what that means. Types of tasks are limited to ToDos, Deadlines and Events.\n");
        }
    }

    /**
     * Parses the task number from the user input and validates it.
     * Throws a duke.exception.DukeException for empty task number or invalid task number that is outside the indexes of the list.
     *
     * @param input   The user input containing the task number.
     * @param command The type of task.
     * @return The parsed and validated task number.
     * @throws DukeException If the task number is invalid or empty.
     */
    private static int parseTaskNumber(String input, String command) throws DukeException {
        String taskNumString = input.replace(command, "").trim();
        if (taskNumString.isEmpty() || !taskNumString.matches("\\d+")) {
            throw new DukeException("Error! Please provide a valid task number after '" + command + "'.");
        }
        int taskNumber = Integer.parseInt(taskNumString);
        return taskNumber;
    }

    /**
     * Processes and validates that the description is not empty.
     * Throws a duke.exception.DukeException if the description is empty.
     *
     * @param description The description to be checked.
     * @param task        The type of task.
     * @throws DukeException If the description is empty.
     */
    private static void processEmptyDescription(String description, String task) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("\nError! The description of a " + task + " cannot be empty.\n");
        }
    }

    private static LocalDateTime parseDateTimeInput(String input) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("\nError! Please provide a valid date and time format (yyyy-MM-dd HHmm).\n");
        }
    }

    /**
     * Parses and validates the user input for deadline tasks.
     * Throws a duke.exception.DukeException for invalid input or wrong formatting.
     *
     * @param input   The user input containing the description and deadline.
     * @param command The type of task.
     * @return An array containing the parsed description and deadline.
     * @throws DukeException If the input is invalid or has wrong formatting.
     */
    private static String[] parseDeadlineInput(String input, String command) throws DukeException {
        String[] deadlineInput = input.replace(command, "").trim().split(" /by ");

        if (deadlineInput.length != 2) {
            throw new DukeException("\nError! Please provide a valid description and deadline after '" + command + "'.\n");
        }

        String description = deadlineInput[0].trim();
        LocalDateTime by = parseDateTimeInput(deadlineInput[1].trim());

        processEmptyDescription(description, "deadline");

        return new String[]{description, by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))};
    }

    /**
     * Parses and validates the user input for event tasks.
     * Throws a duke.exception.DukeException for invalid input or wrong formatting.
     *
     * @param input   The user input for event tasks.
     * @param command The type of task.
     * @return An array containing the parsed description, start time, and end time.
     * @throws DukeException If the input is invalid or has wrong formatting.
     */
    private static String[] parseEventInput(String input, String command) throws DukeException {
        String[] eventInput = input.replace(command, "").trim().split(" /from ");

        if (eventInput.length != 2) {
            throw new DukeException("\nError! Please provide a valid description, start time, and end time after '" + command + "'.\n");
        }

        String description = eventInput[0].trim();
        String[] timeInput = eventInput[1].split(" /to ");

        if (timeInput.length != 2) {
            throw new DukeException("\nError! Please provide a valid start time and end time after '/from' and '/to'.\n");
        }

        LocalDateTime startTime = parseDateTimeInput(timeInput[0].trim());
        LocalDateTime endTime = parseDateTimeInput(timeInput[1].trim());

        processEmptyDescription(description, "event");

        return new String[]{description, startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))};
    }

}
