package Commands;

import Misc.StorageManager;
import Misc.Ui;

import Irwyn.Tasks.TaskList;

public class MarkCommand extends Command {
    private final int mark;

    MarkCommand (String input) {
        super(false);
        mark = Integer.parseInt(input.split(" ")[1]) - 1;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        taskList.mark(mark);
        storageManager.save(taskList.getTasks());
        String reply = "Nice! I've marked this task as done:\n" + taskList.getTask(mark).toString() + "\n";
        ui.reply(reply);
    }
}