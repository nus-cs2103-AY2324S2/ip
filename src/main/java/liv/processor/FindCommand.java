package liv.processor;

import java.util.ArrayList;
import liv.container.Storage;
import liv.container.TaskList;
import liv.exception.LivException;
import liv.ui.Ui;

public class FindCommand  extends Command {
    private String keyword;

    public FindCommand (String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LivException {
        ArrayList<String> matchingTasks = tasks.findMatchingTasks(keyword);
        String message = Ui.getFindMessage(matchingTasks);
        return message;
    }
}
