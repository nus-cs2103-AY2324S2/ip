package aurora.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import aurora.objects.AuroraException;
import aurora.objects.Task;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/**
 * The DoAfterCommand class handles the "doAfter" command:
 */
public class DoAfterCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /** Storage to interact with. */
    private Storage storage;

    /** Full command. */
    private String command;

    /**
     * Constructor for the DoAfterCommand class.
     *
     * @param taskList TaskList to edit.
     * @param ui Ui to interact with.
     * @param storage Storage to interact with.
     * @param command Full command input.
     */
    public DoAfterCommand(TaskList taskList, Ui ui, Storage storage, String command) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
        this.command = command;
    }

    @Override
    public String handle() throws AuroraException {
        String message = "Command not executed.";
        String[] descriptionAndAftSplit = Parser.splitAtFirstBlank(this.command);
        if (descriptionAndAftSplit.length < 2) {
            throw new AuroraException(AuroraException.INVALID_DOAFTER_FORMAT);
        }
        String descriptionAndAft = descriptionAndAftSplit[1];
        String[] splitDescriptionsAndAft = Parser.splitAtFirstAfter(descriptionAndAft);
        if (splitDescriptionsAndAft.length < 2) {
            throw new AuroraException(AuroraException.INVALID_DOAFTER_FORMAT);
        }
        if (splitDescriptionsAndAft[1].matches("-?\\d+(\\.\\d+)?")) {
            message = handleCaseTask(splitDescriptionsAndAft);
        } else {
            try {
                message = handleCaseTime(splitDescriptionsAndAft);
            } catch (DateTimeParseException e) {
                throw new AuroraException(AuroraException.INVALID_DOAFTER_FORMAT);
            }
        }

        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            message = "Unable to save doAfter to file: " + exception.getMessage();
            return message;
        }

        return message;
    }

    /**
     * Method to handle tha case where the doAfter needs to be done after a date and time.
     *
     * @param splitDescriptionsAndTime
     * @return Response to command.
     * @throws AuroraException If there is an error.
     */
    public String handleCaseTime(String[] splitDescriptionsAndTime) throws DateTimeParseException {
        String message = "";
        String description = splitDescriptionsAndTime[0];
        String dateString = splitDescriptionsAndTime[1];
        LocalDateTime dateLdt = Parser.parseDate(dateString.trim());
        this.taskList.addDoAfter(description, dateLdt);
        message = this.ui.getEchoAddTaskString(this.taskList);
        return message;
    }

    /**
     * Method to handle tha case where the doAfter needs to be done after a task.
     *
     * @param splitDescriptionsAndTask
     * @return Response to command.
     * @throws AuroraException If there is an error.
     */
    public String handleCaseTask(String[] splitDescriptionsAndTask) throws AuroraException {
        String message = "";
        String description = splitDescriptionsAndTask[0];
        int taskNumber = Integer.parseInt(splitDescriptionsAndTask[1]);
        if (taskNumber < 0) {
            throw new AuroraException("Please enter an integer greater than 0 as the second input.");
        } else if (taskNumber > this.taskList.getTaskList().size()) {
            throw new AuroraException("Please enter an integer representing a task within the list.");
        } else {
            this.taskList.addDoAfter(description, taskNumber - 1);
            message = this.ui.getEchoAddTaskString(this.taskList);
        }
        return message;
    }
}
