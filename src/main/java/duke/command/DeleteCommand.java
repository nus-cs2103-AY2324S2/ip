package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String n) {
        int index = Integer.parseInt(n) - 1;
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

        Task removed = tasks.tasks.remove(index);
        String length = "" + tasks.getSize();
        System.out.println("Noted. I've removed this task:");
        System.out.println(removed.toString());
        System.out.println("Now you have " + length + " tasks in the list.");

    }

    public boolean isExit() {
        return false;
    }
}
