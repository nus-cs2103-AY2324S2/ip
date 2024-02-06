package utilities;

import java.io.IOException;

import commands.Commands;
import exceptions.WilliamException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 * Deals with making sense of the user command
 */
public class Parser {

    private TaskList taskList;
    private Storage storage;
    private boolean isExit = false;

    /**
     * Initialises the tasklist and the storage
     * 
     * @param taskList Arraylist of tasks
     * @param storage Storage class to write to file
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Contains the status of isExit
     * 
     * @return isExit Status of the parseCommands to show whether the user have exited the chatbot
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Receives the command based on the user input and the additional information to perform
     * chatbox task
     * 
     * @param command Command based on the user input
     * @param additionalInformation Additional information that contains the details of the task,
     *        dates and time
     * @return True or False to see whether the user has exited the chatbot
     */
    public boolean parseCommands(Commands command, String additionalInformation) {
        try {
            switch (command) {
            case todo:
                handleTodo(additionalInformation);
                break;
            case deadline:
                handleDeadline(additionalInformation);
                break;
            case event:
                handleEvent(additionalInformation);
                break;
            case list:
                this.taskList.printList();
                break;
            case delete:
                this.taskList.deleteFromList(additionalInformation);
                break;
            case mark:
                handleMarkAndUnmark(additionalInformation, true);
                break;
            case unmark:
                handleMarkAndUnmark(additionalInformation, false);
                break;
            case find:
                handleFind(additionalInformation);
                break;
            case bye:
                handleBye();
                return false;
            default:
                break;
            }
        } catch (WilliamException | IOException e) {
            System.out.println(e.getMessage() + "\n");
        }
        return true;
    }

    /**
     * Handles toDo task, and add toDo into arraylist of tasks
     * 
     * @param additionalInformation
     * @throws WilliamException
     */
    private void handleTodo(String additionalInformation) throws WilliamException {
        AdditionalInfoParser.checkAdditionalDetailEmpty(additionalInformation);
        taskList.addTask(new Todo(additionalInformation));
    }

    /**
     * Handles deadline task, and add deadline into arraylist of tasks
     * 
     * @param additionalInformation
     * @throws WilliamException
     */
    private void handleDeadline(String additionalInformation) throws WilliamException {
        String[] deadlineDetails = AdditionalInfoParser.splitInput(additionalInformation, " /by ");
        DateAndTimeParser.acceptDateAndTime(deadlineDetails[1]);
        taskList.addTask(new Deadline(deadlineDetails[0],
                DateAndTimeParser.convertStringToDate(deadlineDetails[1])));
    }

    /**
     * Handles event task, and add event into arraylist of tasks
     * 
     * @param additionalInformation
     * @throws WilliamException
     */
    private void handleEvent(String additionalInformation) throws WilliamException {
        String[] eventDetails =
                AdditionalInfoParser.splitInput(additionalInformation, " /from ", " /to ");
        DateAndTimeParser.acceptDateAndTime(eventDetails[1]);
        DateAndTimeParser.acceptDateAndTime(eventDetails[2]);
        DateAndTimeParser.checkWhetherToAndFromValid(eventDetails[1], eventDetails[2]);
        taskList.addTask(
                new Event(eventDetails[0], DateAndTimeParser.convertStringToDate(eventDetails[1]),
                        DateAndTimeParser.convertStringToDate(eventDetails[2])));
    }

    /**
     * Handles mark and unmark task
     * 
     * @param additionalInformation
     * @param isMark
     * @throws WilliamException
     */
    private void handleMarkAndUnmark(String additionalInformation, boolean isMark)
            throws WilliamException {
        AdditionalInfoParser.checkAdditionalDetailEmpty(additionalInformation);
        if (isMark == true) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        taskList.markAndUnmark(additionalInformation);
    }

    /**
     * Handles finding task based on the task id
     * 
     * @param additionalInformation
     * @throws WilliamException
     */
    private void handleFind(String additionalInformation) throws WilliamException {
        AdditionalInfoParser.checkAdditionalDetailEmpty(additionalInformation);
        taskList.findTasks(additionalInformation);
    }

    /**
     * Handles the event where user exits the chatbot, and add the updated tasks into file
     * 
     * @throws IOException
     */
    private void handleBye() throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
        storage.writeToFile(taskList.getTasks());
    }
}
