package command;

import task.Todo;

public class DeleteCmd extends Command {
    public int index;
    @Override
    public void execute() {
        tasks.delete(index);

    }

    public DeleteCmd(int i) {
        index = i;
    }
}
