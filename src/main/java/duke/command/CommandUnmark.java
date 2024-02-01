package duke.command;

import duke.Ui;
import duke.tasks.Task;
import duke.TaskList;
import duke.DukeCeption;

public class CommandUnmark extends Command {

    public CommandUnmark(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(String taskNumber) {
        try {
            Task task = taskList.getTask(taskNumber);
            task.markAsNotDone();
            ui.add("Alright! this task is now unmarked:");
            ui.add(task.toString());
        } catch (DukeCeption e) {
            ui.add(e.getMessage());
        } finally {
            ui.print();
        }

    }
}
