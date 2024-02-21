package meanduke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import meanduke.tasks.Deadline;
import meanduke.tasks.TaskList;

/**
 * This class represents a Command that adds a Deadline to a TaskList
 */
public class AddDeadlineCommand extends AddCommand {
    private final String description;
    private final LocalDate deadlineDate;
    private final LocalTime deadlineTime;

    /**
     * Constructs a new AddDeadlineCommand that adds a new Deadline to the specified TaskList
     *
     * @param taskList     The TaskList to add the new Deadline to.
     * @param description  The description of the new Deadline to be added.
     * @param deadlineDate The date where the deadline task needs to be completed.
     * @param deadlineTime The time when the deadline task needs to be completed.
     */
    public AddDeadlineCommand(TaskList taskList, String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskList);
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns the string describing the proper format to add a Deadline Task
     */
    public static String getUsage() {
        return Command.getUsage() + " add deadline <description> /by <YYYY-MM-DD> [HH:MM]";
    }

    @Override
    public String execute() {
        super.getTaskList().add(new Deadline(this.description,
                false, this.deadlineDate, this.deadlineTime));
        return "Added Deadline task: " + this.description
                + "(by: " + this.deadlineDate
                + (this.deadlineTime == null ? "" : " " + this.deadlineTime)
                + ")";
    }
}
