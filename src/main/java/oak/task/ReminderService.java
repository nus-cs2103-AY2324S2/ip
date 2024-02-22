package oak.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import oak.task.model.Deadline;
import oak.task.model.Event;
import oak.task.model.Task;
import oak.task.model.Todo;

public class ReminderService {
    /**
     * Checks all tasks currently in the system and formats a return String of all tasks
     * for which the user should be reminded
     *
     * @return A Formatted String about the tasks to remind the user of
     */
    public String getReminders() {
        StringBuilder result = new StringBuilder();

        ArrayList<Task> allTasks = TaskService.tasks;

        for (Task task : allTasks) {
            // Note: No need to check Todo since we do not have set deadlines for them
            if (task instanceof Event) {
                if (toRemind(((Event) task).getToDateTime())) {
                    result.append(task).append("\n");
                }
            } else if (task instanceof Deadline) {
                if (toRemind(((Deadline) task).getByDateTime())) {
                    result.append(task).append("\n");
                }
            }
        }
        if (result.length() > 1) {
            result.append("Here are your daily reminders! \n").append(result);
        }
        else {
            result.append("Congratulations! You have no reminders for any tasks!");
        }

        return result.toString();
    }

    /**
     * Checks if the user needs to be reminded of a specific task based on the following criteria:
     * 1) The task has not expired, that is, the toDateTime or the byDateTime has not passed the current datetime
     *
     * @param toDateTime
     * @return Either true or false, indicating whether the user should be reminded about the event
     */
    private Boolean toRemind(LocalDateTime toDateTime) {
        LocalDateTime cur = LocalDateTime.now();
        return cur.isBefore(toDateTime);
    }
}
