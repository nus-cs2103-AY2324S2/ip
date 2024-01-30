package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;
import duke.task.Task;
import duke.task.Event;

public class EventCommand extends Command {
    private final String DETAILS;

    /**
     * Constructs a EventCommand class with given parameter
     * @param details A string representation of type event task by user
     */
    public EventCommand(String details) {
        this.DETAILS = details;
    }
    /**
     * Break down the input parameter, creates Event task and add it to the task list
     * Generate ui message to user and saves the changes to file
     * @param tasksList A TaskList class that represents task list
     * @param ui A TextUi class that represents the ui
     * @param storage A Storage class which represents the storage of file
     */
    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) {
        String[] detailArray = DETAILS.split("/");
        String description = detailArray[0].strip();
        String from = detailArray[1].strip();
        String to = detailArray[1].strip();
        System.out.println(from);
        Task event = new Event(description, from, to);
        tasksList.addToTaskList(event);
        ui.showAddTaskMessage(event);
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