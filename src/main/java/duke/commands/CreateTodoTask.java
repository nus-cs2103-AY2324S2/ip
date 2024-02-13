package duke.commands;

import duke.exceptions.InvalidTaskException;
import duke.exceptions.StorageException;
import duke.mainUtils.Parser;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;


public class CreateTodoTask extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, StorageException {
        taskList.addTask(Parser.parseTodoTask(ui.getCommand()));
    }

}
