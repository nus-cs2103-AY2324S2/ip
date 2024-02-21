package command;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;
import task.Task;

/**
 * A FindCommand class to search the tasks based on the description.
 * A subclass of Command class.
 */
public class FindCommand extends Command {

    /** The information form the user to search tasks based on the description. */
    private String input;

    /**
     * A constructor with userInput as argument.
     *
     * @param userInput The information to search a task based on the description.
     */
    public FindCommand(String userInput) {
        this.input = userInput;
    }

    /**
     * Searches tasks based on the description.
     * If no description is inputted, an error message is returned.
     * Returns the response from Andelu.
     *
     * @param tasks The TaskList Object that contains a List of Tasks.
     * @param ui THe Ui Object that interact with the user.
     * @param storage Storage Manager to write the file.
     * @return The response from Andelu.
     * @throws AndeluException If missing the description.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {
        assert input != null : "input should not be null";
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new AndeluException("Missing the Description!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:\n");

        int index = 1;

        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(splitInput[1].trim())) {
                stringBuilder.append((index++) + "." + task.toString() + "\n");
            }
        }

        if (index == 1) {
            stringBuilder.append("There is no such task.\n");
        }
        return stringBuilder.toString();
    }
}
