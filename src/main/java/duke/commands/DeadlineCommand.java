package duke.commands;

import java.time.DateTimeException;

import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidArgumentException;
import duke.tasks.DeadlineTask;
import duke.tasks.Task;


public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE = "added this task for you liao:\n%s";


    private final String name;
    private final String deadline;

    public DeadlineCommand(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = new DeadlineTask(name, deadline);
            tasks.add(task);
            ui.print(String.format(MESSAGE, task));
        } catch (InvalidArgumentException | DateTimeException e) {
            ui.print(e.getMessage());
        }
    }
}
