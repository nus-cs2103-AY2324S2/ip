package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
     * Parses the user input and executes the corresponding task operation.
     * @param input The user input command.
     * @return A string representing the result or feedback of the operation.
     */
    public String parse(String input) {
        ArrayList<Task> inventory = storage.load();
        try {
            assert inventory.size() < 0 : "Task List should not have a negative number of tasks";
            if (input.toLowerCase().equalsIgnoreCase("list")) {
                return handleListCommand(input);
            } else if (input.toLowerCase().startsWith("mark")) {
                return handleMarkCommand(input, inventory);
            } else if (input.toLowerCase().startsWith("unmark")) {
                return handleUnmarkCommand(input, inventory);
            } else if (input.toLowerCase().startsWith("todo")) {
                return handleTodoCommand(input);
            } else if (input.toLowerCase().startsWith("deadline")) {
                return handleDeadlineCommand(input);
            } else if (input.toLowerCase().startsWith("event")) {
                return handleEventCommand(input);
            } else if (input.startsWith("delete")) {
                return handleDeleteCommand(input, inventory);
            } else if (input.startsWith("find")) {
                return handleFindCommand(input);
            } else if (input.startsWith("sort")) {
                return handleSort(input);
            } 
            else {
                return "OOPS!!! I'm sorry, but that's an invalid command :-(";
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "list" command, displaying the list of tasks.
     * @param input The user input command.
     * @return A string representing the list of tasks.
     * @throws DukeException If an error occurs during the operation.
     */
    private String handleListCommand(String input) throws DukeException {
        if (!input.trim().equals("list")) {
            throw new DukeException("OOPS!!! This is an invalid call of list command.");
        } else {
            ListTask lister = new ListTask(storage);
            return lister.list();
        }
    }

    /**
     * Handles the "mark" command, marking a specified task as done.
     * @param input     The user input command.
     * @param inventory The list of tasks.
     * @return A string representing the result of the mark operation.
     * @throws DukeException If an error occurs during the operation.
     */
    private String handleMarkCommand(String input, ArrayList<Task> inventory) throws DukeException {
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
    }

    /**
     * Handles the "unmark" command, marking a specified task as not done.
     * @param input     The user input command.
     * @param inventory The list of tasks.
     * @return A string representing the result of the unmark operation.
     * @throws DukeException If an error occurs during the operation.
     */
    private String handleUnmarkCommand(String input, ArrayList<Task> inventory) throws DukeException {
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
    }

    /**
     * Handles the "todo" command, adding a new todo task.
     * @param input The user input command.
     * @return A string representing the result of the add operation.
     * @throws DukeException If an error occurs during the operation.
     */
    private String handleTodoCommand(String input) throws DukeException {
        if (input.trim().equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            String task = input.substring(5);
            ToDo todoTask = new ToDo(task);
            AddTask adder = new AddTask(storage, todoTask);
            adder.add();
            return adder.toString();
        }
    }

    /**
     * Handles the "deadline" command, adding a new deadline task.
     * @param input The user input command.
     * @return A string representing the result of the add operation.
     * @throws DukeException If an error occurs during the operation.
     */
    private String handleDeadlineCommand(String input) throws DukeException {
        ArrayList<Task> inventory = storage.load();
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
    }

    /**
     * Handles the "event" command, adding a new event task.
     * @param input The user input command.
     * @return A string representing the result of the add operation.
     * @throws DukeException If an error occurs during the operation.
     */
    private String handleEventCommand(String input) throws DukeException {
        String validFormat = "Please input in the format: Event *TASK* /*from YYYY-MM-DD TIME* /*to YYYY-MM-DD TIME*";
        if (input.trim().equals("event")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty. " + validFormat);
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
            } catch (Exception e) {
                throw new DukeException("OOPS!!! The date format is invalid. " + validFormat);
            }
            Events eventTask = new Events(parts[0], dateTime1, dateTime2);
            AddTask adder = new AddTask(storage, eventTask);
            adder.add();
            return adder.toString();
        }
    }

    /**
     * Handles the "delete" command, deleting a specified task.
     * @param input     The user input command.
     * @param inventory The list of tasks.
     * @return A string representing the result of the delete operation.
     * @throws DukeException If an error occurs during the operation.
     */
    private String handleDeleteCommand(String input, ArrayList<Task> inventory) throws DukeException {
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
    }

    /**
     * Handles the "find" command, finding tasks that match a specified keyword.
     * @param input The user input command.
     * @return A string representing the tasks that match the keyword.
     * @throws DukeException If an error occurs during the operation.
     */
    private String handleFindCommand(String input) throws DukeException {
        String validFormat = "Please input in format: find *Task*";
        if (input.trim().equals("find")) {
            throw new DukeException("OOPS!! Invalid find command " + validFormat);
        } else {
            String task = input.substring(5);
            FindTask finder = new FindTask(storage, task);
            finder.createMatches();
            return finder.toString();
        }
    }

    private String handleSort(String input) throws DukeException {
        ArrayList<Task> temp = new ArrayList<>(storage.load());
        temp.sort(new TaskComparator());
        String result = "Here is the SORTED Task List!: \n";
        for (Task i : temp) {
            result += i.toString() + "\n";
        }

        return result;
    }

}


class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        // Compare ToDo tasks, place them at the back
        if (task1 instanceof ToDo && task2 instanceof ToDo) {
            return 0;
        } else if (task1 instanceof ToDo) {
            return 1;
        } else if (task2 instanceof ToDo) {
            return -1;
        }

        // Compare Deadline and Events tasks based on their deadlines
        LocalDateTime deadline1 = getTaskDeadline(task1);
        LocalDateTime deadline2 = getTaskDeadline(task2);

        return deadline1.compareTo(deadline2);
    }

    private LocalDateTime getTaskDeadline(Task task) {
        if (task instanceof Deadlines) {
            return ((Deadlines) task).getAbsoluteDeadline();
        } else if (task instanceof Events) {
            return ((Events) task).getStart();
        } else {
            // ToDo tasks have no deadline
            return LocalDateTime.MAX;
        }
    }
}
