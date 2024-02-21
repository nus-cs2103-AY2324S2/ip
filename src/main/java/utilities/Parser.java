package utilities;

import java.io.IOException;

import enums.Commands;
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

    /**
     * Initialises the tasklist and the storage
     *
     * @param taskList Arraylist of tasks
     * @param storage  Storage class to write to file
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Receives the command based on the user input and the additional information to perform
     * chatbox task
     *
     * @param command               Command based on the user input
     * @param additionalInformation Additional information that contains the details of the task,
     *                              dates and time
     * @return Response based on the user input
     */
    public String parseCommands(Commands command, String additionalInformation) {
        String response;
        try {
            switch (command) {
            case TODO:
                response = handleTodo(additionalInformation);
                break;
            case DEADLINE:
                response = handleDeadline(additionalInformation);
                break;
            case EVENT:
                response = handleEvent(additionalInformation);
                break;
            case LIST:
                response = taskList.printList();
                break;
            case SORT:
                response = taskList.printListSortedByPriority();
                break;
            case DELETE:
                response = taskList.deleteFromList(additionalInformation);
                break;
            case MARK:
                response = handleMarkAndUnmark(additionalInformation, true);
                break;
            case UNMARK:
                response = handleMarkAndUnmark(additionalInformation, false);
                break;
            case FIND:
                response = handleFind(additionalInformation);
                break;
            case PRIORITY:
                response = handlePriority(additionalInformation);
                break;
            case BYE:
                response = handleBye();
                break;
            default:
                response = "Unknown command.";
                break;
            }
        } catch (WilliamException | IOException e) {
            response = e.getMessage();
        }
        return response + "\n";
    }

    /**
     * Handles toDo task, and add toDo into arraylist of tasks
     *
     * @param additionalInformation Information to be added as ToDo task
     * @return String that says ToDo task has been added
     * @throws WilliamException
     */
    private String handleTodo(String additionalInformation) throws WilliamException {
        AdditionalInfoParser.checkAdditionalDetailEmpty(additionalInformation);
        Todo newTodo = new Todo(additionalInformation);
        this.taskList.addTask(newTodo);
        return "Added todo: " + newTodo.toString();
    }

    /**
     * Handles deadline task, and add deadline into arraylist of tasks
     *
     * @param additionalInformation Information to be added as Deadline task
     * @return String that says Deadline task has been added
     * @throws WilliamException
     */
    private String handleDeadline(String additionalInformation) throws WilliamException {
        String[] deadlineDetails = AdditionalInfoParser.splitInput(additionalInformation, " /by ");
        DateAndTimeParser.acceptDateAndTime(deadlineDetails[1]);
        Deadline newDeadline = new Deadline(deadlineDetails[0],
                DateAndTimeParser.convertStringToDate(deadlineDetails[1]));
        this.taskList.addTask(newDeadline);
        return "Added deadline: " + newDeadline.toString();
    }

    /**
     * Handles event task, and add event into arraylist of tasks
     *
     * @param additionalInformation Information to be added as Event task
     * @return String that says Event task has been added
     * @throws WilliamException
     */
    private String handleEvent(String additionalInformation) throws WilliamException {
        String[] eventDetails =
                AdditionalInfoParser.splitInput(additionalInformation, " /from ", " /to ");
        DateAndTimeParser.acceptDateAndTime(eventDetails[1]);
        DateAndTimeParser.acceptDateAndTime(eventDetails[2]);
        DateAndTimeParser.checkWhetherToAndFromValid(eventDetails[1], eventDetails[2]);
        Event newEvent =
                new Event(eventDetails[0], DateAndTimeParser.convertStringToDate(eventDetails[1]),
                        DateAndTimeParser.convertStringToDate(eventDetails[2]));
        this.taskList.addTask(newEvent);
        return "Added event: " + newEvent.toString();
    }

    /**
     * Handles mark and unmark task
     *
     * @param additionalInformation Information of the task that is being selected to be mark/unmark
     * @param isMark                Either mark which is true, or unmark which is false
     * @return String that says whether the task has been mark or unmark
     * @throws WilliamException
     */
    private String handleMarkAndUnmark(String additionalInformation, boolean isMark)
            throws WilliamException {
        AdditionalInfoParser.checkAdditionalDetailEmpty(additionalInformation);
        boolean isMarkChanged = this.taskList.markAndUnmark(additionalInformation, isMark);
        if (isMarkChanged == false) {
            return "It has already been marked " + (isMark ? "done" : "not done") + ".";
        }
        if (isMark) {
            return "Nice! I've marked this task as done.";
        } else {
            return "OK, I've marked this task as not done yet.";
        }
    }

    /**
     * Handles finding task based on the task id
     *
     * @param additionalInformation Information of the task that is being selected to be find
     * @return Strings of task that has been found
     * @throws WilliamException
     */
    private String handleFind(String additionalInformation) throws WilliamException {
        return this.taskList.findTasks(additionalInformation);
    }

    /**
     * Handles the event where user exits the chatbot, and add the updated tasks into file
     *
     * @return Strings that says the user has exited the chatbot
     * @throws IOException
     */
    private String handleBye() throws IOException {
        this.storage.writeToFile(this.taskList.getTasks());
        System.exit(0);
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles prioritising task
     *
     * @param additionalInformation Information of the task to be prioritised
     * @return String that says that whether the task has been prioritised
     */
    private String handlePriority(String additionalInformation) throws WilliamException {
        String[] priorityDetails =
                AdditionalInfoParser.splitInput(additionalInformation, "/id ", "/priority ");
        return this.taskList.prioritiseTask(priorityDetails[1], priorityDetails[2]);
    }
}
