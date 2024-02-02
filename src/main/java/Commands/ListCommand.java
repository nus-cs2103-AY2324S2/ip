package Commands;

import Exceptions.LeluException;
import Tasks.Task;
import TasksStorage.Storage;
import TasksStorage.TaskList;
import UI.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        System.out.println(tasks.toString());
    }
}
