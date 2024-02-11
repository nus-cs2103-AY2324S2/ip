package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
/**
 * Command to create a new Deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    /**
     * Contructs a deadline command.
     *
     * @param description Description of task.
     * @param by Due date of deadline. LocalDate object.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command, creating a new task, adding it to the list, saving to storage.
     * Also displays messages to user.
     *
     * @param list TaskList object containing current tasks.
     * @param storage To update storage with new deadline task.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        Task newDeadline = Task.createTask(TaskType.DEADLINE, description, false, by);
        list.add(newDeadline);
        storage.save(list);

        super.setResponse("added new deadline: " + newDeadline
                + "\nLooks like you have " + list.countTasks() + " things left to do!");
    }
}
