package command;

import task.Todo;

public class DeleteCmd extends Command {
    public int index;
    public void execute() {
        tasks.delete(index);

    }

    public DeleteCmd(int i) {
        index = i;
    }
}
