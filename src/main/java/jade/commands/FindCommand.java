package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

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
     * @inheritDoc This implementation prints all tasks that contain the keyword.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JadeException {
        StringBuilder sb = new StringBuilder();
        int count = 0; // track the number of matching tasks found
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i - 1).containsKeyword(keyword)) {
                count++;
                sb.append(String.format("\n\t%d. %s", count, tasks.get(i - 1)));
            }
        }
        if (count == 0) {
            return "There are no matching results!";
        } else {
            return String.format("Here are the matching tasks in your list:%s", sb);
        }
    }

    /**
     * @inheritDoc The DeleteCommand does not indicate the exit of the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

