package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeCeption;
import duke.tasks.Task;

public class CommandMark extends Command {

    public CommandMark(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(String taskNumber) {
        try {
            Task task = taskList.getTask(taskNumber);
            task.markAsDone();
            ui.add("Great! I will mark this as done:");
            ui.add(task.toString());
            ui.print();
        } catch (DukeCeption e) {
            ui.print(e.getMessage());
        }

    }
}
