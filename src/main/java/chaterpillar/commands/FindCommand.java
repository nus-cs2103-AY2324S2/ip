package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.storage.Storage;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to find all tasks containing the search word.
 *
 * @author marclamp
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for this class
     *
     * @param keyword to be searched and matched
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all tasks in the list that contains the matching word/phrase
     *
     * @param tasks the list of tasks.
     * @param ui UI for this application.
     * @param storage storage for this application.
     * @return reply from the ChatBot.
     * @throws ChaterpillarException if empty search keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        if (this.keyword.trim().isEmpty()) {
            throw new ChaterpillarException("Word/phrase to be search cannot be empty!");
        }

        TaskList newList = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.containsInName(this.keyword)) {
                newList.addTask(task);
            }
        }

        if (newList.size() == 0) {
            String output = "There are no items that match your search.";
            ui.echo(output);
            return output;
        } else {
            String output = "Here are the items that match the keyword (" + this.keyword + "): \n";
            return output + new ListCommand(newList).execute(newList, ui, storage);
        }
    }
}
