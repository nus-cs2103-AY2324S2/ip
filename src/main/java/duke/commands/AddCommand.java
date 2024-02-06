package duke.commands;

import duke.tasks.TaskList;

public abstract class AddCommand extends Command {

    private TaskList taskList;

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
