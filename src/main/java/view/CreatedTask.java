package view;

import objects.Task;
import objects.TaskList;

public class CreatedTask {
    public static void display(TaskList tasks, Task task) {
        String o = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task, tasks.size());
        EncaseLines.display(o);
    }
}
