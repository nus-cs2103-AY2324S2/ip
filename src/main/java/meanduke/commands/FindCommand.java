package meanduke.commands;

import meanduke.tasks.TaskList;

/**
 * This class represents a Command that filters a TaskList with a String
 */
public class FindCommand extends Command {

    private final TaskList taskList;
    private final String searchString;

    /**
     * Constructs a new FindCommand that will filter the given TaskList using the given String.
     *
     * @param taskList The TaskList to filter through.
     * @param searchString The String to filter the TaskList using.
     */
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
    public String execute() {
        return this.taskList.filter(this.searchString);
    }
}
