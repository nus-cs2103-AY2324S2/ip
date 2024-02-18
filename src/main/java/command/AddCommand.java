package command;

import exceptions.DukeException;
import task.Task;
import task.TaskList;
import utilities.Storage;

/**
 * Controls what happens when a task is added.
 */
public class AddCommand extends Command {
    /**
     * Task to be added into the task list.
     */
    private Task taskToAdd;

    /**
     * AddCommand class constructor.
     * @param newTask New task created and waiting to be added into the task list.
     */
    public AddCommand(Task newTask) {
        super(false);
        this.taskToAdd = newTask;
    }

    /**
     * Executes the add task process.
     * @param taskList The task list that the task is added to.
     * @param storage The storage that the task list is stored in after the task is added.
     * @return The response expected from the chatbot.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        for (int i = 0; i < taskList.length(); i++) {
            if (this.taskToAdd.descriptionToString().equals(taskList.getTask(i).descriptionToString())) {
                throw new DukeException("Task already exists!");
            }
        }
        taskList.addTask(this.taskToAdd);
        storage.save(taskList);
        String s = String.format("Got it. I've added this task:\n%s\n%s",
                this.taskToAdd.toString(), taskList.numTaskToString());
        return s;
    }
}
