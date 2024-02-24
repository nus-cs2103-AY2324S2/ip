package damon.command;

import damon.storage.Storage;
import damon.task.Deadline;
import damon.task.Event;
import damon.task.Task;
import damon.task.ToDo;
import damon.tasklist.TaskList;
import damon.ui.Ui;

import java.time.LocalDate;

/**
 * Represents AddCommand object which is corresponding to user's adding Task input.
 */
public class AddCommand extends Command {
    public AddCommand(String command) {
        super(command);
    }

    /**
     * Executes AddCommand, i.e., adds a new Task to current TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object of Damon object.
     * @param storage Storage object of Damon object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask;
        if (this.command.startsWith("todo")) {
            newTask = addToDoTask(this.command);
        } else if (this.command.startsWith("deadline")) {
            newTask = addDeadlineTask(this.command);
        } else {
            newTask = addEventTask(this.command);
        }
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks);
        storage.writeFile(tasks);
    }

    private Task addToDoTask(String inputString) {
        String description = inputString.substring(5);
        return new ToDo(description);
    }

    private Task addDeadlineTask(String inputString) {
        String[] splittedString = inputString.substring(9).split(" /by ");
        String description = splittedString[0];
        String by = splittedString[1];
        return new Deadline(description, LocalDate.parse(by));
    }

    private Task addEventTask(String inputString) {
        String[] firstSplittedString = inputString.substring(6).split(" /from ");
        String description = firstSplittedString[0];
        String[] secondSplittedString = firstSplittedString[1].split(" /to ");
        String startTime = secondSplittedString[0];
        String endTime = secondSplittedString[1];
        return new Event(description, startTime, endTime);
    }
}
