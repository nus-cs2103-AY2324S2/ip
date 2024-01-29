package Commands;

import Task.Task;
import UI.Ui;
import Task.TaskManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class printUserTasksCommand extends Command{

    public static final String COMMAND = "list";

    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        ArrayList<Task> taskList = taskManager.getUserTasks();
        ArrayList<String> taskDetailsArrayList = taskList.stream().map(task -> task.toString()).collect(Collectors.toCollection(ArrayList::new));
        userInterface.printUserTasks(taskDetailsArrayList);
        return true;
    }
}
