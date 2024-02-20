package linus.commands;

import java.util.ArrayList;

import linus.Ui;
import linus.tasks.Task;
import linus.tasks.TaskList;

/**
 * The command class when user wants to see tasks in the list
 */
public class CommandList extends Command {

    public CommandList(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(String description) {
        ArrayList<Task> list = taskList.getList();
        ui.beginToPrintTaskList();
        for (int i = 0; i < list.size(); i++) {
            ui.printTaskInListWithIndex(list.get(i).toString(), i+1);
        }
    }
    
}
