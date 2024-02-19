package hal.command;

import hal.task.TaskList;

public class UnknownCommand extends Command {

    public UnknownCommand() {
    }

    @Override
    public String execute(TaskList taskList) {
        return "Unknown command. What are you doing Dave.";
    }
}
