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
        ui.add("These tasks matches your search:");
        for (Task currTask : listOfTask) {
            if (currTask.isMatchingDescription(find)) {
                ui.add(String.format("%d. %s",
                        counter,
                        currTask));
                counter += 1;
            }
        }
        ui.print();
    }
    
}
