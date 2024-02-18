package asher.Commands;

import asher.BotException;
import asher.Tasks.Deadline;
import asher.Tasks.TaskList;
import asher.Ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String input) {
        super(input);
    }

    public Deadline createDeadlineCommand() throws BotException {
        int split = input.indexOf("/by");
        assert split != -1 : "Due date not found!";
        assert split + 4 < input.length() : "No such deadline!";

        String[] parts = input.substring(9).split("/by", 2);
        String description = parts[0].trim();
        String deadline = parts[1].trim();

        String[] deadlineParts = deadline.split(" ", 2);
        String dueDateInString = deadlineParts[0].trim();
        String dueTimeInString = deadlineParts[1].trim();
        LocalDate dueDate = LocalDate.parse(dueDateInString);
        LocalTime dueTime = LocalTime.parse(dueTimeInString);

        if (description.isEmpty() || deadline.isEmpty()) {
            throw new BotException("Description and deadline cannot be empty!");
        }
        return new Deadline(description, dueDate, dueTime);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Deadline deadline = createDeadlineCommand();
            taskList.addTask(deadline);
            String addTaskMessage = ui.showAddTaskMessage(deadline);
            int totalTask = taskList.getSize();
            String numberOfTaskMessage = ui.showNumberOfTaskInListMessage(totalTask);

            return addTaskMessage + "\n" + numberOfTaskMessage;
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
