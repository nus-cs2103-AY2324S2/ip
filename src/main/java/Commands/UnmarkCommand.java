package Commands;

import Irwyn.Tasks.TaskList;
import Misc.StorageManager;
import Misc.Ui;

public class UnmarkCommand extends Command {
    private final int unmark;

    UnmarkCommand (String input) {
        super(false);
        unmark = Integer.parseInt(input.split(" ")[1]) - 1;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        taskList.unmark(unmark);
        storageManager.save(taskList.getTasks());
        String reply = "OK, I've marked this task as not done yet:\n" + taskList.getTask(unmark).toString() + "\n";
        ui.reply(reply);
    }
}
