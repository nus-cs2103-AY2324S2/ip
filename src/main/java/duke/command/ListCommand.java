package duke.command;

import java.util.List;

import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super("list", List.of());
    }

    @Override
    public TaskList execute(TaskList tasks) {
        System.out.printf("\nHere's your list! ~~(^-^)\n");
        for (int i = 0; i < tasks.getNoOfTasks(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.getTask(i));
        }
        return tasks;
    }
}
