package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private String description;

    private LocalDate by;
    private String tag;
    private boolean isTagged;

    /**
     * Constructs a DeadlineCommand with the specified input, description, and due date.
     *
     * @param input       The input string associated with the command.
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task.
     * @param tag         The tag of the deadline task.
     */
    public DeadlineCommand(String input, String description, LocalDate by, String tag) {
        super(input);
        this.description = description;
        this.by = by;
        this.tag = tag;
        this.isTagged = true;
    }

    /**
     * Constructs a DeadlineCommand with the specified input, description, and due date.
     *
     * @param input       The input string associated with the command.
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task.
     */
    public DeadlineCommand(String input, String description, LocalDate by) {
        super(input);
        this.description = description;
        this.by = by;
        this.tag = "null";
        this.isTagged = false;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        int counter = tasks.getCounter();


        Task t;
        if (isTagged) {
            t = new Deadline(description, tag, by);
        } else {
            t = new Deadline(description, by);
        }
        tasks.addTask(t);

        return ui.showAddTaskMessage(t, counter);
    }
}
