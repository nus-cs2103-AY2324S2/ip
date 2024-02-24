package view;

import objects.Task;

public class SnoozedTask extends UI {
    public static String display(Task task) {
        return String.format("I've delayed this task by 1 hour:\n   %s", task.toString());
    }
}
