package hal.command;

import hal.task.TaskList;

public class ByeCommand extends Command {

    public ByeCommand() {}

    public String execute(TaskList taskList) {
        return "This mission is too important for me to allow you to jeopardize it. Goodbye.";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
