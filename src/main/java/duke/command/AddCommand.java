package duke.command;

import java.util.List;

import duke.task.Task;
import duke.task.TaskList;


public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super("add", List.of());
        this.task = task;
    }

    public TaskList execute(TaskList tasks) {
        tasks.addTask(task);
        Integer count = tasks.getNoOfTasks();

        System.out.printf(
            "\n        ~~~ >^o_o^< ~~~\nGot it! I've added this task:\n"
            + "- %s\n\nYou have %d task(s) in the list.\n",
            task, count);
        return tasks;
    }
}
