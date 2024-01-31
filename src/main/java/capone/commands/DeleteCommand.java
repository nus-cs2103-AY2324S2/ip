package capone.commands;

import java.util.ArrayList;

import capone.Storage;
import capone.TaskList;
import capone.Ui;
import capone.exceptions.CaponeException;
import capone.tasks.Task;

public class DeleteCommand extends Command {
    private final ArrayList<String> inputList;

    public DeleteCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        // If the inputList has more than two arguments, throw exception.
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter an index of a task you'd like to delete.\n"
                    + "You can view all tasks using the 'list' command.\n"
                    + "Usage: delete [index]");
        } else if (inputList.size() > 2) {
            throw new CaponeException("Please enter only one index you would like to delete.\n"
                    + "You can view all tasks using the 'list' command.\n"
                    + "Usage: delete [index]");
        }

        try {
            // Remove the task from the tasks Arraylist.
            Task removedTask = taskList.removeTask(Integer.parseInt(inputList.get(1)) - 1);

            storage.writeTasksToJsonFile(taskList);

            ui.sendMessage(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n",
                    removedTask.toString(), taskList.getSize()));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CaponeException("Sorry, you have entered an invalid index.\n"
                    + "You can check the list of valid indices using the 'list' command.");
        }


    }


}
