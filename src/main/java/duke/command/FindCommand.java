package duke.command;

import duke.util.Parser;
import duke.util.TaskList;

/**
 * Represents the Command of finding a task from a list.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Initializes a Command to find e a task that contains the given keyword.
     *
     * @param type the type of the Command which is find.
     * @param keyword the keyword for finding the task.
     */
    public FindCommand(Parser.Cmd type, String keyword) {
        super(type);
        this.keyword = keyword;
    }

    /**
     * Finds and displays the task with the keyword.
     *
     * @param taskList the given taskList from which the task with keyword is searched.
     */
    @Override
    public void run(TaskList taskList) {
        taskList.searchAndDisplay(this.keyword);
    }
}
