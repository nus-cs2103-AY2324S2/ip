package shodan.command.impl;

import shodan.ShodanException;
import shodan.TaskList;
import shodan.command.Command;
import shodan.storage.StorageManager;
import shodan.tasks.Task;
import shodan.ui.TermUi;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    List<String> keywords;
    public FindCommand(List<String> keywords) {
        /* Filter out any keywords containing spaces.
        * This should only occur when the user enters a string
        * containing multiple spaces in a row.
        */
        this.keywords = keywords.stream()
                .filter(keyword -> !keyword.contains(" "))
                .collect(Collectors.toList());
    }
    public boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) throws ShodanException {
        ui.listSearchedTasks(tasks.findTasks(keywords));
        return false;
    }
}
