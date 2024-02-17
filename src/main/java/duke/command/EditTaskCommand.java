package duke.command;

import java.io.IOException;

import duke.JamieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
//import duke.task.ToDo;


public class EditTaskCommand extends Command {
    private final int index;
    private final String attribute;
    private final String newValue;

    public EditTaskCommand(int index, String attribute, String newValue) {
        this.index = index;
        this.attribute = attribute;
        this.newValue = newValue;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamieException {
        try {
            Task task = tasks.getTask(index);
            switch (attribute.toLowerCase()) {

            case "by": // For Deadline
                if (task instanceof Deadline) {
                    ((Deadline) task).setBy(newValue);
                } else {
                    throw new JamieException("This task does not support 'by' attribute.");
                }
                break;
            case "from": // For Event
                if (task instanceof Event) {
                    ((Event) task).setFrom(newValue);
                } else {
                    throw new JamieException("This task does not support 'from' attribute.");
                }
                break;
            case "to": // For Event
                if (task instanceof Event) {
                    ((Event) task).setTo(newValue);
                } else {
                    throw new JamieException("This task does not support 'to' attribute.");
                }
                break;
            default:
                throw new JamieException("Unknown attribute for editing.");
            }
            storage.save(tasks); // Save the updated task list
            return ui.showEditTaskMessage(task);
        } catch (IndexOutOfBoundsException e) {
            throw new JamieException("The task index provided is out of bounds.");
        } catch (JamieException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
