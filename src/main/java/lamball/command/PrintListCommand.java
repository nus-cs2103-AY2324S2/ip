package lamball.command;

import lamball.TaskList;

/**
 * Command that prints the list of tasks
 */
public class PrintListCommand extends Command {
    private TaskList taskList;

    /**
     * Constructor for command.
     *
     * @param tasks TaskList to print from.
     */
    public PrintListCommand(TaskList tasks) {
        this.taskList = tasks;
    }

    @Override public boolean run() {
        taskList.printList();
        return true;
    }
}
