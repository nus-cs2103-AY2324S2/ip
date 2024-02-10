package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;

public class ViewByDateCommand extends Command {

    private LocalDate date;

    public ViewByDateCommand(LocalDate date) {
        super("viewbydate", null);
        this.date = date;
    }

    @Override
    public TaskList execute(TaskList tasks) {
        List<Task> tasksByDate = tasks.getTasksByDate(date);

        // Displaying the date header
        System.out.printf("Here are your tasks on %s:\n", formatDate(date));

        // Displaying tasks
        for (int i = 0; i < tasksByDate.size(); i++) {
            System.out.println((i + 1) + ". " + tasksByDate.get(i));
        }

        return new TaskList(tasksByDate);
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }
}
