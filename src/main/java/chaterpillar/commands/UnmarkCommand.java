package chaterpillar.commands;

import java.io.IOException;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws ChaterpillarException, IOException {
        if (index > tasks.size()) {
            throw new ChaterpillarException(
                    "Sorry! That item does not exist in the list.\n"
                    + "You currently have " + tasks.size() + " tasks in the list.");
        } else {
            Task currTask = tasks.get(index - 1);
            currTask.unmark();

            ui.echo("Ok, I've marked this task as not done yet:");
            ui.echo(currTask.toString());

            storage.saveAllToFile(tasks);
        }
    }
}
