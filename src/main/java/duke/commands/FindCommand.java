package duke.commands;

import duke.core.Ui;
import duke.tasks.TaskList;

public class FindCommand extends Command{

    private TaskList taskList;
    private String searchString;

    public FindCommand(TaskList taskList, String searchString) {
        super();
        this.taskList = taskList;
        this.searchString = searchString;
    }

    @Override
    public void execute() {
        Ui.printMessage(taskList.filter(searchString));
    }

    public static String getUsage() {
        return Command.getUsage() + "find <filter>";
    }
}
