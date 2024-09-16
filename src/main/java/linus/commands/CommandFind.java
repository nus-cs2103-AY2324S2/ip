package linus.commands;

import java.util.ArrayList;

import linus.Ui;
import linus.tasks.Task;
import linus.tasks.TaskList;

public class CommandFind extends Command {

    public CommandFind(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(String find) {
        ArrayList<Task> listOfTask = taskList.getList();
        int counter = 1;
        ui.beginTofindMatchingTasks();
        
        for (Task currTask : listOfTask) {
            if (currTask.isMatchingDescription(find)) {
                ui.printTaskInListWithIndex(currTask.toString(), counter);
                counter += 1;
            }
        }
    }
    
}
