package command;

import task.Task;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

import exceptions.InvalidStatusUpdateException;

public class ManageCommand extends Command {
    private Command.Types type;
    private int index;

    public ManageCommand(Command.Types type, int index) {
        this.type = type;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        switch (this.type) {
            case MARK:
                mark(tasks, storage);
                break;
            case UNMARK:
                unmark(tasks, storage);
                break;
            case DELETE:
                delete(tasks, storage);
                break;
        }
    }

    @Override
    public String getTestData() {
        return "";
    }

    public void mark(TaskList tasks, Storage storage) {
        if (index >= tasks.getSize() || index < 0) {
            UI.print("Oops! You did not give a valid index.");
            return;
        }
        Task task = tasks.getTask(this.index);
        try {
            task.updateStatus(true);
            UI.print("Nice! I've marked this task as done:");
            UI.print(task);
            String line = storage.readLine(index);
            String newLine = line.substring(0, line.length() - 5) + "true";
            storage.updateLine(index, newLine);
            storage.updateLine(index, newLine);
        } catch (InvalidStatusUpdateException e) {
            UI.print("This task was already marked!");
            UI.print(task);
        }
    }

    public void unmark(TaskList tasks, Storage storage) {
        if (index >= tasks.getSize() || index < 0) {
            UI.print("Oops! You did not give a valid index.");
            return;
        }
        Task task = tasks.getTask(index);
        try {
            task.updateStatus(false);
            UI.print("OK, I've marked this task as not done yet:");
            UI.print(task);
            String line = storage.readLine(index);
            String newLine = line.substring(0, line.length() - 4) + "false";
            storage.updateLine(index, newLine);
        } catch (InvalidStatusUpdateException e) {
            UI.print("This task was already unmarked!");
            UI.print(task);
        }
    }

    public void delete(TaskList tasks, Storage storage) {
        if (index >= tasks.getSize() || index < 0) {
            UI.print("Oops! You did not give a valid index.");
            return;
        }
        Task removed = tasks.deleteTask(index);
        storage.deleteLine(index);

        UI.print("Noted. I've removed this task:");
        UI.print("\t" + removed);
        UI.print(String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }
}
