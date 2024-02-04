package Commands;

import Irwyn.Tasks.TaskList;
import Misc.StorageManager;
import Misc.Ui;
import Irwyn.Tasks.Task;
import java.util.ArrayList;

public class ListCommand extends Command {
    ListCommand () {
        super(false);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        String reply = "Here are the tasks in your list:\n";
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            String num = i + 1 + ". ";
            Task task = tasks.get(i);
            reply += num + task + "\n";
        }

        ui.reply(reply);
    }
}