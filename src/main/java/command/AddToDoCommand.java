package command;


import java.util.ArrayList;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;
import task.Task;
import task.ToDo;


/**
 * A AddToDoCommand to add new ToDo task.
 * A subclass of Command class.
 */
public class AddToDoCommand extends Command {

    /** The information from the user to create new ToDo. */
    private String input;

    /**
     * Creates a constructor with userInput as argument.
     *
     * @param userInput The information when creating new ToDo.
     */
    public AddToDoCommand(String userInput) {
        this.input = userInput;
    }

    /**
     * Adds new ToDo task.
     * Performs some prior checks to ensure the validity of the new Deadline.
     * If invalid input occurs, error message is returned.
     * Returns the response from Andelu.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui the Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @return The response from Andelu.
     * @throws AndeluException If there is missing description
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {
        assert input != null : "input should not be null";
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new AndeluException("Missing the description!");
        }

        String name = "";
        for (int i = 1; i < splitInput.length; i++) {
            name += splitInput[i] + " ";
        }

        ToDo newToDo = new ToDo(name.trim(), false);
        tasks.addTask(newToDo);
        ArrayList<Task> newToDoList = new ArrayList<>();
        newToDoList.add(newToDo);
        storage.writeArrayListToFile(newToDoList, false);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it. I've added this task:\n");
        stringBuilder.append(newToDo.toString() + "\n");
        stringBuilder.append("Now you have " + tasks.getTasks().size() + " tasks in the list.\n");

        ui.printAnyStatement("Got it. I've added this task:");
        ui.printAnyStatement(newToDo.toString());
        ui.printAnyStatement("Now you have " + tasks.getTasks().size() + " tasks in the list.");

        return stringBuilder.toString();
    }
}
