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

/** The DoAfterCommand class represents the "doAfter" command: */
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
     * Constructs a DoAfterCommand object.
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
     * Validates the doAfter command input by checking if it follows the appropriate format.
     * Returns a String array containing the description at index 0 and either a datetime or
     * the index of the task to be associated with the new DoAfter object at index 1.
     *
     * @return String array containing the description at index 0 and either a datetime or
     *         the index of the task to be associated with the new DoAfter object at index 1.
     * @throws AuroraException If the DoAfter command is of an invalid format.
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
     * Returns a String alerting the user that the DoAfter object has been created based on the command input.
     *
     * @param splitDescriptionsAndAft String array containing the description of the DoAfter at index 0 and
     *                                the datetime or task it is associated with at index 1.
     * @return String alert regarding the creation of the DoAfter.
     * @throws AuroraException If the datetime associated with the DoAfter is of invalid format.
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
     * Saves the task list after the new DoAfter task is added to the task list to the storage file.
     *
     * @throws AuroraException If an error occurs while saving the task list to the storage file.
     */
    private void saveDoAfterTasks() throws AuroraException {
        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            throw new AuroraException("I'm unable to save doAfter to file: " + exception.getMessage());
        }
    }

    /**
     * Returns a String alerting the user that the DoAfter object with a datetime associated
     * to it has been created and added to the task list.
     *
     * @param splitDescriptionsAndTime String array containing the description of the DoAfter at index 0 and
     *                                 the datetime or task it is associated with at index 1.
     * @return String alerting the user that the DoAfter object with a datetime associated
     *         to it has been created and added to the task list.
     * @throws DateTimeParseException If the datetime is of incorrect format.
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
     * Returns a String alerting the user that the DoAfter object with another task associated
     * to it has been created and added to the task list.
     *
     * @param splitDescriptionsAndTask String array containing the description of the DoAfter at index 0 and
     *                                 the datetime or task it is associated with at index 1.
     * @return String alerting the user that the DoAfter object with a datetime associated
     *         to it has been created and added to the task list.
     * @throws AuroraException If an error occurs while creating and adding the new DoAfter object to the task list.
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
