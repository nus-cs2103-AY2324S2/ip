package commands;

import java.io.IOException;

import exceptions.ChaterpillarException;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import storage.Storage;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException, IOException {
        if (index > tasks.size()) {
            throw new ChaterpillarException(
                    "Sorry! That item does not exist in the list.\n" +
                    "You currently have " + tasks.size() + " tasks in the list.");
        } else {
            ui.echo("Nice! I've marked this task as done:");
            Task currTask = tasks.get(index-1);
            currTask.mark();
            ui.echo(currTask.toString());
            storage.saveAllToFile(tasks);
        }
    }
}
