package commands;

import java.io.IOException;
import java.util.ArrayList;
import tasks.Task;
import ui.Ui;

public class ViewTaskListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        Ui.printList(tasks);
    }
}
