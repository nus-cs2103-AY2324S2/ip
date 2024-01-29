package cappy.command;

import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.task.Todo;
import cappy.ui.Ui;

import java.io.IOException;

public class TodoCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input)
            throws CappyException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new CappyException("Please enter the task description.");
        }
        String description = input.getPositionalArgument(0);
        Todo task = new Todo(description);
        tasks.addTask(task);
        ui.showAddedTask(task, tasks);
        tasks.save();
    }
}
