package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.Ui;

public class AddCommand extends Command {
    String taskType;
    String taskDescription;
    String deadline;
    String eventStart;
    String eventEnd;

    public AddCommand(String taskType, String taskDescription) {
        super();
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    public AddCommand(String taskType, String taskDescription, String deadline) {
        super();
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }

    public AddCommand(String taskType, String taskDescription, String eventStart, String eventEnd) {
        super();
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = null;
        if (this.taskType.equals("todo")) {
            task = new Todo(this.taskDescription);
        } else if (this.taskType.equals("deadline")) {
            task = new Deadline(this.taskDescription, this.deadline);
        } else {
            task = new Event(this.taskDescription, this.eventStart, this.eventEnd);
        }
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
    }
}
