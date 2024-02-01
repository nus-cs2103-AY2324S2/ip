package duke;

import duke.task.*;
import exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private String identify(String command) {
        String[] strings = command.split(" ");
        return strings[0];
    }

    public boolean parse(String str, IOHandler io, TaskList taskList, Storage storage) throws FileIOException {
        String command = identify(str);
        try {
            switch (command) {
                case "deadline":
                    Deadline tempDeadline = setDeadline(str.substring(8));
                    taskList.addTask(tempDeadline);
                    io.echoAdd(tempDeadline, taskList);
                    storage.saveInFile(taskList);
                    return true;
                case "event":
                    Event tempEvent = setEvent(str.substring(5));
                    taskList.addTask(tempEvent);
                    io.echoAdd(tempEvent, taskList);
                    storage.saveInFile(taskList);
                    return true;
                case "todo":
                    Todo tempToDo = setToDo(str.substring(4));
                    taskList.addTask(tempToDo);
                    io.echoAdd(tempToDo, taskList);
                    storage.saveInFile(taskList);
                    return true;
                case "delete":
                    Task tempDelete = deleteTask(str, taskList);
                    storage.saveInFile(taskList);
                    io.divider();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(" " + tempDelete.toString());
                    System.out.println("Now you have " + taskList.size() +" tasks in the list.");
                    io.divider();
                    return true;
                case "mark":
                    Task tempMark = setDone(str, taskList);
                    io.divider();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tempMark);
                    io.divider();
                    return true;
                case "unmark":
                    Task tempUnmark = setUndone(str, taskList);
                    io.divider();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tempUnmark);
                    io.divider();
                    return true;
                case "list":
                    io.display(taskList);
                    return true;
                case "bye":
                    io.exit();
                    return false;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException | IllegalDateFormatException e) {
            io.divider();
            System.out.println("ERROR : " + e.getMessage());
            io.divider();
        }
        return true;
    }

    private Deadline setDeadline(String str) throws IllegalDateFormatException, SyntaxException {
        String[] arr = str.split("/by ");
        try {
            if (arr.length != 2) {
                throw new SyntaxException("Please check the command syntax");
            }
            return new Deadline(arr[0], parseDateTime(arr[1]));
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Wrong Format for the date kindly put in \nyyyy-MM-dd HHmm.", str);
        }
    }

    private Event setEvent(String str) throws IllegalDateFormatException, SyntaxException {
        try {
            String[] event = str.split("/from | /to ");
            if (event.length != 3) {
                throw new SyntaxException("Please check the command syntax");
            }
            return new Event(event[0],LocalDateTime.parse(event[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                    LocalDateTime.parse(event[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Wrong Format for the date kindly put in \nyyyy-MM-dd HHmm.", str);
        }
    }

    private Todo setToDo(String str)
            throws DukeException {
        String[] todo = str.split("todo ?+");
        if (todo.length > 0) {
            return new Todo(todo[0]);
        } else {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private LocalDateTime parseDateTime(String dateTime) throws IllegalDateFormatException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new IllegalDateFormatException("Incorrect format", dateTime);
        }
    }

    private Task deleteTask(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {

            String[] string = str.split(" ");
            if (string.length != 2) {
                throw new SyntaxException("Need only index number after delete");
            }
            int index = Integer.parseInt(string[1]);
            return taskList.removeIndex(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number after delete");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }
    }

    private int getIndexOfMark(String str) {
        return Integer.parseInt(str.substring(5));
    }

    private int getIndexOfUnmark(String str) {
        return Integer.parseInt(str.substring(7));
    }

    private Task setDone(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {
            int index = getIndexOfMark(str);
            taskList.markTask(index);
            return taskList.get(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number to mark as done");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }
    }

    private Task setUndone(String str, TaskList taskList) throws SyntaxException, SemanticException {
        try {
            int index = getIndexOfUnmark(str);
            taskList.unmarkTask(index);
            return taskList.get(index);
        } catch (NumberFormatException e) {
            throw new SyntaxException("Need only index number to mark as undone");
        } catch (IndexOutOfBoundsException e) {
            throw new SemanticException("Index is out of bounds, please write correct index number");
        }

    }
}