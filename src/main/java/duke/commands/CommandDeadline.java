package duke.commands;

import duke.Ui;

import duke.exceptions.DukeCeption;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.IncorrectFormatException;

import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The command class to create a deadline task
 */
public class CommandDeadline extends Command {
    
    public CommandDeadline(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * {@inherit}
     */
    @Override
    public void execute(String description) {
        try {
            Task task = cleanUserInput(description);
            taskList.addNewTask(task);
            ui.add("Okay! added this Deadline:");
            ui.add(task.toString());
            ui.add(String.format("Now you have %d tasks in the list.", taskList.getSize()));
        } catch (DukeCeption e) {
            ui.add(e.getMessage());
        } finally {
            ui.print();
        }
    }

    /**
     * Cleans the raw user input and creates a Task after cleaning user input
     * Returns a task after cleaning the user input
     * @param description String of raw user input
     * @return Task after cleaning user input
     * @throws DukeCeption when description is empty or /by is written incorrectly
     */
    public Task cleanUserInput(String description) throws DukeCeption {
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

