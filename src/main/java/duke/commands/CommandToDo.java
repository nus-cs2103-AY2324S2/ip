package duke.commands;

import duke.Ui;

import duke.exceptions.DukeCeption;
import duke.exceptions.EmptyDescriptionException;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * The command class to create a ToDo task
 */
public class CommandToDo extends Command {

    public CommandToDo(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(String description) {
        try {
            Task task = this.cleanUserInput(description);
            taskList.addNewTask(task);
            ui.add("Okay! added this ToDo:");
            ui.add(task.toString());
            ui.add(String.format("Now you have %d tasks in the list.", taskList.getSize()));
        } catch (DukeCeption e) {
            ui.add(e.getMessage());
        }
    }

    /**
     * Cleans the raw user input and creates a ToDo Task after cleaning user input
     * Returns a task after cleaning the user input
     * @param description String of raw user input
     * @return Task after cleaning user input
     * @throws DukeCeption when description is empty or /by is written incorrectly
     */
    public Task cleanUserInput(String description) throws DukeCeption {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("ToDo cannot be empty!");
        }
        Task task = new ToDo(description);
        return task;
    }

}
