package cappy.command;

import java.io.IOException;

import cappy.task.Task;
import cappy.task.TaskList;
import cappy.ui.Ui;
import cappy.storage.Storage;
import cappy.parser.ParsedInput;
import cappy.error.CappyException;

public class DeleteCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws CappyException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new CappyException("Please enter an index.");
        }
        String indexStr = input.getPositionalArgument(0);
        try {
            int index = Integer.parseInt(indexStr);
            if (!tasks.validIndex(index)) {
                throw new CappyException("Please enter a valid index.");
            }
            Task task = tasks.getTask(index);
            tasks.removeTask(index);
            String[] messages = {
                "Noted. I've removed this task:", task.toString(),
                "Now you have " + tasks.size() + " tasks in the list."
            };
            ui.show(messages);
            tasks.save();
        } catch (NumberFormatException e) {
            throw new CappyException("Please enter a valid index.");
        }
    }
}

