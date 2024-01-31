package capone.commands;

import capone.*;
import capone.exceptions.CaponeException;
import capone.tasks.Task;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final ArrayList<String> inputList;

    public UnmarkCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter an index of a task you'd like to unmark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: unmark [index]");
        } else if (inputList.size() > 2) {
            throw new CaponeException("Please enter only one index you would like to unmark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: unmark [index]");
        }

        try {
            Task unmarkedTask = taskList.getTask(Integer.parseInt(inputList.get(1))-1);
            unmarkedTask.unmarkTask();
            storage.writeTasksToJsonFile(taskList);

            // Inform user that task has been marked.
            ui.sendMessage("OK, I've marked this task as not done yet:\n" + unmarkedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CaponeException("Sorry, you have entered an invalid index.\n" +
                    "You can check the list of valid indices using the 'list' command.");
        }
    }
}
