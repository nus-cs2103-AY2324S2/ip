package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskEncoder {
    public static String encodeTask(Task task) {
        int hasDoneInt = task.getHasDone() ? 1 : 0;
        if (task instanceof Todo) {
            System.out.printf("T | %d | %s %n", hasDoneInt, task.getDescription());
            return String.format("T | %d | %s ", hasDoneInt, task.getDescription());
        }
        else if (task instanceof Deadline) {
            return String.format("D | %d | %s | %s", hasDoneInt, task.getDescription(),
                    ((Deadline) task).getDeadlineString());
        }
        else {
            Event event = (Event) task;
            return String.format("T | %d | %s | %s-%s", hasDoneInt, event.getDescription(),
                    event.getStartDateString(), event.getEndDateString());
        }
    }
}
