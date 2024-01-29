package campus.infrastructure;

import campus.exceptions.CampusException;
import campus.tasks.Deadline;
import campus.tasks.Event;
import campus.tasks.Task;
import campus.tasks.ToDos;

import java.util.Scanner;

/**
 * Parser handles all the logic for when a command is entered into the ChatBot, it serves as the bridge between the
 * storage object and taskList object and is the main handler for data travelling between these two fields
 */
public class Parser {
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.storage = storage;

        try {
            this.taskList = taskList;
            this.taskList.updateListFromFile(storage.getListOfStrings());
        } catch (CampusException e) {
            this.ui.displayErrorMessage(e);
        }
    }

    /**
     * Constantly active and listens for user input
     */
    public void listen() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean online = true;

        do {
            try {
                userInput = scanner.nextLine();
                online = this.processCommand(userInput);
            } catch (CampusException e) {
                this.ui.displayErrorMessage(e);
            }
        } while (online);
    }

    /**
     * Process the command fed by the user and decides which path of execution to go next, also handles null cases
     * and unknown command cases
     * @param userInput the user command input
     * @return boolean true for continue feeding, false only in the case of "bye" that is to exit the ChatBot
     * @throws CampusException Exception in the case that the command is not understood
     */
    public boolean processCommand(String userInput) throws CampusException {
        String[] arr = userInput.split(" ", 2);
        String firstWord;
        String remaining;

        if (arr.length > 1) {
            firstWord = arr[0].trim();
            remaining = arr[1].trim();
        } else {
            firstWord = arr[0].trim();
            remaining = "";
        }

        switch(firstWord) {
            case "list":
                this.ui.display(this.taskList);
                break;
            case "mark":
            case "unmark":
            case "delete":
                handleUpdateCommands(firstWord, userInput);
                break;
            case "todo":
                handleTodoCommand(remaining);
                break;
            case "deadline":
                handleDeadlineCommand(remaining);
                break;
            case "event":
                handleEventCommand(remaining);
                break;
            case "bye":
                return false;
            case "":
                break;
            case "find":
                handleFindCommand(remaining);
                break;
            default:
                throw new CampusException("Sorry, I don't understand that command, please check for potential spelling errors");
        }
        this.storage.updateFileFromList(this.taskList);
        return true;
    }

    public void handleFindCommand(String remaining) {
        TaskList tempTaskList = this.taskList.getTaskListWhere(remaining);
        this.ui.display(tempTaskList);
    }

    public void handleUpdateCommands (String command, String userInput) {
        Task task = this.taskList.getIthTaskString(userInput);
        switch (command) {
            case "mark":
                this.taskList.markDone(task);
                this.ui.markDone(task);
                break;
            case "unmark":
                this.taskList.markUndone(task);
                this.ui.markUndone(task);
                break;
            case "delete":
                this.taskList.delete(task);
                this.ui.delete(this.taskList, task);
                break;
        }
    }

    public void handleTodoCommand(String remaining) throws CampusException {
        if (remaining.isEmpty()) {
            throw new CampusException("Error! A todo task must have a name, please follow the following syntax: todo <task name>\n");
        } else {
            ToDos todo = new ToDos(remaining);
            this.taskList.add(todo);
            this.ui.add(this.taskList, todo);
        }
    }

    public void handleDeadlineCommand(String remaining) throws CampusException {
        String[] temp = remaining.split("/by", 2);
        if (temp.length != 2) {
            throw new CampusException("Error! A deadline task must have the correct number of parameters, please follow the following syntax: deadline <deadline name> /by <endDateTime (HHmm dd/MM/yyyy)>\n");
        }

        String deadlineName = temp[0].trim();
        String endDateTime = temp[1].trim();

        try {
            Deadline deadline = new Deadline(deadlineName, endDateTime);
            this.taskList.add(deadline);
            this.ui.add(this.taskList, deadline);
        } catch (CampusException e) {
            System.out.printf("%s\n%n", e.getMessage());
        }
    }

    public void handleEventCommand(String remaining) throws CampusException {
        String[] temp = remaining.split("/from", 2);

        if (temp.length != 2) {
            throw new CampusException("Error! An event task must have the correct number of parameters, please follow the following syntax: event <event name> /from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n");
        }

        String eventName = temp[0].trim();
        String remaining1 = temp[1].trim();
        String[] temp2 = remaining1.split("/to", 2);

        if (temp2.length != 2) {
            throw new CampusException("Error! An event task must have parameters, please follow the following syntax: event <event name> /from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n");
        }

        String from = temp2[0].trim();
        String to = temp2[1].trim();

        try {
            Event event = new Event(eventName, from, to);
            this.taskList.add(event);
            this.ui.add(this.taskList, event);
        } catch (CampusException e) {
            System.out.printf("%s\n%n", e.getMessage());
        }
    }
}
