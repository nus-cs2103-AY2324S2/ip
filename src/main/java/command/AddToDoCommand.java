package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;
import task.ToDo;

import java.util.ArrayList;

/**
 * A AddToDoCommand to add new ToDo task.
 * A subclass of Command class.
 */
public class AddToDoCommand extends Command {

    private String input;

    /**
     * Creates a constructor with userInput as argument.
     * @param userInput
     */
    public AddToDoCommand(String userInput) {
        this.input = userInput;
    }

    /**
     * Adds new ToDo task.
     * Performs some prior checks to ensure the validity of the new Deadline.
     * If invalid input occurs, error message is returned.
     *
     * @param tasks the Task Object that contains a List of Task.
     * @param ui the Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws DukeException If there is missing description
     */
    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("Missing the description!");
        }
        String name = "";
        for (int i = 1; i < splitInput.length; i++) {
            name += splitInput[i] + " ";
        }
        ToDo newToDo= new ToDo(name.trim(), false);
        tasks.addTask(newToDo);
        ArrayList<Task> newToDoList = new ArrayList<>();
        newToDoList.add(newToDo);
        storage.writeArrayListToFile(newToDoList, false);
        ui.printAnyStatement("Got it. I've added this task:");
        ui.printAnyStatement(newToDo.toString());
        ui.printAnyStatement("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
