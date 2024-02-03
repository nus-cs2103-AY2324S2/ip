package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE = "ok i help you delete this task ah:\n%s";
    public static final String ERROR_MESSAGE = "oi the argument must be a number la";

    private final String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(this.index);

            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            ui.print(String.format(MESSAGE, task));
        } catch (NumberFormatException e) {
            ui.print(ERROR_MESSAGE);
        }
    }
}
