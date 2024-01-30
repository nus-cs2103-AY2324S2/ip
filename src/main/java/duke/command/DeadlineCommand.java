package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;
import duke.task.Task;
import duke.task.Deadline;

import java.util.*;

/**
 * Represents a DeadlineCommand class which adds a deadline task
 */
public class DeadlineCommand extends Command {
    private final String DETAILS;
    /**
     * Constructs a DeadlineCommand class with given parameter
     * @param detail A string representation of type deadline task by user
     */
    public DeadlineCommand(String detail) {
        this.DETAILS = detail;
    }

    /**
     * Break down the input parameter, creates Deadline task and add it to the task list
     * Generate ui message to user and saves the changes to file
     * @param tasksList A TaskList class that represents task list
     * @param ui A TextUi class that represents the ui
     * @param storage A Storage class which represents the storage of file
     */
    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) {
        String[] detailArray = DETAILS.split("/");
        System.out.println(DETAILS);
        System.out.println(Arrays.toString(detailArray));
        String description = detailArray[0].strip();
        String by = detailArray[1].strip();
        Task deadline = new Deadline(description, by);
        tasksList.addToTaskList(deadline);
        ui.showAddTaskMessage(deadline);
        storage.saveToFile(tasksList.getList());
    }

    /**
     * Returns a boolean value to indicate whether to exit the program
     * @return a boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
