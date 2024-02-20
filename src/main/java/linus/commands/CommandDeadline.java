package linus.commands;

import linus.Ui;
import linus.exceptions.EmptyDescriptionException;
import linus.exceptions.IncorrectFormatException;
import linus.exceptions.LinusCeption;
import linus.tasks.Deadline;
import linus.tasks.Task;
import linus.tasks.TaskList;

/**
 * The command class to create a deadline task
 */
public class CommandDeadline extends Command {
    
    public CommandDeadline(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(String description) {
        try {
            Task task = cleanUserInput(description);
            taskList.addNewTask(task);
            String taskType = task.getTaskType();
            ui.addNewTask(taskType, task.toString(), taskList.getSize());
        } catch (LinusCeption e) {
            ui.add(e.getMessage());
        }
    }

    /**
     * Cleans the raw user input and creates a Deadline Task after cleaning user input
     * Returns a task after cleaning the user input
     * @param description String of raw user input
     * @return Task after cleaning user input
     * @throws DukeCeption when description is empty or /by is written incorrectly
     */
    public Task cleanUserInput(String description) throws LinusCeption {
        try {
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("Deadline cannot be empty!");
            }
            String[] descriptionList = description.split("/by", 2);
            String taskDescription = descriptionList[0].trim();
            String by = descriptionList[1].trim();
            Task task = new Deadline(taskDescription, by);
            return task;
            } catch (IndexOutOfBoundsException e) {
                throw new IncorrectFormatException("Make sure /by is written properly");
            }
        }
    }

