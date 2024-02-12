package aurora.command;

import aurora.objects.AuroraException;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The DeadlineCommand class handles the "deadline" command.
 */
public class DeadlineCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /** Storage to interact with. */
    private Storage storage;

    /** Full command. */
    private String command;

    /**
     * Constructor for the DeadlineCommand class.
     *
     * @param taskList TaskList to edit.
     * @param ui Ui to interact with.
     * @param storage Storage to interact with.
     * @param command Full command input.
     */
    public DeadlineCommand(TaskList taskList, Ui ui, Storage storage, String command) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
        this.command = command;
    }

    @Override
    public String handle() throws AuroraException {
        String message = "Command not executed.";
        String[] descriptionAndDateSplit = Parser.splitAtFirstBlank(this.command);
        if (descriptionAndDateSplit.length < 2) {
            throw new AuroraException("Invalid number of arguments!\n" +
                    "Make sure to enter deadline, then specify the description of the task followed by the deadline.\n" +
                    "These two fields should be separated with /by.");
        }
        String descriptionAndDate = descriptionAndDateSplit[1];
        String[] splitVariables = Parser.splitAtFirstBy(descriptionAndDate);
        if (splitVariables.length < 2) {
            throw new AuroraException("Invalid number of arguments!\n" +
                    "Make sure to enter deadline, then specify the description of the task followed by the deadline.\n" +
                    "These two fields should be separated with /by.");
        } else {
            String description = splitVariables[0];
            String dateString = splitVariables[1];
            try {
                LocalDateTime dateLdt = Parser.parseDate(dateString.trim());
                this.taskList.addDeadline(description, dateLdt);
                //this.ui.echoAddTask(this.taskList);
            } catch (DateTimeParseException e) {
                throw new AuroraException("Invalid date format. Please use dd/MM/yyyy HHmm format for deadlines.");
            }
        }
        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            return "Unable to save deadline to file: " + exception.getMessage();
        }
        return this.ui.getEchoAddTaskString(this.taskList);
    }

    @Override
    public boolean isBye() {
        return false;
    }

}
