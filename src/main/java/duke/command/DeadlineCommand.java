package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String description;

    private LocalDate by;

    public DeadlineCommand(String input, String description, LocalDate by) {
        super(input);
        this.description = description;
        this.by = by;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        int counter = tasks.getCounter();

        Task t = new Deadline(description, by);
        tasks.addTask(t);

        return ui.showAddTaskMessage(t, counter);
    }
}
