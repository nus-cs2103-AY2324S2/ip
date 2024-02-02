package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;
import jade.ui.Ui;

/**
 * The <code>FindCommand</code> object represents the command to
 * search for tasks that contain the keyword.
 */
public class FindCommand extends Command {
    private final String keyword; // the keyword of the description to be searched

    /**
     * Class constructor specifying the keyword of the description to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @inheritDoc
     * This implementation prints all tasks that contain the keyword.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JadeException {
        StringBuilder sb = new StringBuilder();
        int count = 0; // track the number of matching tasks found
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).containsKeyword(keyword)) {
                count++;
                sb.append(String.format("\t%d. %s\n", count, tasks.get(i)));
            }
        }
        ui.printMessage(String.format("\tHere are the matching tasks in your list:\n%s", sb));
    }

    /**
     * @inheritDoc
     * The DeleteCommand does not indicate the exit of the program.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
