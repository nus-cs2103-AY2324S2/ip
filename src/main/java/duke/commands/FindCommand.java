package duke.commands;

import duke.core.Ui;
import duke.tasks.TaskList;

public class FindCommand extends Command {

    private final TaskList taskList;
    private final String searchString;

    public FindCommand(TaskList taskList, String searchString) {
        super();
        this.taskList = taskList;
        this.searchString = searchString;
    }

    /**
     * Returns the String containing the proper usage of filtering the tasklist
     */
    public static String getUsage() {
        return Command.getUsage() + " find <filter>";
    }

    @Override
    public void execute() {
        Ui.printMessage(taskList.filter(searchString));
    }
}
