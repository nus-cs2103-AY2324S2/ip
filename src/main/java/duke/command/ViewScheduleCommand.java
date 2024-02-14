package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Task;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

public class ViewScheduleCommand extends Command {
    private LocalDate date;

    public ViewScheduleCommand(Parser.Cmd type, LocalDate date) {
        super(type);
        this.date = date;
    }

    /**
     * Marks/Unmarks the task on the index field in the given list.
     *
     * @param taskList the given taskList to mark/unmark the task.
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
        ArrayList<Task> tasks = taskList.searchDate(this.date);
        ui.displaySelectedList(tasks);
    }
}
