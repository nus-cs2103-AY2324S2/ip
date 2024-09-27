package jade.commands;

import java.util.stream.IntStream;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * The <code>FindCommand</code> object represents the command to
 * search for tasks that contain the keyword.
 */
public class FindCommand extends Command {
    private static final String RESULT_MSG_FORMATTED = "Here are the matching tasks I've found:%s";
    private static final String EMPTY_RESULT_MSG = "Sorry, I did not find any matching results.";
    private final String keyword; // the keyword of the description to be searched

    /**
     * Class constructor specifying the keyword of the description to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     * Prints all tasks that contain the keyword.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws JadeException {
        StringBuilder sb = new StringBuilder();
        int[] count = {0}; // track the number of matching tasks found
        IntStream.range(0, taskList.size())
                .filter(x -> taskList.get(x).containsKeyword(keyword))
                .forEach(x -> {
                    count[0]++;
                    sb.append(String.format("\n\t%d. %s", x + 1, taskList.get(x)));
                });
        if (count[0] == 0) {
            return EMPTY_RESULT_MSG;
        } else {
            return String.format(RESULT_MSG_FORMATTED, sb);
        }
    }

    /**
     * {@inheritDoc}
     * Indicates that the program is not exiting.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

