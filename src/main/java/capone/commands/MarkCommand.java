package capone.commands;

import capone.*;
import capone.exceptions.CaponeException;
import capone.tasks.Task;

import java.util.ArrayList;

public class MarkCommand extends Command{
    private final ArrayList<String> inputList;

    public MarkCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage storage) throws CaponeException {
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter an index of a task you'd like to mark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: mark [index]");
        } else if (inputList.size() > 2) {
            throw new CaponeException("Please enter only one index you would like to mark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: mark [index]");
        }

        // Mark task as done.
        try {
            Task markedTask = taskList.getTask(Integer.parseInt(inputList.get(1))-1);
            markedTask.markTask();
            storage.writeTasksToJsonFile(taskList);

            // Inform user that task has been marked.
            ui.sendMessage("Nice! I've marked this task as done:\n" + markedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CaponeException("Sorry, you have entered an invalid index.\n" +
                    "You can check the list of valid indices using the 'list' command.");
        }
    }
}
