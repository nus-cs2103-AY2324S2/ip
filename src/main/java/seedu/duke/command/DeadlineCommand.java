package seedu.duke.command;

import java.time.LocalDateTime;

import seedu.duke.common.TaskList;
import seedu.duke.exception.DuplicateTaskException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;


/**
 * Represents a deadline command initiated by the user. <code>DeadLineCommand</code> would creates a deadline task
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_USAGE = "deadline: it creates a deadline task \n"
            + "Example: deadline SU 2103T /by 2024-02-06 18:00";
    private String description;
    private LocalDateTime deadline;

    /**
     * Constructor of the deadline task
     *
     * @param description description of the deadline task
     * @param deadline    deadline of the task
     */
    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Creates a deadline task, save it to the task list and then display the result to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DuplicateTaskException {
        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTask(i);
            if (task instanceof Deadline) {
                boolean isSameDescription = description.equals(task.getDescription());
                boolean isSameTime = deadline.equals(((Deadline) task).getDeadline());
                boolean isSameStatus = !task.getHasDone();
                if (isSameStatus && isSameDescription && isSameTime) {
                    throw new DuplicateTaskException(task);
                }
            }
        }
        Deadline deadlineTask = new Deadline(description, false, deadline);
        taskList.addTask(deadlineTask);
        ui.generateNewTaskResponse(deadlineTask, taskList);
    }
}
