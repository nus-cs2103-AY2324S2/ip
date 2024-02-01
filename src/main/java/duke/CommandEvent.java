package duke;

import duke.tasks.Event;
import duke.tasks.Task;

public class CommandEvent extends Command {

    CommandEvent(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(String description) {
        try {
            Task task = this.cleanUserInput(description);
            taskList.addNewTask(task);
            ui.add("Okay! added this Event:");
            ui.add(task.toString());
            ui.add(String.format("Now you have %d tasks in the list.", taskList.getSize()));
        } catch (DukeCeption e) {
            ui.add(e.getMessage());
        } finally {
            ui.print();
        }
    }
    
    private Task cleanUserInput(String description) throws DukeCeption {
        try {
            if (description.isEmpty()) {
                throw new DukeCeption("Event cannot be empty!");
            }
            String[] descriptionList = description.split("/from", 2);
            String[] fromAndToList = descriptionList[1].split("/to", 2);
            String taskDescription = descriptionList[0].trim();
            String from = fromAndToList[0].trim();
            String to = fromAndToList[1].trim();
            Task task = new Event(taskDescription, from, to);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeCeption("Make sure /from and /to is written properly");
        }
    }
}
