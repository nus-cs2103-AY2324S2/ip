package cappy.command;

import java.io.IOException;

import cappy.task.Task;
import cappy.task.TaskList;
import cappy.ui.Ui;
import cappy.storage.Storage;
import cappy.parser.ParsedInput;
import cappy.error.CappyException;

public class FindCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws CappyException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new CappyException("Please enter a keyword.");
        }
        String keyword = input.getPositionalArgument(0);
        TaskList results = tasks.search(keyword);
        if (results.size() == 0) {
            ui.show("No matches found :(");
        } else {
            String[] messages = new String[results.size() + 1];
            messages[0] = " Here are the matching tasks in your list:";
            System.arraycopy(results.toString().split("\n"), 0, messages, 1, results.size());
            ui.show(messages);
        }
    }
}


