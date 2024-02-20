package duke;

/**
 * Parser class to parse the user command
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
    /**
     * Parses user input calling the relevant methods to
     * process the command.
     *
     * @param fullCommand The full user input command
     */

    public String parse(String fullCommand) {
        String[] inputStringSplits = fullCommand.split(" ");
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
            outputResponse = this.processFindCommand(inputStringSplits);
            break;
        case "t":
        case "todo":
            outputResponse = this.processTodoCommand(inputStringSplits);
            break;
        case "dead":
        case "deadline":
            outputResponse = this.processDeadlineCommand(fullCommand);
            break;
        case "e":
        case "event":
            outputResponse = this.processEventCommand(fullCommand);
            break;
        default:
            outputResponse = this.processInvalidCommand();
            break;
        }
        return outputResponse;
    }
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Process bye command and set isExit to true.
     *
     * @return Returns a string goodbye message.
     */
    private String processByeCommand() {
        // Get response message
        outputResponse = ui.showGoodbyeMessage();
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
        outputResponse = this.ui.showListMessage(this.taskList);
        return outputResponse;
    }

    /**
     * Process mark command to mark selected task as done.
     * Updates the storage file as well.
     *
     * @param inputStringSplits The String array containing the input command split by whitespace.
     * @return String message that shows selected task marked as done.
     */
    private String processMarkCommand(String[] inputStringSplits) {
        // Mark task as done (execute command)
        Task currTask;
        int taskIndex = Integer.parseInt(inputStringSplits[1]);
        this.taskList.markTask(taskIndex);
        // Update storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        currTask = this.taskList.getTask(taskIndex);
        outputResponse = this.ui.showMarkMessage(currTask);
        return outputResponse;
    }

    /**
     * Process the unmark command to set selected task as not done.
     * Updates the storage file as well.
     *
     * @param inputStringSplits The String array containing the input command split by whitespace.
     * @return String message that shows selected task marked as not done.
     */
    private String processUnmarkCommand(String[] inputStringSplits) {
        // Mark task as not done
        Task currTask;
        int taskIndex = Integer.parseInt(inputStringSplits[1]);
        this.taskList.unMarkTask(taskIndex);
        // Update Storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        currTask = this.taskList.getTask(taskIndex);
        outputResponse = this.ui.showUnmarkMessage(currTask);
        return outputResponse;
    }

    /**
     * Process delete command to delete selected task from task list.
     * Update the storage file for future loading of tasks.
     *
     * @param inputStringSplits The String array containing the input command split by whitespace.
     * @return String message that shows selected task is deleted.
     */
    private String processDeleteCommand(String[] inputStringSplits) {
        // Delete Task from task list
        Task currTask;
        int taskIndex = Integer.parseInt(inputStringSplits[1]);
        currTask = this.taskList.getTask(taskIndex);
        this.taskList.deleteTask(taskIndex);
        // Update Storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        outputResponse = this.ui.showDeleteMessage(currTask, taskList);
        return outputResponse;
    }

    /**
     * Process find command to find the selected task from task list.
     *
     * @param inputStringSplits The String array containing the input command split by whitespace.
     * @return A String of tasks that has the matching keyword.
     */
    private String processFindCommand(String[] inputStringSplits) {
        String keywordToFind = inputStringSplits[1];
        // Get response message
        outputResponse = this.ui.showFindMessage(this.taskList, keywordToFind);
        return outputResponse;
    }

    /**
     * Process the to-do command to add new to-do task into the task list.
     * Updates the storage file as well.
     *
     * @param inputStringSplits The String array containing the input command split by whitespace.
     * @return String message to show that task has been added.
     */
    private String processTodoCommand(String[] inputStringSplits) {
        // Get Description for new to-do tasks
        StringBuilder todoDescription = new StringBuilder();
        for (int i = 1; i < inputStringSplits.length; i++) {
            todoDescription.append(inputStringSplits[i]);
        }
        try {
            // Create to-do task
            Todo currTodo = new Todo(todoDescription.toString());
            // Add to-do task to task list
            this.taskList.addTask(currTodo);
            // Update storage
            this.storage.updateStorageFile(this.taskList);
            // Get response message
            outputResponse = this.ui.showToDoMessage(currTodo, this.taskList);
        } catch (TodoException e) {
            System.err.println(e.getMessage());
        }
        return outputResponse;
    }

    /**
     * Process deadline command to add new deadline task into the task list.
     * Updates the storage file as well.
     *
     * @param fullCommand The full user input command.
     * @return String message showing that selected deadline task has been added into the task list.
     */
    private String processDeadlineCommand(String fullCommand) {
        int lengthOfDeadline = 9;
        int lengthOfBy = 3;
        // Create new deadline task
        String[] deadlineCommandStringSplits = (fullCommand.substring(lengthOfDeadline)).split("/");
        String deadlineDescription = deadlineCommandStringSplits[0];
        String deadlineByInString = deadlineCommandStringSplits[1].substring(lengthOfBy);
        Deadline currDeadline = new Deadline(deadlineDescription, deadlineByInString);
        // Add new deadline task to task list
        this.taskList.addTask(currDeadline);
        // Update storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        outputResponse = this.ui.showDeadlineMessage(currDeadline, this.taskList);
        return outputResponse;
    }

    /**
     * Process event command to add new event task into the task list.
     * Updates the storage file as well.
     *
     * @param fullCommand The full user input command.
     * @return String message showing that selected event task has been added into the task list.
     */
    private String processEventCommand(String fullCommand) {
        int lengthOfEvent = 6;
        int lengthOfFrom = 5;
        int lengthOfTo = 3;
        // Create new event task
        String[] eventCommandStringSplits = (fullCommand.substring(lengthOfEvent)).split("/");
        String eventDescription = eventCommandStringSplits[0];
        String eventStartTimeInString = eventCommandStringSplits[1].substring(lengthOfFrom);
        String eventEndTimeInString = eventCommandStringSplits[2].substring(lengthOfTo);
        Event currEvent = new Event(eventDescription, eventStartTimeInString,
                eventEndTimeInString);
        // Add event task to task list
        this.taskList.addTask(currEvent);
        // Update storage
        this.storage.updateStorageFile(this.taskList);
        // Get response message
        outputResponse = this.ui.showEventMessage(currEvent, this.taskList);
        return outputResponse;
    }

    /**
     * Process any invalid commands.
     *
     * @return String message showing that the command is invalid.
     */
    private String processInvalidCommand() {
        // Get response message
        outputResponse = this.ui.showWrongCommand();
        return outputResponse;
    }


}
