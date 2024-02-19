import java.time.LocalDate;

public class AddCommand extends Command {
    AddCommand(String command) {
        super(command);
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
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

    Task addToDoTask(String inputString) {
        String description = inputString.substring(5);
        return new ToDo(description);
    }

    Task addDeadlineTask(String inputString) {
        String[] splittedString = inputString.substring(9).split(" /by ");
        String description = splittedString[0];
        String by = splittedString[1];
        return new Deadline(description, LocalDate.parse(by));
    }

    Task addEventTask(String inputString) {
        String[] firstSplittedString = inputString.substring(6).split(" /from ");
        String description = firstSplittedString[0];
        String[] secondSplittedString = firstSplittedString[1].split(" /to ");
        String startTime = secondSplittedString[0];
        String endTime = secondSplittedString[1];
        return new Event(description, startTime, endTime);
    }
}
