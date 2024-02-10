import java.util.ArrayList;

public interface Command {
    void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws WhisperException;
    boolean isExit();
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
            tasks.add(taskToAdd);
            ui.printTaskAdded(taskToAdd, tasks.size());
            storage.saveFile(tasks);
        } catch (WhisperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
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
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            ui.printRemovedTask(tasks.get(taskIndex), tasks.size());
            tasks.remove(taskIndex);
            storage.saveFile(tasks);
        } catch (WhisperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
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
}

// Mark Command
class MarkCommand implements Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws WhisperException {
        tasks.get(taskIndex).markAsDone();
        ui.printTaskAsDone(tasks.get(taskIndex));
        storage.saveFile(tasks);
    }

    @Override
    public boolean isExit() {
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
        tasks.get(taskIndex).markAsUndone();
        ui.printTaskAsUndone(tasks.get(taskIndex));
        storage.saveFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}