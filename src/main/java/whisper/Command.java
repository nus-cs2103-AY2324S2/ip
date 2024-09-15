package whisper;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The Command interface represents an executable command in the Whisper application.
 */
public interface Command {
    /**
     * Executes the command with the given parameters.
     *
     * @param tasks   The list of tasks on which the command operates.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage to save changes persistently.
     * @throws WhisperException If there is an error executing the command.
     */
    void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws WhisperException;

    /**
     * Indicates whether the command should exit the application.
     *
     * @return True if the command triggers an application exit, false otherwise.
     */
    boolean isExit();

    /**
     * Checks if the command matches a given keyword.
     *
     * @param keyword The keyword to check for a match.
     * @return True if the command matches the keyword, false otherwise.
     */
    boolean isKeywordMatch(String keyword);
}

/**
 * The AddCommand class represents a command to add a new task to the task list.
 */
class AddCommand implements Command {
    private Task taskToAdd;
    private Ui ui;

    /**
     * Constructs an AddCommand with the specified task to add.
     *
     * @param taskToAdd The task to be added.
     */
    public AddCommand(Task taskToAdd, Ui ui) {
        this.taskToAdd = taskToAdd;
        this.ui = ui;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            assert tasks != null : "Tasks list should not be null";
            assert ui != null : "UI should not be null";
            assert storage != null : "Storage should not be null";
            assert taskToAdd != null : "Task to add should not be null";

            // Add the task directly to the original tasks list
            tasks.add(taskToAdd);
            // Save the updated tasks list to the file
            storage.saveFile(tasks);
            ui.printTaskAdded(taskToAdd, tasks.size());
        } catch (WhisperException e) {
            ui.printError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isKeywordMatch(String keyword) {
        return false;
    }
}

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 */
class DeleteCommand implements Command {
    private int taskIndex;
    private Ui ui;

    /**
     * Constructs a DeleteCommand with the specified task index to delete.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex, Ui ui) {
        this.taskIndex = taskIndex;
        this.ui = ui;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws WhisperException {
        try {
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task removedTask = tasks.remove(taskIndex);
                ui.printRemovedTask(removedTask, tasks.size());
                storage.saveFile(tasks);
            } else {
                throw WhisperException.invalidTaskID();
            }
        } catch (WhisperException e) {
            throw new WhisperException("Invalid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isKeywordMatch(String keyword) {
        return false;
    }
}

/**
 * The ExitCommand class represents a command to exit the Whisper application.
 */
class ExitCommand implements Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean isKeywordMatch(String keyword) {
        return false;
    }
}

/**
 * The ListCommand class represents a command to list all tasks in the task list.
 */
class ListCommand implements Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isKeywordMatch(String keyword) {
        return false;
    }
}

/**
 * The MarkCommand class represents a command to mark a task as done in the task list.
 */
class MarkCommand implements Command {
    private int taskIndex;
    private Ui ui;

    /**
     * Constructs a MarkCommand with the specified task index to mark as done.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex, Ui ui) {
        this.taskIndex = taskIndex;
        this.ui = ui;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws WhisperException {
        try {
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsDone();
                ui.printTaskAsDone(tasks.get(taskIndex));
                storage.saveFile(tasks);
            } else {
                ui.printError("Invalid task number. Please choose between task 1 - " + tasks.size() + "\n");
            }
        } catch (WhisperException e) {
                throw WhisperException.invalidTaskID();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isKeywordMatch(String keyword) {
        return false;
    }
}

/**
 * The UnmarkCommand class represents a command to mark a task as not done in the task list.
 */
class UnmarkCommand implements Command {
    private int taskIndex;
    private Ui ui;

    /**
     * Constructs an UnmarkCommand with the specified task index to mark as not done.
     *
     * @param taskIndex The index of the task to be marked as not done.
     *
     */
    public UnmarkCommand(int taskIndex, Ui ui) {
        this.taskIndex = taskIndex;
        this.ui = ui;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws WhisperException {
        try {
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsUndone();
                ui.printTaskAsUndone(tasks.get(taskIndex));
                storage.saveFile(tasks);
            } else {
                ui.printError("Invalid task number. Please choose between task 1 - " + tasks.size() + "\n");
            }
        } catch (WhisperException e) {
            throw WhisperException.invalidTaskID();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isKeywordMatch(String keyword) {
        return false;
    }
}

/**
 * The FindCommand class represents a command to search for tasks by a keyword.
 */
class FindCommand implements Command {
    private String keyword;
    private Ui ui;

    /**
     * Creates a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword, Ui ui) {
        this.keyword = keyword;
        this.ui = ui;
    }

    /**
     * Executes the find command by searching for tasks containing the specified keyword.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        // Use Streams to filter tasks based on the keyword
        ArrayList<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        ui.printMatchingTasks(matchingTasks);
    }

    /**
     * Checks if the find command represents an exit action.
     *
     * @return Always returns false as the find command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if the find command matches a given keyword.
     *
     * @param keyword The keyword to check for a match.
     * @return True if the find command matches the keyword, false otherwise.
     */
    @Override
    public boolean isKeywordMatch(String keyword) {
        // check if the command matches the given keyword
        return this.keyword.equalsIgnoreCase(keyword);
    }
}

/**
 * The HelpCommand class represents a command to display a help message to the user.
 */
class HelpCommand implements Command {

    /**
     * Executes the help command by displaying a help message to the user.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The user interface to display the help message.
     * @param storage The storage handler (not used in this command).
     */

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        // Display the help message
        ui.printHelpMessage();
    }

    /**
     * Indicates whether the command should exit the application.
     *
     * @return Always returns false as the help command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if the command matches a given keyword.
     *
     * @param keyword The keyword to check for a match.
     * @return True if the command matches the keyword, false otherwise.
     */
    @Override
    public boolean isKeywordMatch(String keyword) {
        return keyword.equalsIgnoreCase("help");
    }
}
