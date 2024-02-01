package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
     *
     * @param tasks The TaskList Object that contains a List of Tasks.
     * @param ui THe Ui Object that interact with the user.
     * @param storage Storage Manager to write the file.
     * @throws DukeException If missing the description.
     */
    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("Missing the Description!");
        }

        ui.printAnyStatement("Here are the matching tasks in your list:");

        int index = 1;

        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(splitInput[1].trim())) {
                ui.printAnyStatement((index++) + "." + task.toString());
            }
        }

        if (index == 1) {
            ui.printAnyStatement("There is no such task.");
        }

    }
}
