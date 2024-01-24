package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import controller.AddTaskCommand;
import controller.Command;
import controller.DeleteTaskCommand;
import controller.InvalidCommand;
import controller.ListTaskCommand;
import controller.MarkTaskCommand;
import controller.UnmarkTaskCommand;
import model.Deadline;
import model.Event;
import model.Task;
import model.TaskList;
import model.ToDo;

public class Parser {
    private final String command;
    private final TaskList taskList;
    public Parser(String command, TaskList taskList) {
        this.command = command;
        this.taskList = taskList;
    }

    public Command parse() {
        String[] splitTask = command.split(" ", 2);
        Task task;
        Command command = null;
        switch(splitTask[0]) {
        case ListTaskCommand.COMMAND_WORD:
            command = new ListTaskCommand(taskList);
            break;
        case MarkTaskCommand.COMMAND_WORD:
            try {
                command = parseMark(splitTask);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case UnmarkTaskCommand.COMMAND_WORD:
            try {
                command = parseUnmark(splitTask);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case AddTaskCommand.TODO:
            try {
                task = parseToDo(splitTask);
                command = new AddTaskCommand(task, taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case AddTaskCommand.EVENT:
            try {
                task = parseEvent(splitTask);
                command = new AddTaskCommand(task, taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case AddTaskCommand.DEADLINE:
            try {
                task = parseDeadline(splitTask);
                command = new AddTaskCommand(task, taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break;
        case DeleteTaskCommand.COMMAND_WORD:
            try {
                command = parseDelete(splitTask);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            break; // Add break statement here
        default:
            command = new InvalidCommand();
        }
        return command;
    }


    private static ToDo parseToDo(String[] todo) throws DukeException {
        if (todo.length != 2 || todo[1].isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new ToDo(todo[1]);
    }

    private static Event parseEvent(String[] event) throws DukeException {
        if (event.length != 2 || event[1].isEmpty()) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] splitEvent = event[1].split(" /from ", 2);
        if (splitEvent.length != 2) {
            throw new DukeException("Invalid input. Use: event event_title /from dd-mm-yyyy HHmm /to HHmm");
        }
        String[] splitDuration = splitEvent[1].split(" /to ", 2);
        if (splitDuration.length != 2) {
            throw new DukeException("Invalid input. Use: event event_title /from dd-mm-yyyy HHmm /to HHmm");
        }

        String title = splitEvent[0];
        String from = splitDuration[0];
        String to = splitDuration[1];
        String temp = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy HHmm");
            LocalDateTime fromDateTime = LocalDateTime.parse(from, formatter);
            temp = fromDateTime.format(DateTimeFormatter.ofPattern("dd-M-yyyy")) + " " + to;
            LocalDateTime toTime = LocalDateTime.parse(temp, formatter);
            return new Event(title, fromDateTime, toTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date & time input. Use /from dd-mm-yyyy HHmm /to HHmm");
        }
    }

    private static Deadline parseDeadline(String[] deadline) throws DukeException {
        if (deadline.length != 2 || deadline[1].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] splitDeadline = deadline[1].split(" /by ", 2);
        if (splitDeadline.length != 2) {
            throw new DukeException("Invalid input. Use: deadline deadline_title /by dd-mm-yyyy HHmm");
        }
        String title = splitDeadline[0];
        String time = splitDeadline[1];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            return new Deadline(title, dateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Use dd-mm-yyyy HHmm as the date format.");
        }
    }

    private MarkTaskCommand parseMark(String[] command) throws DukeException {
        try {
            int markIndex = Integer.parseInt(command[1]) - 1;
            try {
                return new MarkTaskCommand(markIndex, this.taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index of task cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The index you've input is not an integer.");
        }
    }
    private UnmarkTaskCommand parseUnmark(String[] command) throws DukeException {
        try {
            int unmarkIndex = Integer.parseInt(command[1]) - 1;
            try {
                return new UnmarkTaskCommand(unmarkIndex, this.taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index of task cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The index you've input is not an integer.");
        }
    }

    private DeleteTaskCommand parseDelete(String[] command) throws DukeException {
        try {
            int deleteIndex = Integer.parseInt(command[1]) - 1;
            try {
                return new DeleteTaskCommand(deleteIndex, this.taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index of task cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The index you've input is not an integer.");
        }
    }
}
