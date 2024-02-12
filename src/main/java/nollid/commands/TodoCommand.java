package nollid.commands;

import java.util.ArrayList;
import java.util.Arrays;

import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.EmptyDescriptionException;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.NollidException;
import nollid.tasks.Todo;

/**
 * TodoCommand class represents a command to add a new ToDo task.
 */
public class TodoCommand extends Command {
    private static final String USAGE_HINT = "Usage: todo task_description {/tags tag1,tag2,tag3,...}";
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
        verifyArguments();

        String taskDescription = getTaskDescription();

        ArrayList<String> tags = getTags();
        Todo task = new Todo(taskDescription, tags);

        tasks.add(task);
        storage.update(tasks);

        String message = "Alright, added:\n" + "\t" + task + "\n";
        message += tasks.summary();
        return message;
    }

    private String getTaskDescription() throws EmptyDescriptionException {
        StringBuilder taskDescription = new StringBuilder();

        // Get text from after command until the first option. (/tags, /deadline, etc.)
        for (int i = 1; i < argsList.size(); i++) {
            if (!argsList.get(i).matches("/\\w+")) {
                taskDescription.append(argsList.get(i)).append(" ");
            } else {
                break;
            }
        }

        checkDescriptionNotEmpty(taskDescription.toString());

        return taskDescription.toString();
    }

    private void verifyArguments() throws NollidException {
        if (argsList.size() == 1) {
            throw new EmptyDescriptionException("Todo description cannot be empty!\n"
                    + USAGE_HINT);
        }

        if (argsList.indexOf("/tags") == 1) {
            throw new EmptyDescriptionException("Todo description cannot be empty!\n"
                    + USAGE_HINT);
        }

        boolean tagKeywordDetectedButNoTagsProvided = argsList.indexOf("/tags") == argsList.size() - 1;
        if (tagKeywordDetectedButNoTagsProvided) {
            throw new InvalidArgumentException("Did you forget to include some tags?\n"
                    + USAGE_HINT);
        }
    }

    private ArrayList<String> getTags() {
        int tagKeywordIndex = this.argsList.indexOf("/tags");

        // If user did not use "/tags" keyword, return empty list
        if (tagKeywordIndex == -1) {
            return new ArrayList<>();
        }

        int tagsIndex = tagKeywordIndex + 1;
        ArrayList<String> tags = new ArrayList<>(Arrays.asList(argsList.get(tagsIndex).split(",")));
        return tags;
    }

    private void checkDescriptionNotEmpty(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Todo description cannot be empty!\n"
                    + USAGE_HINT);
        }
    }
}
