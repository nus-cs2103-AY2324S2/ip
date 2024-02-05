package commands;

import tasks.TaskList;

public class ListCommand extends Command {
    public void execute(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasks.get(i));
        }
    }
}
