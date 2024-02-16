package whisper;
import java.util.ArrayList;

public interface Command {
    void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws WhisperException;
    boolean isExit();

    /**
     * Checks if the command matches a given keyword.
     *
     * @param keyword The keyword to check for a match.
     * @return True if the command matches the keyword, false otherwise.
     */
    boolean isKeywordMatch(String keyword);
}

// Add Command
class AddCommand implements Command {
    private Task taskToAdd;

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

    @Override
    public boolean isKeywordMatch(String keyword) {
        return false;
    }
}

// Delete Command
class DeleteCommand implements Command {
    private int taskIndex;

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

    @Override
    public boolean isKeywordMatch(String keyword) {
        return false;
    }
}

// Exit Command
class ExitCommand implements Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
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

// List Command
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

// Mark Command
class MarkCommand implements Command {
    private int taskIndex;

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

    @Override
    public boolean isKeywordMatch(String keyword) {
        return false;
    }
}

// Unmark command
class UnmarkCommand implements Command {
    private int taskIndex;

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

    /**
     * Creates a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
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
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
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