package ellie.command;

import ellie.TaskList;

public class ListCommand extends Command {

    public void run(TaskList tasklist) {
        tasklist.listTasks();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        // If the object is compared with itself, or is instance of this,
        // then return true
        if (o instanceof ListCommand) {
            return true;
        } else {
            return false;
        }
    }

}
