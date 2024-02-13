package osiris.commands;

import java.util.ArrayList;
import java.util.stream.Collectors;

import osiris.task.Task;
import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing the "list" command to print user tasks.
 */
public class PrintUserTasksCommand extends Command {

    /** Keyword used to identify the command. */
    public static final String COMMAND = "list";

    /**
     * Executes the "list" command to print user tasks.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return String notification if the command is executed successfully.
     */
    @Override
    public String execute(TaskManager taskManager, Ui userInterface) {
        ArrayList<Task> userTasksList = taskManager.getUserTasks();
        ArrayList<String> taskDetailsArrayList = userTasksList.stream().map(Task::toString)
                .collect(Collectors.toCollection(ArrayList::new));
        return userInterface.printUserTasks(taskDetailsArrayList);
    }
}
