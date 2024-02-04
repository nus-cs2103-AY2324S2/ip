package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE = "good job bro, marked this task as done:\n%s";
    public static final String ERROR_MESSAGE = "oi the argument must be a number la";

    private final String index;

    public MarkCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(this.index);
            Task task = tasks.get(index - 1);
            task.markDone(true);
            ui.print(String.format(MESSAGE, tasks.get(index - 1)));
        } catch (NumberFormatException e) {
            ui.print(ERROR_MESSAGE);
        }
    }
}
