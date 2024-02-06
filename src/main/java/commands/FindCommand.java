package commands;

import exceptions.FileError;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;


public class FindCommand extends Command {
    private final String wordToSearch;

    public FindCommand(String wordToSearch) {
        this.wordToSearch = wordToSearch;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileError {
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (Task task : tasks.getTaskList()) {
            if (task.descriptionContainsWord(wordToSearch)) {
                output.append(counter).append(".");
                output.append(task);
                counter++;
            }
        }
        ui.showResult(String.valueOf(output));
    }
}
