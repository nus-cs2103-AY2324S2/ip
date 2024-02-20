package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UndoCommand;
import duke.data.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Parser class to parse commands
 */
public class Parser {

    /**
     * Returns task stored in given line entry.
     *
     * @param taskStored String line entry of task stored in txt file
     * @return ToDo, Event or Deadline Task stored in given line.
     */
    public static Task parseFromStorage(String taskStored) {
        assert !taskStored.isEmpty() : "Task stored cannot be empty string";
        String[] taskLine = taskStored.split(";;;"); // eg. [task code, mark status, description & date if any]
        String taskCode = taskLine[0];
        boolean isMarked = taskLine[1].equals("1");
        String[] details = taskLine[2].split(":::"); // eg. [desc] or [desc, end] or [desc, start, end]
        String taskName = details[0];
        Task decodedTask = new ToDo(taskName); // default ToDo task

        switch (taskCode) {
        case "T":
            break;
        case "D":
            String taskDeadline = details[1];
            decodedTask = new Deadline(taskName, parseStorageDate(taskDeadline));
            break;
        case "E":
            String taskStartDate = details[1];
            String taskEndDate = details[2];
            decodedTask = new Event(taskName, parseStorageDate(taskStartDate),
                    parseStorageDate(taskEndDate));
            break;
        default:
            assert false : "Unknown task code from storage";
        }
        if (isMarked) {
            decodedTask.setMarked();
        }
        return decodedTask;
    }

    /**
     * Returns command of specified instruction.
     *
     * @param fullCmd String full command input by user.
     * @return Command object.
     * @throws DukeException  If unknown command was given.
     */
    public static Command parse(String fullCmd, Command lastCommand) throws DukeException {
        String cmd = fullCmd.split(" ")[0]; // eg. [cmd, ..]
        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMark(fullCmd, true);
        case "unmark":
            return parseMark(fullCmd, false);
        case "todo":
            return parseToDo(fullCmd);
        case "deadline":
            return parseDeadline(fullCmd);
        case "event":
            return parseEvent(fullCmd);
        case "delete":
            return parseDelete(fullCmd);
        case "find":
            return parseFind(fullCmd);
        case "undo":
            return new UndoCommand(lastCommand);
        default:
            throw new DukeException("Sorry, what do you mean?\n");
        }
    }

    private static MarkCommand parseMark(String fullCmd, boolean isComplete)
            throws DukeException {
        String markFormTxt = "Sorry! To mark or unmark tasks, please do\n"
                + "\t(un)mark (number)\n";
        try {
            int updateIndex = Integer.parseInt(fullCmd.split(" ")[1]);
            return new MarkCommand(updateIndex, isComplete);
        } catch (NumberFormatException e) {
            throw new DukeException(markFormTxt);
        }
    }

    private static AddCommand parseDeadline(String fullCmd) throws DukeException {
        String deadlineFormTxt = "Sorry! Please use the given format for deadline tasks:\n"
                + "\tdeadline (description) /by DD/MM/YYYY hhmm\n"
                + "\teg. deadline homework /by 30/01/2023 1800\n";

        try {
            String[] fullCmdArr = fullCmd.split("/by ", 2); // separate into [desc, by ..]
            if (fullCmdArr.length != 2) {
                throw new DukeException(deadlineFormTxt);
            }
            String name = fullCmdArr[0].substring(9); // "deadline ..."
            String dueDate = fullCmdArr[1];
            if (name.trim().isEmpty()) {
                throw new DukeException("Description Blank");
            } else if (dueDate.trim().isEmpty()) {
                throw new DukeException(deadlineFormTxt);
            }
            Task newTask = new Deadline(name, parseCmdDate(dueDate));
            return new AddCommand(newTask);
        } catch (Exception e) {
            throw new DukeException(deadlineFormTxt);
        }
    }

    private static AddCommand parseEvent(String fullCmd) throws DukeException {
        String eventFormTxt = "Sorry! Please use the given format for event tasks:\n"
                + "\tevent (description) /from DD/MM/YYYY hhmm /to DD/MM/YYYY hhmm\n"
                + "\teg. event assignment /from 30/01/2023 1200 /to 12/02/2023 2359\n";
        try {
            String[] fullCmdArr = fullCmd.split("/from ");
            if (fullCmdArr.length != 2) {
                throw new DukeException(eventFormTxt);
            }
            String[] fromToArr = fullCmdArr[1].split(" /to ");
            if (fromToArr.length != 2) {
                throw new DukeException(eventFormTxt);
            }

            String name = fullCmdArr[0].substring(6); // "event ..."
            if (name.trim().isEmpty()) {
                throw new DukeException("Description Blank");
            }

            Task newTask = new Event(name, parseCmdDate(fromToArr[0]),
                    parseCmdDate(fromToArr[1]));
            return new AddCommand(newTask);
        } catch (Exception e) {
            throw new DukeException(eventFormTxt);
        }
    }

    private static AddCommand parseToDo(String fullCmd) throws DukeException {
        assert !fullCmd.isEmpty() : "Command cannot be empty";
        if (fullCmd.length() < 5) { // "Todo ..."
            throw new DukeException("Description Blank");
        }
        String name = fullCmd.substring(5);
        if (name.trim().isEmpty()) {
            throw new DukeException("Description Blank");
        }
        Task newTask = new ToDo(name);
        return new AddCommand(newTask);
    }

    private static DeleteCommand parseDelete(String fullCmd) throws DukeException {
        assert !fullCmd.isEmpty() : "Command cannot be empty";

        String deleteFormTxt = "Did you mean to delete the task? Please do this:\n"
                + "\tdelete (number)\n";
        try {
            int deleteIndex = Integer.parseInt(fullCmd.split(" ")[1]);
            return new DeleteCommand(deleteIndex);
        } catch (NumberFormatException e) {
            throw new DukeException(deleteFormTxt);
        }
    }

    private static FindCommand parseFind(String fullCmd)
            throws DukeException {
        assert !fullCmd.isEmpty() : "Command cannot be empty";
        String findFormTxt = "Please specify the keyword you wish to find!\n";
        try {
            String query = fullCmd.split(" ", 2)[1];
            if (query.trim().isEmpty()) {
                throw new DukeException(findFormTxt);
            }
            return new FindCommand(query);
        } catch (Exception e) {
            throw new DukeException(findFormTxt);
        }
    }

    private static LocalDateTime parseCmdDate(String dateTime) {
        assert !dateTime.isEmpty() : "Date cannot be empty";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    private static LocalDateTime parseStorageDate(String date) {
        assert !date.isEmpty() : "Date cannot be empty";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(date, formatter);
    }

}
