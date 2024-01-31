package osiris.commands;

import java.util.ArrayList;
import java.util.stream.Collectors;

import osiris.task.Task;
import osiris.task.TaskManager;
import osiris.ui.Ui;


public class printUserTasksCommand extends Command {

    public static final String COMMAND = "list";

    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        ArrayList<Task> taskList = taskManager.getUserTasks();
        ArrayList<String> taskDetailsArrayList = taskList.stream().map(task -> task.toString())
                .collect(Collectors.toCollection(ArrayList::new));
        userInterface.printUserTasks(taskDetailsArrayList);
        return true;
    }
}
