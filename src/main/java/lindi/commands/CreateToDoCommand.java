package lindi.commands;

import lindi.storage.Storage;
import lindi.task.CreateToDoException;
import lindi.task.Task;
import lindi.task.TaskList;

/**
 * Represents a command that creates a ToDo upon execution
 */
public class CreateToDoCommand extends Command {
    public static final String COMMAND_TODO = "todo";
    private final String commandString;

    /**
     * Creates command to create a ToDo with the arguments supplied by the user
     *
     * @param commandString user input string
     */
    public CreateToDoCommand(String commandString) {
        this.commandString = commandString;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Creates a ToDo and adds it to task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task createdtask = Task.createToDo(this.commandString);
            tasks.add(createdtask);
            this.statusMsg = String.format("Okay. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                    createdtask, tasks.size());
            storage.saveToFile(tasks);
        } catch (CreateToDoException e) {
            this.statusMsg = e.getMessage();
        }
    }
}
