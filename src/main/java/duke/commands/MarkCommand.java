package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private int index;
    private String command;

    public MarkCommand(int index, String command) {
        super();
        this.index = index;
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException(String.format("OOPS!!! There are no tasks to %s.", this.command));
        }
        Task target = taskList.get(this.index);
        if (this.command.equals("mark")) {
            target.markDone();
        } else {
            target.unmarkDone();
        }
        System.out.printf("Nice! I've %sed this task as done:%n", this.command);
        System.out.println("  " + target);
    }
}
