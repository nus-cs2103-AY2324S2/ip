package meanduke.commands;

import meanduke.tasks.TaskList;

/**
 * This class represents a Command that adds a Task to a TaskList
 */
public abstract class AddCommand extends Command {

    private final TaskList taskList;

    public AddCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    public static String getUsage() {
        return Command.getUsage() + " add <type> ...";
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

}
