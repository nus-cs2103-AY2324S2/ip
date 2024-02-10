package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

/**
 * <code>Command</code> to find all tasks containing the search word.
 */
public class FindCommand extends Command{
    private final String keyword;

    /**
     * Constructor for this class
     * @param keyword to be searched and matched
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all tasks in the list that contains the matching word/phrase
     * @param tasks the list of tasks
     * @param ui UI for this application
     * @param storage storage for this application
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        if (this.keyword.trim().isEmpty()) {
            ui.echo("Word/phrase to be search cannot be empty!");
            return;
        }

        TaskList newList = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.containsInName(this.keyword)) {
                newList.addTask(task);
            }
        }

        if (newList.size() == 0) {
            ui.echo("There are no items that match your search.");
        } else {
            new ListCommand(newList).execute(newList, ui, storage);
        }
    }
}
