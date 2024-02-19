package command;

import exceptions.InvalidStatusUpdateException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.UI;

/**
 * The ManageCommand class represents a command to manage tasks, such as marking, unmarking, or deleting.
 * It extends the Command class and implements the execute method to perform the specified manage action.
 */
public class ManageCommand extends Command {

    /**
     * The type of manage command (MARK, UNMARK, DELETE).
     */
    private Command.Types type;

    /**
     * The index of the task to be managed.
     */
    private int[] indices;

    /**
     * Constructs a ManageCommand with the specified command type and index.
     *
     * @param type  The type of manage command (MARK, UNMARK, DELETE).
     * @param indices The index of the task to be managed.
     */
    @SafeVarargs
    public ManageCommand(Command.Types type, int... indices) {
        this.type = type;
        this.indices = indices;
    }

    /**
     * Executes the ManageCommand, performing the specified manage action on the given task list and storage.
     *
     * @param tasks   The TaskList on which the manage action is performed.
     * @param storage The Storage where changes are saved.
     * @return A string containing the result message for this operation.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        switch (this.type) {
        case MARK:
            return mark(tasks, storage);
        case UNMARK:
            return unmark(tasks, storage);
        case DELETE:
            return delete(tasks, storage);
        default:
            return "";
        }
    }

    /**
     * Retrieves an empty string as test data associated with ManageCommand.
     *
     * @return An empty string used for testing.
     */
    @Override
    public String getTestData() {
        return "";
    }

    /**
     * Marks the task at the specified index as done, updating its status in the task list and storage.
     *
     * @param tasks   The TaskList from which the task is marked.
     * @param storage The Storage where changes are saved.
     * @return A string containing the result message for this operation.
     */
    public String mark(TaskList tasks, Storage storage) {
        String result = "";
        for (int index : this.indices) {
            assert index >= tasks.getSize() || index < 0 : "Provided index is invalid";
            if (index >= tasks.getSize() || index < 0) {
                UI.print(String.format("Naughty boy... %d is not a valid index.", index + 1));
                result += String.format("Naughty boy... %d is not a valid index.\n", index + 1);
                return result;
            }
            Task task = tasks.getTask(index);
            try {
                task.updateStatus(true);
                UI.print("Nice! I've marked this task as done:");
                UI.print(task);

                result += "Nice! I've marked this task as done:\n";
                result += task + "\n";

                String line = storage.readLine(index);
                String newLine = line.substring(0, line.length() - 5) + "true";
                storage.updateLine(index, newLine);
                storage.updateLine(index, newLine);
            } catch (InvalidStatusUpdateException e) {
                UI.print("Naughty boy... This task was already marked!");
                UI.print(task);

                result += "Naughty boy... This task was already marked!\n";
                result += task + "\n";
            }
        }
        return result;
    }

    /**
     * Unmarks the task at the specified index, updating its status in the task list and storage.
     *
     * @param tasks   The TaskList from which the task is unmarked.
     * @param storage The Storage where changes are saved.
     * @return A string containing the result message for this operation.
     */
    public String unmark(TaskList tasks, Storage storage) {
        String result = "";
        for (int index : this.indices) {
            assert index >= tasks.getSize() || index < 0 : "Provided index is invalid";
            if (index >= tasks.getSize() || index < 0) {
                UI.print(String.format("Naughty boy... %d is not a valid index.", index + 1));
                result += String.format("Naughty boy... %d is not a valid index.\n", index + 1);
                return result;
            }
            Task task = tasks.getTask(index);
            try {
                task.updateStatus(false);
                UI.print("OK, I've marked this task as not done yet:");
                UI.print(task);

                result += "OK, I've marked this task as not done yet:\n";
                result += task + "\n";

                String line = storage.readLine(index);
                String newLine = line.substring(0, line.length() - 4) + "false";
                storage.updateLine(index, newLine);
            } catch (InvalidStatusUpdateException e) {
                UI.print("Naughty boy... This task was already unmarked!");
                UI.print(task);

                result += "Naughty boy... This task was already unmarked!\n";
                result += task + "\n";
            }
        }
        return result;
    }

    /**
     * Deletes the task at the specified index, removing it from the task list and storage.
     *
     * @param tasks   The TaskList from which the task is deleted.
     * @param storage The Storage where changes are saved.
     * @return A string containing the result message for this operation.
     */
    public String delete(TaskList tasks, Storage storage) {
        String result = "";
        for (int index : this.indices) {
            assert index >= tasks.getSize() || index < 0 : "Provided index is invalid";
            if (index >= tasks.getSize() || index < 0) {
                UI.print(String.format("Naughty boy... %d is not a valid index.", index + 1));
                result += String.format("Naughty boy... %d is not a valid index.\n", index + 1);
                return result;
            }
            Task removed = tasks.deleteTask(index);
            storage.deleteLine(index);

            UI.print("Noted. I've removed this task:");
            UI.print("\t" + removed);

            result += "Noted. I've removed this task:\n";
            result += removed + "\n";
        }
        UI.print(String.format("Now you have %d tasks in the list.", tasks.getSize()));
        result += String.format("Now you have %d tasks in the list.", tasks.getSize());
        return result;
    }
}
