package duke.commands;

import duke.tasks.TaskList;

public abstract class AddCommand extends Command {

    private final TaskList taskList;

    public AddCommand(TaskList tasklist) {
        this.taskList = tasklist;
    }

    public static String getUsage() {
        return Command.getUsage() + " add <type> ...";
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

}
