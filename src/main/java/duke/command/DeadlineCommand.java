package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;
import duke.task.Task;
import duke.task.Deadline;

import java.util.*;
public class DeadlineCommand extends Command {
    private final String DETAILS;

    public DeadlineCommand(String detail) {
        this.DETAILS = detail;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
