package micromanager.commands;

import micromanager.exceptions.DukeException;
import micromanager.storage.Storage;
import micromanager.storage.TaskList;
import micromanager.tasks.Task;

/**
 * MarkCommand class represents a command to mark or unmark a task as done.
 * It extends the Command class and provides methods to execute the command.
 */
public class MarkCommand extends Command {
    private int index;
    private String command;

    /**
     * Constructs a MarkCommand object with the specified index and command.
     *
     * @param index   The index of the task to mark or unmark.
     * @param command The command to execute (mark or unmark).
     */
    public MarkCommand(int index, String command) {
        super();
        this.index = index;
        this.command = command;
    }

    /**
     * Executes the mark or unmark command by marking or unmarking the specified task as done.
     *
     * @param taskList The list of tasks.
     * @param storage  The storage handler (not used in this command).
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException(String.format("OOPS!!! There are no tasks to %s.", command));
        }
        Task target = taskList.get(index);
        if (command.equals("mark")) {
            target.markDone();
            return String.format("Nice! I've %sed this task as done:%n\n", command) + "  " + target;
        } else {
            target.unmarkDone();
            return String.format("OK, I've %sed this task as done:%n\n", command) + "  " + target;
        }

    }
}
