package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;

public class TodoCommand extends Command{

    private final String todo;

    public TodoCommand(String todo) {
        this.todo = todo;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(this.todo);
        tasks.add(task);
        return tasks.standardize(task);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TodoCommand otherCommand)) {
            return false;
        }
        return this.todo.equals(otherCommand.todo);
    }
}
