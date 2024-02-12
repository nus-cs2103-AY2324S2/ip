package commands;

import exceptions.InvalidTaskException;
import exceptions.StorageException;
import mainUtils.Parser;
import mainUtils.Storage;
import mainUtils.Ui;
import tasks.TaskList;


public class CreateTodoTask extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, StorageException {
        taskList.addTask(Parser.parseTodoTask(ui.getCommand()));
    }

}
