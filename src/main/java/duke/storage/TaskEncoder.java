package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The TaskEncoder class implements a method that would encode Task object into string
 */
public class TaskEncoder {
    /**
     * Returns the encoded string of the task object using the predefined format
     * @param task the Task object to be encoded
     * @return The encoded string for a task
     */
    public static String encodeTask(Task task) {
        int hasDoneInt = task.getHasDone() ? 1 : 0;
        if (task instanceof Todo) {
            return String.format("T | %d | %s ", hasDoneInt, task.getDescription());
        } else if (task instanceof Deadline) {
            return String.format("D | %d | %s | %s", hasDoneInt, task.getDescription(),
                    ((Deadline) task).getDeadlineString());
        } else {
            Event event = (Event) task;
            return String.format("T | %d | %s | %s-%s", hasDoneInt, event.getDescription(),
                    event.getStartDateString(), event.getEndDateString());
        }
    }
}
