package duke;

import controller.*;
import model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private final String command;
    private final TaskList taskList;
    private final Storage storage;
    public Parser(String command, TaskList taskList, Storage storage) {
        this.command = command;
        this.taskList = taskList;
        this.storage = storage;
    }

    public void parse() {
        String[] splitTask = command.split(" ", 2);
        Task task;
        switch(splitTask[0]) {
            case "list":
                new ListTaskCommand(taskList).execute(storage);
                return;
            case "mark":
                try {
                    parseMark(splitTask).execute(storage);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            case "unmark":
                try {
                    parseUnmark(splitTask).execute(storage);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            case "todo":
                try {
                    task = parseToDo(splitTask);
                    new AddTaskCommand(task, taskList).execute(storage);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            case "event":
                try {
                    task = parseEvent(splitTask);
                    new AddTaskCommand(task, taskList).execute(storage);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            case "deadline":
                try {
                    task = parseDeadline(splitTask);
                    new AddTaskCommand(task, taskList).execute(storage);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            case "delete":
                try {
                    parseDelete(splitTask).execute(storage);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                return;
            default:
                new InvalidCommand().execute(storage);
        }
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
