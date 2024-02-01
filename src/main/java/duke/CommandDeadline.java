package duke;

import duke.tasks.Deadline;
import duke.tasks.Task;

public class CommandDeadline extends Command {
    
    public CommandDeadline(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

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

    public Task cleanUserInput(String description) throws DukeCeption {
        try {
            if (description.isEmpty()) {
                throw new DukeCeption("Deadline cannot be empty!");
            }
            String[] descriptionList = description.split("/by", 2);
            String taskDescription = descriptionList[0].trim();
            String by = descriptionList[1].trim();
            Task task = new Deadline(taskDescription, by);
            return task;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeCeption("Make sure /by is written properly");
            }
        }
    }

