package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.actions.AddTask;
import duke.actions.DeleteTask;
import duke.actions.FindTask;
import duke.actions.ListTask;
import duke.actions.MarkTask;
import duke.actions.UnmarkTask;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDo;




/**
 * The Parser class is responsible for interpreting user input and executing corresponding task operations.
 * <p>
 * It serves as the intermediary between the user interface and the application's logic, parsing the input
 * commands to perform actions such as adding, listing, marking, unmarking, deleting tasks, and handling
 * task-related exceptions. The class supports a variety of commands, including "todo", "deadline", "event",
 * "mark", "unmark", and "delete", each requiring specific input formats and handling potential input errors
 * through custom exceptions.
 * </p>
 * <p>
 * Upon receiving input, the Parser decodes the command, validates it, and then initiates the appropriate action
 * by creating instances of action classes like AddTask, DeleteTask, ListTask, MarkTask, and UnmarkTask, or directly
 * manipulating tasks through the storage class. It also provides feedback or error messages based on the outcomes
 * of operations or the nature of exceptions encountered.
 * </p>
 * @see duke.storage.Storage
 * @see duke.tasks.Task
 * @see duke.exception.DukeException
 */
public class Parser {
    private Storage storage;
    public Parser(Storage storage) {
        this.storage = storage;
    }

    /**
     * Parses the input command to perform task operations such as adding, listing, marking, unmarking,
     * and deleting tasks. This method handles user input,
     * executing commands based on the input string. It supports operations for managing tasks,
     * including "todo", "deadline", "event", and status updates like "mark" and "unmark".
     * Error handling is implemented for invalid inputs or commands through custom exceptions.
     * @param input The user input string representing a command and its arguments.
     * @return A response message indicating the outcome of the operation, including error messages for invalid input.
     * @throws DukeException If the input is invalid or does not meet command requirements.
     */
    public String parse(String input) {
        ArrayList<Task> inventory = storage.load();
        try {
            assert inventory.size() < 0 : "Task List should not have a negative number of tasks";
            if (input.toLowerCase().equalsIgnoreCase("list")) {
                if (!input.trim().equals("list")) {
                    throw new DukeException("OOPS!!! This is an invalid call of list command.");
                } else {
                    ListTask lister = new ListTask(storage);
                    return lister.list();
                }
            } else if (input.toLowerCase().startsWith("mark")) {
                if (input.trim().equals("mark")) {
                    throw new DukeException("OOPS!!! Invalid Command, highlight which task to mark");
                } else {
                    int index = Integer.parseInt(input.substring(5));
                    if (index < 1 || index > inventory.size()) {
                        throw new DukeException("OOPS!!! The task number you are trying to mark does not exist. ");
                    }
                    MarkTask marker = new MarkTask(storage, index);
                    return marker.mark();
                }
            } else if (input.toLowerCase().startsWith("unmark")) {
                if (input.trim().equals("unmark")) {
                    throw new DukeException("OOPS!!! Invalid Command, highlight which task to unmark");
                } else {
                    int index = Integer.parseInt(input.substring(7));
                    if (index < 1 || index > inventory.size()) {
                        throw new DukeException("OOPS!!! The task number you are trying to unmark does not exist. ");
                    }
                    UnmarkTask unmarker = new UnmarkTask(storage, index);
                    return unmarker.unmark();
                }
            } else if (input.toLowerCase().startsWith("todo")) {
                if (input.trim().equals("todo")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    String task = input.substring(5);
                    ToDo todoTask = new ToDo(task);
                    AddTask adder = new AddTask(storage, todoTask);
                    adder.add();
                    return adder.toString();
                }
            } else if (input.toLowerCase().startsWith("deadline")) {
                String validFormat = "Please input in format: *deadline* *TASK* /*by yyyy-mm-dd TIME*";
                if (input.trim().equals("deadline")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty. " + validFormat);
                } else if (!input.contains("/")) {
                    throw new DukeException("OOPS!!! Your deadline command's format is invalid. " + validFormat);
                } else if (input.substring(9).split("/").length < 2) {
                    throw new DukeException("OOPS!!! Your deadline command's format is invalid. " + validFormat);
                } else {
                    String[] parts = input.substring(9).split("/");
                    LocalDateTime dateTime;
                    try {
                        String dateTimeString = parts[1].substring(3).trim();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        dateTime = LocalDateTime.parse(dateTimeString, formatter);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("OOPS!!! The date format is invalid. " + validFormat);
                    }
                    Deadlines deadlineTask = new Deadlines(parts[0], dateTime);
                    AddTask adder = new AddTask(storage, deadlineTask);
                    adder.add();
                    String temp = adder.toString();
                    //Also include the tasks with the same deadline as the following task.
                    String similarities = "";
                    int counter = 1;
                    for (int i = 0; i < inventory.size() - 1; i++) {
                        Task t = inventory.get(i);
                        if (!t.identifier().equals("[D]")) {
                            continue;
                        }
                        if (t.getDeadline().equals(dateTime.toLocalDate())) {
                            similarities += counter + ": " + t.toString() + "\n";
                        }
                        counter++;
                    }
                    if (similarities.isBlank()) {
                        temp += "\n\n\nThere are no other deadlines on the same day as this Task's deadline :)";
                        return temp;
                    } else {
                        temp += "\n\n\nThe following are the tasks with the same deadlines!: \n" + similarities;
                        return temp;
                    }
                }
            } else if (input.toLowerCase().startsWith("event")) {
                String validFormat = "Please input in the format: "
                                     +
                                    "Event *TASK* /*from YYYY-MM-DD TIME* /*to YYYY-MM-DD TIME*";
                if (input.trim().equals("event")) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty. " + validFormat);
                } else if (!input.contains("/")) {
                    throw new DukeException("OOPS!!! Your event command's format is invalid. " + validFormat);
                } else if (input.substring(6).split("/").length < 3) {
                    throw new DukeException("OOPS!!! Your event command's format is invalid. " + validFormat);
                } else {
                    String[] parts = input.substring(6).split("/");
                    LocalDateTime dateTime1;
                    LocalDateTime dateTime2;
                    try {
                        String dateTimeString1 = parts[1].substring(5).trim();
                        String dateTimeString2 = parts[2].substring(3).trim();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        dateTime1 = LocalDateTime.parse(dateTimeString1, formatter);
                        dateTime2 = LocalDateTime.parse(dateTimeString2, formatter);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("OOPS!!! The date format is invalid. " + validFormat);
                    }
                    Events eventTask = new Events(parts[0], dateTime1, dateTime2);
                    AddTask adder = new AddTask(storage, eventTask);
                    adder.add();
                    return adder.toString();
                }
            } else if (input.startsWith("delete")) {
                String validFormat = "Please input in format: delete *index*";
                if (input.trim().equals("delete")) {
                    throw new DukeException("OOPS!!! Invalid delete command " + validFormat);
                } else {
                    int index = Integer.parseInt(input.substring(7));
                    if (index < 1 || index > inventory.size()) {
                        throw new DukeException("OOPS!!! The task number you are trying to delete does not exist. ");
                    }
                    DeleteTask deleter = new DeleteTask(storage, index);
                    deleter.delete();
                    return deleter.toString();
                }
            } else if (input.startsWith("find")) {
                String validFormat = "Please input in format: find *Task*";
                if (input.trim().equals("find")) {
                    throw new DukeException("OOPS!! Invalid find command " + validFormat);
                } else {
                    String task = input.substring(5);
                    FindTask finder = new FindTask(storage, task);
                    finder.createMatches();
                    return finder.toString();
                }
            } else {
                return "OOPS!!! I'm sorry, but that's an invalid command :-(";
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
