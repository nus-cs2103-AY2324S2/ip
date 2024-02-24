package damon.command;

import damon.storage.Storage;
import damon.task.Deadline;
import damon.task.Event;
import damon.task.Task;
import damon.task.ToDo;
import damon.tasklist.TaskList;
import damon.ui.Ui;

import java.time.LocalDate;

public class AddCommand extends Command {
    public AddCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask;

        if (this.command.startsWith("todo")) {
            newTask = addToDo(this.command);
        } else if (this.command.startsWith("deadline")) {
            newTask = addDeadline(this.command);
        } else {
            newTask = addEvent(this.command);
        }

        tasks.addTask(newTask);
        ui.showAddCommand(newTask, tasks);
        storage.writeFile(tasks);
    }

    private Task addToDo(String inputString) {
        String description = inputString.substring(5);

        return new ToDo(description);
    }

    private Task addDeadline(String inputString) {
        String[] splittedString = inputString.substring(9)
                .split(" /by ");
        String description = splittedString[0];
        String by = splittedString[1];

        return new Deadline(description, LocalDate.parse(by));
    }

    private Task addEvent(String inputString) {
        String[] firstSplittedString = inputString.substring(6)
                .split(" /from ");
        String description = firstSplittedString[0];

        String[] secondSplittedString = firstSplittedString[1].split(" /to ");
        String startTime = secondSplittedString[0];
        String endTime = secondSplittedString[1];

        return new Event(description, startTime, endTime);
    }
}
