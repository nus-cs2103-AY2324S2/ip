package Commands;

import Misc.StorageManager;
import Misc.Ui;
import Irwyn.Tasks.Deadline;
import Irwyn.Tasks.TaskList;

import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private final String[] deadline;
    DeadlineCommand (String input) {
        super(false);
        deadline = input.replaceFirst("deadline ", "").split(" /by ");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        try {
            String description = this.deadline[0];
            String by = this.deadline[1];
            Deadline deadline = new Deadline(description, by);
            taskList.addTask(deadline);
            storageManager.save(taskList.getTasks());
            ui.reply(deadline.replyString(taskList.getTasksSize()));
        } catch (DateTimeParseException e) {
            ui.reply("Invalid date format. Please use YYYY-MM-DD or YYYY-MM-DD HH:MM:SS format.\n");
        }
    }
}
