package seedu.duke.command;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.common.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;


/**
 * Represents a find command initiated by the user. <code>FindCommand</code> would find tasks that match a keyword
 * and display it to the users.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_USAGE = "find: finds tasks that match a keyword.\n Example: find book";
    private String keyword;

    /**
     * Constructor of the FindCommand
     *
     * @param keyword The keyword of the task
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks that match the keyword and then display them to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Pattern pattern = Pattern.compile(".*" + keyword + ".*");
        List<Task> tasks = new ArrayList<>();


        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTask(i);
            String description = task.getDescription();
            Matcher matcher = pattern.matcher(description);
            if (matcher.matches()) {
                tasks.add(task);
            }
        }

        ui.generateFindResultResponse(tasks);
    }
}
