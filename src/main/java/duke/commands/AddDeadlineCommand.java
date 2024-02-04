package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.core.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddDeadlineCommand extends AddCommand {
    private String description;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    public AddDeadlineCommand(TaskList taskList, String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskList);
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    public static String getUsage() {
        return Command.getUsage() + " add deadline <description> /by <YYYY-MM-DD> [HH:MM]";
    }

    public void execute() {
        super.getTaskList().add(new Deadline(this.description,
                false, this.deadlineDate, this.deadlineTime));
        Ui.printMessage("Added Deadline task: " + description
                + "(by: " + deadlineDate
                + (deadlineTime == null ? "" : " " + deadlineTime)
                + ")");
    }
}
