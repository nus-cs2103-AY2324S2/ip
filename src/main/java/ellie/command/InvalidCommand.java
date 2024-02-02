package ellie.command;

import ellie.Parser;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final InvalidCommand other = (InvalidCommand) obj;
        if (this.errorMessage == null) {
            return other.errorMessage != null;
        }

        if (this.errorMessage != other.errorMessage) {
            return false;
        }

        return true;
    }

}
