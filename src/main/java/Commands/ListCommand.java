package Commands;

import Exceptions.LeluException;
import Tasks.Task;
import TasksStorage.Storage;
import UI.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage, String message) throws LeluException {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d.%s\n", i + 1, tasks.get(i).toString());
        }
        System.out.println();
    }
}
