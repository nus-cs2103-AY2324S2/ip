package ellie.command;

import ellie.TaskList;

public class ExitCommand extends Command {
    
    public ExitCommand() {
        super.isExit = true;
    }

    public void run(TaskList tasklist) {

    }
    
}
