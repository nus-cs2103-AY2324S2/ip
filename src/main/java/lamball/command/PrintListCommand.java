package lamball.command;

import lamball.TaskList;

public class PrintListCommand extends Command {
    public PrintListCommand(TaskList tasks) {
        super(tasks);
    }

    @Override public boolean run() {
        taskList.printList();
        return true;
    }
}
