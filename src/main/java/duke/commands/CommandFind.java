package duke.commands;

import java.util.ArrayList;

import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

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
