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
        switch (commandWord) {
            case "todo":
                return addTodo(parts[1], tasks, ui, storage);
            case "deadline":
                return addDeadline(parts[1], tasks, ui, storage);
            case "event":
                return addEvent(parts[1], tasks, ui, storage);
            case "mark":
                return markOrUnmarkTask(parts[1], tasks, ui, storage, true);
            case "unmark":
                return markOrUnmarkTask(parts[1], tasks, ui, storage, false);
            case "delete":
                return deleteTask(parts[1], tasks, ui, storage);
            case "find":
                return findTask(parts[1], tasks, ui);
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
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks.getTasks());
        return ui.showTaskAdded(todo, tasks.getSize());
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param input  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @return The response message after adding the task.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static String addDeadline(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" /by ");
        if (splitInput.length < 2 || splitInput[0].isEmpty() || splitInput[1].isEmpty()) {
            throw new DukeException("The deadline description or date is missing.");
        }
        Deadline deadline = new Deadline(splitInput[0].trim(), splitInput[1].trim());
        tasks.addTask(deadline);
        storage.save(tasks.getTasks());
        return ui.showTaskAdded(deadline, tasks.getSize());
    }

    /**
     * Adds a event task to the list of tasks.
     *
     * @param input  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @return The response message after adding the task.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static String addEvent(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" /from ");
        if (splitInput.length < 2 || splitInput[0].isEmpty() || splitInput[1].isEmpty()) {
            throw new DukeException("The event description or start time is missing.");
        }
        String[] timeSplit = splitInput[1].split(" /to ");
        if (timeSplit.length < 2 || timeSplit[0].isEmpty() || timeSplit[1].isEmpty()) {
            throw new DukeException("The event end time or details are missing.");
        }
        Event event = new Event(splitInput[0].trim(), timeSplit[0].trim(), timeSplit[1].trim());
        tasks.addTask(event);
        storage.save(tasks.getTasks());
        return ui.showTaskAdded(event, tasks.getSize());
    }

    /**
     * Marks or unmarks a task in the list of tasks.
     *
     * @param input  The input command to parse.
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interactions.
     * @param storage The storage for tasks.
     * @return The response message after marking or unmarking the task.
     * @throws DukeException If the command is invalid or execution fails.
     */
    private static String markOrUnmarkTask(String input, TaskList tasks, Ui ui,
                                           Storage storage, boolean isMark) throws DukeException {
        try {
            int idx = Integer.parseInt(input) - 1;
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
