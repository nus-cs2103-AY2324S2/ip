package duke.commands;

import duke.exceptions.InvalidCommandException;
import duke.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.TaskList;
import duke.Ui;


public class CreateTaskCommand extends Command {
    private String taskType;
    private String details;
    public CreateTaskCommand (String taskType, String details) {
        super(false);
        this.taskType = taskType;
        this.details = details;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        Task newTask;
        if (taskType.equals("todo")) {
            newTask = new ToDo(details);
        } else if (taskType.equals("deadline")) {
            String[] deadlineSplit = details.trim().split("/by");
            if (deadlineSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/by' to indicate the deadline!");
            }
            newTask = new Deadline(deadlineSplit[0].trim(), deadlineSplit[1].trim());
        } else {
            String[] fromSplit = details.split("/from");
            if (fromSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/from' to indicate event start time!"
                );
            }
            String eventName = fromSplit[0].trim();

            String[] toSplit = fromSplit[1].split("/to");
            if (toSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/to' to indicate event end time!"
                );
            }
            String from = toSplit[0].trim();
            String to = toSplit[1].trim();
            newTask = new Event(eventName, from, to);
        }

        tasks.addNewTask(newTask);

    }
}
