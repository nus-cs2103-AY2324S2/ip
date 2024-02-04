package Commands;

import Irwyn.Tasks.TaskList;
import Misc.StorageManager;
import Misc.Ui;
import Irwyn.Tasks.Task;


public class DeleteCommand extends Command {
    private final int delete;
    public DeleteCommand (String input) {
        super(false);
        delete = Integer.parseInt(input.split(" ")[1]) - 1;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        Task t = taskList.delete(delete);
        storageManager.save(taskList.getTasks());
        String reply = "Noted. I've removed this task:\n" +
                t.toString() + "\n" +
                "Now you have " + taskList.getTasksSize() + " tasks in the list.\n";
        ui.reply(reply);
    }
}