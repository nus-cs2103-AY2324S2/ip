package util;

import duke.TaskList;
import task.Deadline;
import task.Event;
import task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * The util.Parser class is responsible for parsing various user inputs related to tasks and dates.
 *
 * @author Tan Qin Yong
 */
public class Parser {
    /**
     * Constructs a util.Parser object.
     */
    public Parser() {}

    public String parseByeCommand(Ui ui, Storage storage, TaskList taskList) {
        storage.saveToFile(taskList);
        return ui.printBye();
    }

    public String parseListCommand(Ui ui, TaskList taskList) {
        String toPrint = "";
        toPrint += ui.printLine();
        toPrint += taskList.printAllTasks();
        toPrint += ui.printLine();
        return toPrint;
    }

    public String parseDeleteCommand(Ui ui, Storage storage, TaskList taskList, String[] commandArr) {
        String toPrint = "";
        toPrint += ui.printLine();

        try {
            int taskNo = Integer.parseInt(commandArr[1]);
            toPrint += taskList.deleteTask(taskNo);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            toPrint += ui.printOperationError(e);
        }
        storage.saveToFile(taskList);
        toPrint += ui.printLine();

        return toPrint;
    }

    public String parseFindCommand(Ui ui, TaskList taskList, String[] commandArr) {
        String toPrint = "";
        try {
            toPrint += ui.printLine();
            String keyWord = commandArr[1];
            toPrint += taskList.findTask(keyWord);
            toPrint += ui.printLine();
        } catch (IllegalArgumentException e) {
            toPrint += ui.printUnknown();
        }

        return toPrint;
    }

    public String parseMarkCommand(Ui ui, Storage storage, TaskList taskList, String[] commandArr) {
        String toPrint = "";

        toPrint += ui.printLine();
        toPrint += ui.printMark();

        try {
            int taskNo = Integer.parseInt(commandArr[1]);
            toPrint += taskList.markDoneAtInd(taskNo);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            toPrint += ui.printOperationError(e);
        }
        storage.saveToFile(taskList);
        toPrint += ui.printLine();

        return toPrint;
    }

    public String parseUnmarkCommand(Ui ui, Storage storage, TaskList taskList, String[] commandArr) {
        String toPrint = "";

        toPrint += ui.printLine();
        toPrint += ui.printUnmark();
        try {
            int taskNo = Integer.parseInt(commandArr[1]);
            toPrint += taskList.markNotDoneAtInd(taskNo);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            toPrint += ui.printOperationError(e);
        }
        storage.saveToFile(taskList);
        toPrint += ui.printLine();

        return toPrint;
    }

    public String parseDeadlineCommand(Ui ui, Storage storage, TaskList taskList, String fullCommand) {
        String toPrint = "";

        toPrint += ui.printLine();
        String[] splitCommand = parseDeadline(fullCommand);
        try {
            String taskDescription = splitCommand[0];
            if (taskDescription.isEmpty()) {
                toPrint += ui.printEmptyDescription();
            }
            String byDate = splitCommand[1];
            Deadline dl = new Deadline(taskDescription, parseDate(byDate));
            toPrint += taskList.addTask(dl, false);
        } catch (IndexOutOfBoundsException | IllegalArgumentException | DateTimeParseException e) {
            toPrint += ui.printDeadlineError(e);
        }

        storage.saveToFile(taskList);
        toPrint += ui.printLine();

        return toPrint;
    }

    public String parseEventCommand(Ui ui, Storage storage, TaskList taskList, String fullCommand) {
        String toPrint = "";

        toPrint += ui.printLine();
        String[] splitCommand = parseEvent(fullCommand);
        try {
            String taskDescription = splitCommand[0];
            if (taskDescription.isEmpty()) {
                toPrint += ui.printEmptyDescription();
            }
            String from = splitCommand[1];
            String to = splitCommand[2];
            Event event = new Event(taskDescription, parseDate(from), parseDate(to));
            toPrint += taskList.addTask(event, false);
        } catch (IndexOutOfBoundsException | IllegalArgumentException | DateTimeParseException e) {
            toPrint += ui.printEventError(e);
        }

        storage.saveToFile(taskList);
        toPrint += ui.printLine();

        return toPrint;
    }

    public String parseToDoCommand(Ui ui, Storage storage, TaskList taskList, String fullCommand) {
        String toPrint = "";

        toPrint += ui.printLine();
        fullCommand = parseToDo(fullCommand);
        if (fullCommand.isEmpty()) {
            toPrint += ui.printEmptyDescription();
        }

        ToDo toDo = new ToDo(fullCommand);
        toPrint += taskList.addTask(toDo, false);
        storage.saveToFile(taskList);
        toPrint += ui.printLine();

        return toPrint;
    }

    /**
     * Parses the user input for a deadline command.
     *
     * @param deadlineCommand The user input for the deadline command.
     * @return An array containing the task description and deadline date.
     */
    public String[] parseDeadline(String deadlineCommand) {
        deadlineCommand = deadlineCommand.replace("deadline", "")
                                         .replace("/by", "/");
        String[] splitCommand = deadlineCommand.split("/");
        splitCommand = Arrays.stream(splitCommand)
                              .map(String::trim)
                              .toArray(String[]::new);
        return splitCommand;
    }

    /**
     * Parses the user input for an event command.
     *
     * @param eventCommand The user input for the event command.
     * @return An array containing the task description, start date, and end date.
     */
    public String[] parseEvent(String eventCommand) {
        eventCommand = eventCommand.replace("event", "")
                                   .replace("/from", "/")
                                   .replace("/to", "/");
        String[] splitCommand = eventCommand.split("/");
        splitCommand = Arrays.stream(splitCommand)
                             .map(String::trim)
                             .toArray(String[]::new);
        return splitCommand;
    }

    /**
     * Parses the user input for a to-do command.
     *
     * @param toDoCommand The user input for the to-do command.
     * @return The task description.
     */
    public String parseToDo(String toDoCommand) {
        toDoCommand = toDoCommand.replace("todo", "").trim();
        return toDoCommand;
    }

    /**
     * Parses the date string and converts it to a LocalDate object using the specified format.
     *
     * @param date The date string to be parsed.
     * @return The LocalDate object representing the parsed date.
     * @throws DateTimeParseException If the date string cannot be parsed.
     */
    public LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
