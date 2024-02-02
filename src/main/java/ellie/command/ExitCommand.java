package ellie.command;

import ellie.Parser;
import ellie.TaskList;

public class ExitCommand extends Command {
    
    public ExitCommand() {
        super.isExit = true;
    }

    public void run(TaskList tasklist) {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        // If the object is compared with itself, or is instance of ExitCommand
        // then return true
        if (o instanceof ExitCommand) {
            return true;
        } else {
            return false;
        }
    }

    
}
