package duke;

import java.util.ArrayList;

/**
 * Parses and executes commands for the Duke application.
 */
public class Parser {

    /**
     * Parses and executes a given command.
     *
     * @param input   The input command to parse.
     * @param tasks   The list of tasks.
     * @param storage The storage for tasks.
     * @return The response message after executing the command.
     * @throws DukeException If the command is invalid or execution fails.
     */
    public static String parseAndExecute(String input, TaskList tasks, Storage storage) throws DukeException {
        Ui ui = new Ui();
        if (input.equals("bye")) {
            return ui.showGoodbyeMessage();
        } else if (input.equals("list")) {
            return ui.showTaskList(tasks);
        }

        String[] parts = input.split(" ", 2);
        String commandWord = parts[0];
        String description = (parts.length > 1) ? parts[1] : "";

        switch (commandWord) {
            case "todo":
                return addTodo(description.trim(), tasks, ui, storage);
            case "deadline":
                return addDeadline(description.trim(), tasks, ui, storage);
            case "event":
                return addEvent(description.trim(), tasks, ui, storage);
            case "mark":
                return markOrUnmarkTask(description.trim(), tasks, ui, storage, true);
            case "unmark":
                return markOrUnmarkTask(description.trim(), tasks, ui, storage, false);
            case "delete":
                return deleteTask(description.trim(), tasks, ui, storage);
            case "find":
                return findTask(description.trim(), tasks, ui);
            default:
                throw new DukeException("Invalid Command. I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Adds a todo task to the list of tasks.
     *
     * @param description  The description of the todo task.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @return The response message after adding the task.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static String addTodo(String description, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(description);

        // Check for duplicates
        if (tasks.getTasks().stream().anyMatch(task -> task.equals(newTodo))) {
            return "This task already exists in your list.";
        }

        tasks.addTask(newTodo);
        storage.save(tasks.getTasks());
        return ui.showTaskAdded(newTodo, tasks.getSize());
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param deadlineDescription  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @return The response message after adding the task.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static String addDeadline(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" /by ");
        if (splitInput.length < 2 || splitInput[0].isEmpty() || splitInput[1].isEmpty()) {
            return "The deadline description or date is missing.";
        }
        Deadline newDeadline = new Deadline(splitInput[0].trim(), splitInput[1].trim());
        if (!tasks.isDuplicate(newDeadline)) {
            tasks.addTask(newDeadline);
            storage.save(tasks.getTasks());
            return ui.showTaskAdded(newDeadline, tasks.getSize());
        } else {
            return "This deadline is a duplicate and won't be added.";
        }
    }



    /**
     * Adds a event task to the list of tasks.
     *
     * @param eventDescription  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @return The response message after adding the task.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static String addEvent(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" /from ");
        if (splitInput.length < 2 || splitInput[0].isEmpty() || splitInput[1].isEmpty()) {
            return "The event description or start time is missing.";
        }
        String[] timeSplit = splitInput[1].split(" /to ");
        if (timeSplit.length < 2 || timeSplit[0].isEmpty() || timeSplit[1].isEmpty()) {
            return "The event end time or details are missing.";
        }
        Event newEvent = new Event(splitInput[0].trim(), timeSplit[0].trim(), timeSplit[1].trim());
        if (!tasks.isDuplicate(newEvent)) {
            tasks.addTask(newEvent);
            storage.save(tasks.getTasks());
            return ui.showTaskAdded(newEvent, tasks.getSize());
        } else {
            return "This event is a duplicate and won't be added.";
        }
    }

    /**
     * Marks or unmarks a task in the list of tasks.
     *
     * @param description  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @return The response message after marking or unmarking the task.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static String markOrUnmarkTask(String description, TaskList tasks, Ui ui,
                                           Storage storage, boolean isMark) throws DukeException {
        try {
            int idx = Integer.parseInt(description) - 1;
            Task task = tasks.getTask(idx);
            if (isMark) {
                task.markAsDone();
                storage.save(tasks.getTasks());
                return ui.showMarkTask(task);
            } else {
                task.unmark();
                storage.save(tasks.getTasks());
                return ui.showUnmarkTask(task);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a valid number.");
        }
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param input  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @return  The response message after deleting the task.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static String deleteTask(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int idx = Integer.parseInt(input) - 1;
            Task removedTask = tasks.removeTask(idx);
            storage.save(tasks.getTasks());
            return ui.showTaskDeleted(removedTask, tasks.getSize());
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a valid number.");
        }
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param input  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @return The response message after finding the tasks.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static String findTask(String input, TaskList tasks, Ui ui) {
        ArrayList<Task> foundTasks = tasks.findTasks(input);
        return ui.showFoundTasks(foundTasks);
    }
}
