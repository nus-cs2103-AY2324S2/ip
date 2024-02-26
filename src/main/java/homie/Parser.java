package homie;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Parser class to parse the user command. Process all the user commands and performs the relevant tasks.
 */
public class Parser {
    private boolean isExit = false;
    private String outputResponse = "";
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructor for Parser object.
     * Initialise taskList, ui and storage objects to be used.
     *
     * @param taskList The taskList to be updated.
     * @param ui The ui object to interact with the user interface.
     * @param storage The storage object to be updated.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }
    //@@author ZhiWei1010-reused
    //Reused from https://www.baeldung.com/java-string-valid-date
    // with minor modifications
    /**
     * Checks if input string is of 'dd MM yyyy HHmm' format
     *
     * @param inDate The string to be checked.
     * @return The boolean value of whether the String is of specified date format "dd MM yyy HHmm".
     */
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy HHmm");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
    /**
     * Parses user input calling the relevant methods to process the command.
     *
     * @param fullCommand The full user input command
     */
    public String parse(String fullCommand) throws HomieException, TodoException, EventException, DeadlineException,
            MarkException, UnmarkException, DeleteException, FindException {
        String[] inputStringSplits = fullCommand.trim().split(" ");
        String primaryCommand = inputStringSplits[0].toLowerCase();
        String outputResponse;
        switch (primaryCommand) {
        case "b":
        case "bye":
            this.isExit = true;
            outputResponse = this.processByeCommand();
            break;
        case "l":
        case "list":
            outputResponse = this.processListCommand();
            break;
        case "m":
        case "mark":
            outputResponse = this.processMarkCommand(inputStringSplits);
            break;
        case "unm":
        case "unmark":
            outputResponse = this.processUnmarkCommand(inputStringSplits);
            break;
        case "del":
        case "delete":
            outputResponse = this.processDeleteCommand(inputStringSplits);
            break;
        case "f":
        case "find":
            outputResponse = this.processFindCommand(fullCommand, inputStringSplits);
            break;
        case "t":
        case "todo":
            outputResponse = this.processTodoCommand(fullCommand, inputStringSplits);
            break;
        case "dead":
        case "deadline":
            outputResponse = this.processDeadlineCommand(fullCommand, inputStringSplits);
            break;
        case "e":
        case "event":
            outputResponse = this.processEventCommand(fullCommand, inputStringSplits);
            break;
        default:
            outputResponse = this.processInvalidCommand(fullCommand);
            break;
        }
        return outputResponse;
    }
    /**
     * Process bye command and set isExit to true.
     *
     * @return Returns a goodbye message in String.
     */
    private String processByeCommand() {
        // Get response message
        outputResponse = this.ui.getGoodbyeMessage();
        this.isExit = true;
        return outputResponse;
    }

    /**
     * Process list command and show all tasks in the task list.
     *
     * @return String of tasks in the tasks list.
     */
    private String processListCommand() {
        // Get response message
        String allTasks = this.taskList.getTasks();
        outputResponse = this.ui.showListMessage(allTasks);
        return outputResponse;
    }

    /**
     * Process the to-do command to add new to-do task into the task list.
     * Updates the storage file as well.
     *
     * @param fullCommand The full command of the user input.
     * @return String message to show that task has been added.
     */
    private String processTodoCommand(String fullCommand, String[] commandSplits) throws TodoException {
        // Get Description for new to-do tasks
        if (commandSplits.length < 2) {
            throw new TodoException("No description given!");
        }
        int startIndexForTodoDescription = 5;
        String todoDescription = fullCommand.trim().substring(startIndexForTodoDescription);
        // Create to-do task
        Todo currTodo = new Todo(todoDescription);
        // Add to-do task to task list
        this.taskList.addTask(currTodo);
        // Update storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        outputResponse = this.ui.getToDoMessage(currTodo, this.taskList.getSize());
        return outputResponse;
    }
    /**
     * Process deadline command to add new deadline task into the task list.
     * Updates the storage file as well.
     *
     * @param fullCommand The full user input command.
     * @return String message showing that selected deadline task has been added into the task list.
     */
    private String processDeadlineCommand(String fullCommand, String[] commandSplits) throws DeadlineException {
        int startIndexForDeadlineDescription = 9;
        int startIndexForDeadlineDueDate = 3;
        if (commandSplits.length < 2) {
            throw new DeadlineException("No description is given!");
        }
        // Create new deadline task
        String[] deadlineCommandStringSplits = (fullCommand.trim().substring(startIndexForDeadlineDescription))
                .split("/");
        String deadlineDescription = deadlineCommandStringSplits[0].trim();
        if (deadlineDescription.isEmpty()) {
            throw new DeadlineException("No description is given!");
        }
        String deadlineDueDateInString = deadlineCommandStringSplits[1].substring(startIndexForDeadlineDueDate);
        if (!isValidDate(deadlineDueDateInString)) {
            throw new DeadlineException("Invalid date format!");
        }
        Deadline currDeadline = new Deadline(deadlineDescription, deadlineDueDateInString);
        // Add new deadline task to task list
        this.taskList.addTask(currDeadline);
        // Update storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        outputResponse = this.ui.getDeadlineMessage(currDeadline, this.taskList.getSize());
        return outputResponse;
    }
    /**
     * Process event command to add new event task into the task list.
     * Updates the storage file as well.
     *
     * @param fullCommand The full user input command.
     * @return String message showing that selected event task has been added into the task list.
     */
    private String processEventCommand(String fullCommand, String[] commandSplits) throws EventException {
        if (commandSplits.length < 2) {
            throw new EventException("No description given!");
        }
        int startIndexForEventDescription = 6;
        int startIndexForStartDateTime = 5;
        int startIndexForEndDateTime = 3;
        int endIndexForStartDateTime = 20;
        int endIndexForEndDateTime = 18;
        // Create new event task
        String[] eventCommandStringSplits = (fullCommand.trim()
                .substring(startIndexForEventDescription)).split("/");
        String eventDescription = eventCommandStringSplits[0].trim();
        if (eventDescription.isEmpty()) {
            throw new EventException("No description given!");
        }
        String eventStartDateTimeInString = eventCommandStringSplits[1].substring(startIndexForStartDateTime,
                endIndexForStartDateTime);
        if (!isValidDate(eventStartDateTimeInString)) {
            throw new EventException("/from date is of incorrect format!");
        }
        String eventEndDateTimeInString = eventCommandStringSplits[2].substring(startIndexForEndDateTime,
                endIndexForEndDateTime);
        if (!isValidDate(eventEndDateTimeInString)) {
            throw new EventException("/to date is of incorrect format!");
        }
        Event currEvent = new Event(eventDescription, eventStartDateTimeInString,
                eventEndDateTimeInString);
        // Add event task to task list
        this.taskList.addTask(currEvent);
        // Update storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        outputResponse = this.ui.getEventMessage(currEvent, this.taskList.getSize());
        return outputResponse;
    }
    /**
     * Process delete command to delete selected task from task list.
     * Update the storage file for future loading of tasks.
     *
     * @param inputStringSplits The String array containing the input command split by whitespace.
     * @return String message that shows selected task is deleted.
     */
    private String processDeleteCommand(String[] inputStringSplits) throws DeleteException {
        // Delete Task from task list
        Task currTask;
        if (inputStringSplits.length < 2) {
            throw new DeleteException("No index given!");
        }
        int taskIndex = Integer.parseInt(inputStringSplits[1]);
        if (taskIndex < 1 || taskIndex > this.taskList.getSize()) {
            throw new DeleteException("Invalid index.");
        }
        currTask = this.taskList.getTask(taskIndex);
        this.taskList.deleteTask(taskIndex);
        // Update Storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        outputResponse = this.ui.getDeleteMessage(currTask, taskList.getSize());
        return outputResponse;
    }
    /**
     * Process mark command to mark selected task as done.
     * Updates the storage file as well.
     *
     * @param inputStringSplits The String array containing the input command split by whitespace.
     * @return String message that shows selected task marked as done.
     */
    private String processMarkCommand(String[] inputStringSplits) throws MarkException {
        // Mark task as done (execute command)
        if (inputStringSplits.length < 2) {
            throw new MarkException("No index given!");
        }
        Task currTask;
        int taskIndex = Integer.parseInt(inputStringSplits[1]);
        if (taskIndex < 1 || taskIndex > this.taskList.getSize()) {
            throw new MarkException("Invalid index.");
        }
        this.taskList.markTask(taskIndex);
        // Update storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        currTask = this.taskList.getTask(taskIndex);
        outputResponse = this.ui.getMarkMessage(currTask);
        return outputResponse;
    }

    /**
     * Process the unmark command to set selected task as not done.
     * Updates the storage file as well.
     *
     * @param inputStringSplits The String array containing the input command split by whitespace.
     * @return String message that shows selected task marked as not done.
     */
    private String processUnmarkCommand(String[] inputStringSplits) throws UnmarkException {
        // Mark task as not done
        if (inputStringSplits.length < 2) {
            throw new UnmarkException("No index given!");
        }
        Task currTask;
        int taskIndex = Integer.parseInt(inputStringSplits[1]);
        this.taskList.unMarkTask(taskIndex);
        if (taskIndex < 1 || taskIndex > this.taskList.getSize()) {
            throw new UnmarkException("Invalid index.");
        }
        // Update Storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        currTask = this.taskList.getTask(taskIndex);
        outputResponse = this.ui.getUnmarkMessage(currTask);
        return outputResponse;
    }
    /**
     * Process find command to find the selected task from task list.
     *
     * @param inputStringSplits The String array containing the input command split by whitespace.
     * @return A String of tasks that has the matching keyword.
     */
    private String processFindCommand(String fullCommand, String[] inputStringSplits) throws FindException {
        if (inputStringSplits.length < 2) {
            throw new FindException("No keyword given!");
        }
        if (inputStringSplits.length != 3) {
            throw new FindException("Keyword can only be 1 word!");
        }
        String keywordToFind = inputStringSplits[1].trim();
        if (keywordToFind.isEmpty()) {
            throw new FindException("No keyword given!");
        }
        // Get response message
        String matchingTasks = this.taskList.findTask(keywordToFind);
        outputResponse = this.ui.showFindMessage(matchingTasks);
        return outputResponse;
    }

    /**
     * Process any invalid commands.
     *
     * @return String message showing that the command is invalid.
     */
    private String processInvalidCommand(String fullCommand) throws HomieException {
        // Get response message
        throw new HomieException(fullCommand);
    }
}
