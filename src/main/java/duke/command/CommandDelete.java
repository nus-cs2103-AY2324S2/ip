package duke.command;

import duke.tasks.Task;
import duke.Ui;
import duke.TaskList;
import duke.DukeCeption;

public class CommandDelete extends Command {

    public CommandDelete(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(String taskNumber) {
        try {
            Task task = taskList.delete(taskNumber);
            ui.add("Okay! this task is now removed:");
            ui.add(task.toString());
        } catch (DukeCeption e) {
            ui.add(e.getMessage());
        } finally {
            ui.print();
        }
    }
}
