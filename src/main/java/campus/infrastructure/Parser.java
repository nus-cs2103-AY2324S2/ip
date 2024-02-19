package campus.infrastructure;

import campus.exceptions.CampusException;
import campus.tasks.Deadline;
import campus.tasks.Event;
import campus.tasks.Task;
import campus.tasks.ToDos;


/**
 * Parser handles all the logic for when a command is entered into the ChatBot, it serves as the bridge between the
 * storage object and taskList object and is the main handler for data travelling between these two fields
 */
public class Parser {
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

    /**
     * Initialises the Parser class, which requires a ui, tasklist and storage field.
     * @param ui The Ui Class
     * @param taskList The TaskList Class
     * @param storage The Storage Class
     */
    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.storage = storage;

        this.taskList = taskList;
        this.taskList.updateListFromFile(storage.getListOfStrings());
    }

    /**
     * Parses the user's message, works as the main logic tree to handle the user's commands
     */
    public String parseMessage(String userInput) {
        try {
            String message = this.processCommand(userInput);
            assert (message != null);
            return message;
        } catch (CampusException e) {
            return this.ui.displayErrorMessage(e);
        }
    }

    /**
     * Process the command fed by the user and decides which path of execution to go next, also handles null cases
     * and unknown command cases
     * @param userInput the user command input
     * @return boolean true for continue feeding, false only in the case of "bye" that is to exit the ChatBot
     * @throws CampusException Exception in the case that the command is not understood
     */
    public String processCommand(String userInput) throws CampusException {
        String msg = null;
        String[] arr = userInput.trim().split(" ", 2);
        String firstWord = arr[0].trim();
        String remaining = arr.length > 1 ? arr[1].trim() : "";

        switch (firstWord) {
        case "list":
            // fallthrough
        case "mark":
            // fallthrough
        case "unmark":
            // fallthrough
        case "delete":
            msg = handleUpdateCommands(firstWord, userInput);
            break;
        case "todo":
            // fallthrough
        case "deadline":
            // fallthrough
        case "event":
            msg = handleTaskCommand(firstWord, remaining);
            break;
        case "bye":
            return ui.printExiting();
        case "":
            break;
        case "find":
            msg = handleFindCommand(remaining);
            break;
        case "help":
            msg = handleHelpCommand();
            break;
        default:
            throw new CampusException("Sorry, I don't understand that command,"
                    + " please check for potential spelling errors");
        }
        this.storage.updateFileFromList(this.taskList);
        assert (msg != null);
        return msg;
    }

    private String handleHelpCommand() {
        String message = "CampusBot's Full Command List\n"
                + "The following commands are used to manipulate entries in the list of tasks:\n"
                + "'mark' - marks a particular task as complete\n"
                + "'unmark' - unmarks a particular task as incomplete\n"
                + "'delete' - deletes a particular task\n"
                + "The following commands are used to create a new entry in the list of tasks:\n"
                + "'todo' - creates a new todo task in the list\n"
                + "'deadline' - creates a new deadline task in the list\n"
                + "'event' - creates a new event task in the list\n"
                + "The following commands are admin related commands used to navigate the ChatBot\n"
                + "'list' - displays the current list of tasks\n"
                + "'find' - searches for a task in the list by the keyword that proceeds the find command\n"
                + "'help' - displays the help page, the one you are seeing now :)\n"
                + "'bye' - exits the ChatBot\n";
        return message;
    }

    private String handleTaskCommand(String command, String details) throws CampusException {
        switch (command) {
        case "todo":
            return handleTodoCommand(details);
        case "deadline":
            return handleDeadlineCommand(details);
        case "event":
            return handleEventCommand(details);
        default:
            throw new CampusException("Invalid task command");
        }
    }

    /**
     * Function to handle the command of type 'find' which deals with filtering a specific substring.
     * @param remaining the remaining portion of the command line entered
     */
    public String handleFindCommand(String remaining) {
        TaskList tempTaskList = this.taskList.getTaskListWhere(remaining);
        return this.ui.printTaskList(tempTaskList);
    }

    /**
     * Handles the commands for mark, unmark and delete - passes it to the taskList object which contains methods
     * to deal with manipulation of the task.
     * @param command mark/unmark/delete.
     * @param userInput the index of the list.
     */
    public String handleUpdateCommands(String command, String userInput) {
        Task task = this.taskList.getIthTaskString(userInput);
        String msg = null;
        switch (command) {
        case "mark":
            this.taskList.markDone(task);
            msg = this.ui.markDone(task);
            break;
        case "unmark":
            this.taskList.markUndone(task);
            msg = this.ui.markUndone(task);
            break;
        case "delete":
            this.taskList.deleteTask(task);
            msg = this.ui.delete(this.taskList, task);
            break;
        default:
            assert(false);
        }
        assert(msg != null);
        return msg;
    }

    /**
     * Handles the creation of a new Todo Object
     * @param remaining remaining split of the string
     * @throws CampusException throws an exception if the todo object is not initialised properly
     */
    public String handleTodoCommand(String remaining) throws CampusException {
        if (remaining.isEmpty()) {
            throw new CampusException("Error! A todo task must have a name, "
                    + "please follow the following syntax: todo <task name>\n");
        } else {
            ToDos todo = new ToDos(remaining);
            this.taskList.addTask(todo);
            return this.ui.add(this.taskList, todo);
        }
    }

    /**
     * Handles the creation of a deadline object
     * @param remaining remaining split of the string
     * @throws CampusException throws an exception if the deadline object is not initialised properly
     */
    public String handleDeadlineCommand(String remaining) throws CampusException {
        String[] temp = remaining.split("/by", 2);
        if (temp.length != 2) {
            throw new CampusException("Error! A deadline task must have the correct number of parameters, "
                    + "please follow the following syntax: deadline <deadline name> "
                    + "/by <endDateTime (HHmm dd/MM/yyyy)>\n");
        }

        String deadlineName = temp[0].trim();
        String endDateTime = temp[1].trim();

        try {
            Deadline deadline = new Deadline(deadlineName, endDateTime);
            this.taskList.addTask(deadline);
            return this.ui.add(this.taskList, deadline);
        } catch (CampusException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the creation of an event object
     * @param remaining remaining split of the string
     * @throws CampusException throws an exception if the event object is not initialised properly
     */
    public String handleEventCommand(String remaining) throws CampusException {
        String[] temp = remaining.split("/from", 2);

        if (temp.length != 2) {
            throw new CampusException("Error! An event task must have the correct number of parameters, "
                    + "please follow the following syntax: event <event name> "
                    + "/from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n");
        }

        String eventName = temp[0].trim();
        String remaining1 = temp[1].trim();
        String[] temp2 = remaining1.split("/to", 2);

        if (temp2.length != 2) {
            throw new CampusException("Error! An event task must have parameters, "
                    + "please follow the following syntax: event <event name> "
                    + "/from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n");
        }

        String from = temp2[0].trim();
        String to = temp2[1].trim();

        try {
            Event event = new Event(eventName, from, to);
            this.taskList.addTask(event);
            return this.ui.add(this.taskList, event);
        } catch (CampusException e) {
            return e.getMessage();
        }
    }
}
