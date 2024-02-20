package linus.commands;

import linus.Ui;
import linus.exceptions.EmptyDescriptionException;
import linus.exceptions.LinusCeption;
import linus.tasks.Task;
import linus.tasks.TaskList;
import linus.tasks.ToDo;

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
            String taskType = task.getTaskType();
            ui.addNewTask(taskType, task.toString(), taskList.getSize());
        } catch (LinusCeption e) {
            ui.add(e.getMessage());
        }
    }

    /**
     * Cleans the raw user input and creates a ToDo Task after cleaning user input
     * Returns a task after cleaning the user input
     * @param description String of raw user input
     * @return Task after cleaning user input
     * @throws LinusCeption when description is empty or /by is written incorrectly
     */
    public Task cleanUserInput(String description) throws LinusCeption {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("ToDo cannot be empty!");
        }
        Task task = new ToDo(description);
        return task;
    }

}
