package duke.commands;

import java.io.IOException;
import java.util.ArrayList;
import duke.tasks.Task;
import duke.ui.Ui;

public class ViewTaskListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        Ui.printList(tasks);
    }
}
