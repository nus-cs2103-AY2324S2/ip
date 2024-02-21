package meanduke.commands;

import meanduke.tasks.TaskList;
import meanduke.tasks.ToDo;

/**
 * This class represents a Command that adds a ToDO to a TaskList
 */
public class AddTodoCommand extends AddCommand {

    private final String description;

    /**
     * Constructs a new AddTodoCommand that adds a new ToDo to the specified TaskList
     *
     * @param taskList    The TaskList to add the new ToDo to.
     * @param description The description of the new ToDo to be added.
     */
    public AddTodoCommand(TaskList taskList, String description) {
        super(taskList);
        this.description = description;
    }

    /**
     * Returns the string describing the proper format to add a ToDo Task
     */
    public static String getUsage() {
        return Command.getUsage() + " add todo <description>";
    }

    @Override
    public String execute() {
        super.getTaskList().add(new ToDo(this.description));
        return "Added ToDo task: " + this.description;
    }
}
