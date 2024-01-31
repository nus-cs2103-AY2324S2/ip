package capone.commands;

import capone.*;
import capone.exceptions.CaponeException;
import capone.tasks.ToDo;

import java.util.ArrayList;

public class TodoCommand extends Command {
    private final ArrayList<String> inputList;

    public TodoCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage storage) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter a description for this capone.tasks.ToDo task!\n" +
                    "Usage: todo [description]");
        }

        // Combine the remaining words into a single string
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < inputList.size(); i++) {
            if (i == inputList.size() - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        ToDo newTodo = new ToDo(description.toString(), false);
        taskList.addTask(newTodo);
        storage.writeTasksToJsonFile(taskList);

        ui.sendMessage(String.format("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", newTodo.toString(), taskList.getSize()));
    }
}
