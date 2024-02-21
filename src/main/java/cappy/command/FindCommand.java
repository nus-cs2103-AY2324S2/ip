package cappy.command;

import static cappy.constant.Message.MISSING_KEYWORD;
import static cappy.constant.Message.NO_MATCHES_FOUND;

import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.ui.Ui;

import java.io.IOException;

/** Represents a command to find tasks in the task list. */
public class FindCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input)
            throws CappyException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new CappyException(MISSING_KEYWORD);
        }
        String keyword = input.getPositionalArgument(0);
        TaskList results = tasks.search(keyword);
        if (results.size() == 0) {
            ui.show(NO_MATCHES_FOUND);
        } else {
            String[] messages = new String[results.size() + 1];
            messages[0] = " Here are the matching tasks in your list:";
            System.arraycopy(results.toString().split("\n"), 0, messages, 1, results.size());
            ui.show(messages);
        }
    }
}
