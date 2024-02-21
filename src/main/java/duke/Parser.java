package duke;

import java.util.ArrayList;

/**
 * Represents a parser in the Duke application that interprets and executes user commands.
 *
 * @author Qin Boan
 */
public class Parser {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Parser instance.
     *
     * @param tasks The list of tasks to manage.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save and load tasks.
     */
    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses and executes the given input command.
     *
     * @param input The user input command to parse.
     * @throws DukeException If the command execution encounters any errors.
     */
    public String parse(String input) throws DukeException {

        if ("bye".equals(input)) {
            return handleExit();
        } else if ("list".equals(input)) {
            return handleList();
        } else if (input.startsWith("delete ")) {
            return handleDelete(input);
        } else if (input.startsWith("mark ")) {
            try {
                return handleMark(input);
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else if (input.startsWith("unmark ")) {
            try {
                return handleUnmark(input);
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                return"That's not a valid todo!";
            } else {
                String description = input.substring(5).trim();
                if (description.isEmpty()) {
                    return"That's not a valid todo!";
                } else {
                    return handleAdd(new ToDo(description));
                }
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() <= 9) {
                return "That's not a valid Deadline!";
            } else {
                String[] parts = input.substring(9).split(" /by ");
                if (parts.length == 2) {
                    try {
                        return handleAdd(new Deadline(parts[0], parts[1]));
                    } catch (DukeException e) {
                        return e.getMessage();
                    }
                } else {
                    return "That's not a valid Deadline!";
                }
            }
        } else if (input.startsWith("event")) {
            int fromIndex = input.indexOf(" /from");
            int toIndex = input.indexOf(" /to");
            if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex &&
                    fromIndex >= 6 && toIndex >= fromIndex + 7 && input.length() >= toIndex + 5) {
                String description = input.substring(6, fromIndex).trim();
                String startTime = input.substring(fromIndex + 7, toIndex).trim();
                String endTime = input.substring(toIndex + 5).trim();
                if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                    return "The description, start time, and end time of an event cannot be empty.";
                } else {
                    try {
                        return handleAdd(new Event(description, startTime, endTime));
                    } catch (DukeException e) {
                        return e.getMessage();
                    }
                }
            } else {
                return "That's not a valid Event!";
            }
        } else if (input.startsWith("find ")){
            String keyword = input.substring(5).trim();
            return handleFind(keyword);
        } else {
            return "That's not a valid task!\n";
        }
    }

    private String handleFind(String keyword) {
        ArrayList<Task> matchingTasks = tasks.find(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }

    private String handleExit() {
        return ui.showGoodbye();
        //System.exit(0);
    }

    private String handleList() {
        return ui.showTasks(tasks);
    }

    private String handleMark(String input) throws DukeException {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.getTask(taskIndex);
            task.markAsDone();
            storage.save(tasks);
            return ui.showTask("Nice! I've marked this task as done: " + task);
        } else {
            throw new DukeException("Task does not exist.");
        }
    }

    private String handleUnmark(String input) throws DukeException {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.getTask(taskIndex);
            task.markAsNotDone();
            storage.save(tasks);
            return ui.showTask("OK, I've marked this task as not done yet: " + task);
        } else {
            throw new DukeException("Task does not exist.");
        }
    }

    private String handleAdd(Task task) throws DukeException {
        try {
            tasks.addTask(task);
            storage.save(tasks);
            return ui.showAddedTask(task.toString(), tasks.size());
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
    private String handleDelete(String input) {
        try {
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            Task task = tasks.deleteTask(index);
            storage.save(tasks);
            return ui.showRemovedTask(task.toString(), tasks.size());
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Task does not exist.");
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
