package cappy.command;

import java.io.IOException;
import cappy.task.Todo;
import cappy.task.Task;
import cappy.task.TaskList;
import cappy.ui.Ui;
import cappy.storage.Storage;
import cappy.parser.ParsedInput;
import cappy.error.CappyException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws CappyException, IOException {
        String[] messages = new String[tasks.size() + 1];
        messages[0] = "Here are the tasks in your list:";
        System.arraycopy(tasks.toString().split("\n"), 0, messages, 1, tasks.size());
        ui.show(messages);
    }
}
