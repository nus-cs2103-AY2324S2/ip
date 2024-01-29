package cappy.command;

import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input)
            throws CappyException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new CappyException("Please enter an index.");
        }
        String indexStr = input.getPositionalArgument(0);
        try {
            int index = Integer.parseInt(indexStr);
            if (!tasks.validIndex(index)) {
                throw new CappyException("Please enter a valid index.");
            }
            tasks.getTask(index).undone();
            String[] messages = {
                "OK, I've marked this task as not done yet:", tasks.getTask(index).toString()
            };
            ui.show(messages);
            tasks.save();
        } catch (NumberFormatException e) {
            throw new CappyException("Please enter a valid index.");
        }
    }
}
