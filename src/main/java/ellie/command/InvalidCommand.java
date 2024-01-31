package ellie.command;

import ellie.TaskList;

public class InvalidCommand extends Command {

    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void run(TaskList tasklist) {
        System.out.println(errorMessage);
        return;
    }

}
