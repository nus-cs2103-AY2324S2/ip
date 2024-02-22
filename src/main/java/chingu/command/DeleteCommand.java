package chingu.command;

import chingu.Storage;
import chingu.task.Task;
import chingu.task.TaskList;
import chingu.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String n) {
        int index = Integer.parseInt(n) - 1;
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert !(index < 0 || index >= tasks.getSizeNumber()) : "this task doesn't exist!";
        Task removed = tasks.tasks.remove(index);
        String Response = "Noted. I've removed this task:\n";
        Response = Response + removed.toString() + "\n" +
                tasks.getSize();
        return Response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
