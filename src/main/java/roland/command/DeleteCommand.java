package roland.command;

import roland.Storage;
import roland.task.Task;
import roland.TaskList;
import roland.Ui;

public class DeleteCommand extends Command {

    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index - 1);
        tasks.remove(index-1);
        System.out.println(ui.getBot() + "I have removed " + task.toString() + " from your list. You have " + tasks.size() + " task(s) in list");
    }

}
