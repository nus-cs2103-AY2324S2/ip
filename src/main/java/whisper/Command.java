package whisper;
import java.util.ArrayList;

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
}

/**
 * The AddCommand class represents a command to add a new task to the task list.
 */
class AddCommand implements Command {
    private Task taskToAdd;

    /**
     * Constructs an AddCommand with the specified task to add.
     *
     * @param taskToAdd The task to be added.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            // Add the task directly to the original tasks list
            tasks.add(taskToAdd);
            // Save the updated tasks list to the file
            storage.saveFile(tasks);
            ui.printTaskAdded(taskToAdd, tasks.size());
        } catch (WhisperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 */
class DeleteCommand implements Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index to delete.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
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
}

/**
 * The ExitCommand class represents a command to exit the Whisper application.
 */
class ExitCommand implements Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
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
}

/**
 * The MarkCommand class represents a command to mark a task as done in the task list.
 */
class MarkCommand implements Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index to mark as done.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
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
}

/**
 * The UnmarkCommand class represents a command to mark a task as not done in the task list.
 */
class UnmarkCommand implements Command {
    private int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index to mark as not done.
     *
     * @param taskIndex The index of the task to be marked as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
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
}