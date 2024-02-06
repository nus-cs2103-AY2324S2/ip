package Commands;

import Exceptions.LeluException;
import TasksStorage.Storage;
import TasksStorage.TaskList;
import Ui.Ui;

public class MarkCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        int i = Integer.parseInt(message.split(" ")[1]) - 1;
        tasks.markTask(i);
    }

}
