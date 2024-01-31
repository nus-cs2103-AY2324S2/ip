package ellie.command;

import ellie.TaskList;

public class ListCommand extends Command {

    public void run(TaskList tasklist) {
        tasklist.listTasks();
    }

}
