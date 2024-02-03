package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidArgumentException;
import duke.tasks.Task;
import duke.tasks.TodoTask;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE = "added this task for you liao:\n%s";

    private final String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = new TodoTask(name);
            tasks.add(task);
            ui.print(String.format(MESSAGE, task));
        } catch (InvalidArgumentException e) {
            ui.print(e.getMessage());
        }
    }
}
