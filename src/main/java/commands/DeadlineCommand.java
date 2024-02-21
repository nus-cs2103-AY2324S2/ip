package commands;

import java.time.format.DateTimeParseException;

import irwyn.exceptions.CommandException;
import irwyn.tasks.Deadline;
import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;

/**
 * This class encapsulates the class DeadlineCommand.
 * It executes the creation of a Deadline object.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class DeadlineCommand extends Command {
    private final String[] deadline;

    /**
     * Constructor for a DeadlineCommand object.
     * @param input The input by the user to parse into a command.
     * @throws CommandException if the Deadline command is invalid.
     */
    public DeadlineCommand(String input) throws CommandException {
        super(false);
        if (!input.contains(" /by ")) {
            throw new CommandException();
        }
        deadline = input.replaceFirst("deadline ", "").split(" /by ");
    }

    /**
     * Executes the deadline command.
     * This method creates a Deadline object.
     * If the date format is invalid, it will display an error message.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handles storing & deleting of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        try {
            String description = this.deadline[0];
            String by = this.deadline[1];
            Deadline deadline = new Deadline(description, by);
            if (!isDuplicate(deadline, taskList)) {
                taskList.addTask(deadline);
            } else {
                ui.reply("This task already exists in the task list.");
            }
            storageManager.save(taskList.getTasks());
            ui.reply(deadline.replyString(taskList.getTasksSize()));
        } catch (DateTimeParseException e) {
            ui.reply("Invalid date format. Please use YYYY-MM-DD or YYYY-MM-DD HH:MM:SS format.\n");
        }
    }

    /**
     * Executes the deadline command.
     * This method creates a Deadline object.
     * If the date format is invalid, it will display an error message.
     *
     * @param storageManager Storage manager handles storing & deleting of tasks.
     * @param ui Ui handles output.
     * @param taskList TaskList handles the tasks list.
     * @return Response String.
     */
    @Override
    public String execute(StorageManager storageManager, Ui ui, TaskList taskList) {
        String description = this.deadline[0];
        String by = this.deadline[1];
        Deadline deadline = new Deadline(description, by);
        if (!isDuplicate(deadline, taskList)) {
            taskList.addTask(deadline);
        } else {
            return ui.getReply("This task already exists in the task list.");
        }
        storageManager.save(taskList.getTasks());
        return ui.getReply(deadline.replyString(taskList.getTasksSize()));
    }
}
