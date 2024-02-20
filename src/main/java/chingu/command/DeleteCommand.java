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

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert !(index < 0 || index >= tasks.getSize()) : "this task doesn't exist!";
        System.out.println("HERE");
        Task removed = tasks.tasks.remove(index);
        String Response = "Noted. I've removed this task:\n";
        String length = "" + tasks.getSize();
        Response = Response + removed.toString() + "\n" +
                "Now you have " + length + " tasks in the list.\n";
        return Response;
    }

    public boolean isExit() {
        return false;
    }
}
