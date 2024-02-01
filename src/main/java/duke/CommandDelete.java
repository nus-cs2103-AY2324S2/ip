package duke;

import duke.tasks.Task;

public class CommandDelete extends Command {

    public CommandDelete(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(String taskNumber) {
        try {
            Task task = taskList.delete(taskNumber);
            task.markAsNotDone();
            ui.add("Alright! this task is now unmarked:");
            ui.add(task.toString());
            ui.print();
        } catch (DukeCeption e) {
            ui.print(e.getMessage());
        }
    }
}
