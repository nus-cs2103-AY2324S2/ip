package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

public class CommandTodo extends Command {
    private Todo todo;

    public CommandTodo(Todo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.todo);

        storage.saveTasks(tasks);

        ui.showMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            this.todo, tasks.size()));
    }
}
