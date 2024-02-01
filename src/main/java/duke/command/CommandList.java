package duke.command;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.Ui;

public class CommandList extends Command {

    public CommandList(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(String description) {
        ArrayList<Task> list = taskList.getList();
        ui.add("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            ui.add(String.format("%d. %s",
                    i + 1,
                    list.get(i)));
        }
        ui.print();
    }
    
}
