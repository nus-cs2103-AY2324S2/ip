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
        String[] splitDescriptionsAndAft = parseAndValidateInput();
        String message = processDoAfter(splitDescriptionsAndAft);
        saveDoAfterTasks();
        return message;
    }

    /**
     * Helper method to validate the command input.
     * @return String array containing the description of the DoAfter and the time or task it is associated with.
     * @throws AuroraException If the command is of invalid format.
     */
    private String[] parseAndValidateInput() throws AuroraException {
        String[] descriptionAndAftSplit = Parser.splitAtFirstBlank(this.command);
        if (descriptionAndAftSplit.length < 2) {
            throw new AuroraException(AuroraException.INVALID_DOAFTER_FORMAT);
        }
        String descriptionAndAft = descriptionAndAftSplit[1];
        String[] splitDescriptionsAndAft = Parser.splitAtFirstAfter(descriptionAndAft);
        if (splitDescriptionsAndAft.length < 2) {
            throw new AuroraException(AuroraException.INVALID_DOAFTER_FORMAT);
        }
        return splitDescriptionsAndAft;
    }

    /**
     * Helper method that decides how the doAfter command is processed.
     *
     * @param splitDescriptionsAndAft String array containing the description of the DoAfter and the time
     *                                or task it is associated with.
     * @return String alert regarding the creation of the DoAfter.
     * @throws AuroraException If the command is of invalid format.
     */
    private String processDoAfter(String[] splitDescriptionsAndAft) throws AuroraException {
        // Solution adapted from https://www.baeldung.com/java-check-string-number
        if (splitDescriptionsAndAft[1].matches("-?\\d+(\\.\\d+)?")) {
            return handleCaseTask(splitDescriptionsAndAft);
        }

        try {
            return handleCaseTime(splitDescriptionsAndAft);
        } catch (DateTimeParseException e) {
            throw new AuroraException(AuroraException.INVALID_DOAFTER_FORMAT);
        }
    }

    /**
     * Helper method to save the newly created DoAfter to the file.
     *
     * @throws AuroraException If the saving is unsuccessful.
     */
    private void saveDoAfterTasks() throws AuroraException {
        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            throw new AuroraException("I'm unable to save doAfter to file: " + exception.getMessage());
        }
    }

    /**
     * Method to handle tha case where the doAfter needs to be done after a date and time.
     *
     * @param splitDescriptionsAndTime String array containing the description and the String representation of the
     *                                 time the doAfter is associated with.
     * @return Response to command.
     * @throws DateTimeParseException If there is an error with handling the time.
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
     * @param splitDescriptionsAndTask String array containing the description and the String representation of the
     *                                 task the doAfter is associated with.
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
