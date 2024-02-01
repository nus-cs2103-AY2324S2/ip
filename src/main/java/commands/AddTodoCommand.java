package commands;

import tasks.Task;

public class AddTodoCommand extends AddCommand{
    public AddTodoCommand(Task task) {
        super(task);
    }
}
