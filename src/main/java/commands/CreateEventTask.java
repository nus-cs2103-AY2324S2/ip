package commands;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskException;
import mainUtils.Parser;
import mainUtils.Storage;
import mainUtils.Ui;
import tasks.TaskList;

public class CreateEventTask extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException {
        taskList.addTask(Parser.parseEventTask(ui.getCommand()));
    }
}
