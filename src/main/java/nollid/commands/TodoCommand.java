package nollid.commands;

import java.util.ArrayList;

import nollid.Parser;
import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.EmptyDescriptionException;
import nollid.exceptions.MissingTagsException;
import nollid.exceptions.NollidException;
import nollid.tasks.Todo;

/**
 * TodoCommand class represents a command to add a new ToDo task.
 */
public class TodoCommand extends Command {
    private static final String USAGE_HINT = "Usage: todo task_description [/tags tag1,tag2,...]";
    /**
     * ArrayList containing command arguments.
     */
    private final ArrayList<String> argsList;

    /**
     * Constructor for TodoCommand.
     *
     * @param argsList ArrayList containing command arguments.
     */
    public TodoCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    /**
     * Overrides the execute method from the Command class.
     * Executes the command to add a todo task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws NollidException {
        String taskDescription;
        try {
            taskDescription = Parser.getDescription(argsList);
        } catch (EmptyDescriptionException e) {
            throw new EmptyDescriptionException(e.getMessage() + "\n" + USAGE_HINT);
        }

        ArrayList<String> tags;
        try {
            tags = Parser.getTags(argsList);
        } catch (MissingTagsException e) {
            throw new MissingTagsException(e.getMessage() + "\n" + USAGE_HINT);
        }

        Todo task = new Todo(taskDescription, tags);
        tasks.add(task);
        storage.update(tasks);
        return tasks.getAddSuccessMessage(task);
    }
}
