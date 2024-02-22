package shodan.command.impl;

import java.util.List;
import java.util.stream.Collectors;

import shodan.ShodanException;
import shodan.TaskList;
import shodan.command.Command;
import shodan.storage.StorageManager;
import shodan.ui.TermUi;

/**
 * Finds tasks matching the given keywords from the task list.
 */
public class FindCommand extends Command {
    private List<String> keywords;

    /**
     * Instantiates a new Find command.
     *
     * @param keywords the keywords to search for in the task.
     */
    public FindCommand(List<String> keywords) {
        /* Filter out any keywords containing spaces.
         * This should only occur when the user enters a string
         * containing multiple spaces in a row.
         */
        this.keywords = keywords.stream()
                .filter(keyword -> !keyword.contains(" "))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    public boolean execute(TaskList tasks, StorageManager storageManager, TermUi ui) throws ShodanException {
        ui.listSearchedTasks(tasks.findTasks(keywords));
        return false;
    }
}
