package lindi.commands;

import lindi.storage.Storage;
import lindi.task.CreateEventException;
import lindi.task.Task;
import lindi.task.TaskList;

/**
 * Represents a command that creates an Event upon execution
 */
public class CreateEventCommand extends Command {
    private final String commandString;

    /**
     * Creates command to create an Event with the arguments supplied by the user
     *
     * @param commandString user input string
     */
    public CreateEventCommand(String commandString) {
        this.commandString = commandString;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Creates an Event and adds it to task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task createdtask = Task.createEvent(this.commandString);
            tasks.add(createdtask);
            this.statusMsg = String.format("Okay. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                    createdtask, tasks.size());
            storage.saveToFile(tasks);
        } catch (CreateEventException e) {
            this.statusMsg = e.getMessage();
        }
    }
}
