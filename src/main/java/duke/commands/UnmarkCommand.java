package duke.commands;

import duke.tasks.Task;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE = "ok i help you unmark this task ah:\n%s";
    public static final String ERROR_MESSAGE = "oi the argument must be a number la";

    private final String index;

    public UnmarkCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(this.index);
            Task task = tasks.get(index - 1);
            task.markDone(false);
            ui.print(String.format(MESSAGE, tasks.get(index)));
        } catch (NumberFormatException e) {
            ui.print(ERROR_MESSAGE);
        }
    }
}
