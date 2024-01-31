package osiris.commands;

import osiris.task.Task;
import osiris.ui.Ui;
import osiris.task.TaskManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Command class representing the "list" command to print user tasks.
 */
public class printUserTasksCommand extends Command{

    public static final String COMMAND = "list";

    /**
     * Executes the "list" command to print user tasks.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return Always returns true.
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        ArrayList<Task> taskList = taskManager.getUserTasks();
        ArrayList<String> taskDetailsArrayList = taskList.stream().map(task -> task.toString()).collect(Collectors.toCollection(ArrayList::new));
        userInterface.printUserTasks(taskDetailsArrayList);
        return true;
    }
}
