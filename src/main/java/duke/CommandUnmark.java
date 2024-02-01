package duke;

import duke.tasks.Task;

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
            ui.print();
        } catch (DukeCeption e) {
            ui.print(e.getMessage());
        }
    }
}
