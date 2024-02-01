package utilities;

import java.io.IOException;

import commands.Commands;
import exceptions.WilliamException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 * The Parser class deals with making sense of the user command
 */
public class Parser {

    private TaskList taskList;
    private Storage storage;
    private boolean isExit = false;

    /**
     * Constructor to initialise the tasklist and the storage
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
     * Receive the command based on the user input and the additional information to perform chatbox
     * task
     * 
     * @param command Command based on the user input
     * @param additonalInformation Additional information that contains the details of the task,
     *        dates and time
     * @return True or False to see whether the user has exited the chatbot
     */
    public boolean parseCommands(Commands command, String additonalInformation) {
        switch (command) {
            case todo:
                try {
                    AdditionalInfoParser.checkAdditionalDetailEmpty(additonalInformation);
                    this.taskList.addTask(new Todo(additonalInformation));
                } catch (WilliamException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                break;
            case deadline:
                try {
                    String[] deadlineDetails = AdditionalInfoParser.splitBy(additonalInformation);
                    this.taskList.addTask(new Deadline(deadlineDetails[0],
                            DateAndTimeParser.convertStringToDate(deadlineDetails[1])));
                } catch (WilliamException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                break;
            case event:
                try {
                    String[] eventDetails =
                            AdditionalInfoParser.splitToAndFrom(additonalInformation);
                    this.taskList.addTask(new Event(eventDetails[0],
                            DateAndTimeParser.convertStringToDate(eventDetails[1]),
                            DateAndTimeParser.convertStringToDate(eventDetails[2])));
                } catch (WilliamException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                break;
            case list:
                this.taskList.printList();
                break;
            case delete:
                this.taskList.deleteFromList(additonalInformation);
                break;
            case mark:
                System.out.println("Nice! I've marked this task as done:");
                this.taskList.markAndUnmark(additonalInformation);
                break;
            case unmark:
                System.out.println("OK, I've marked this task as not done yet:");
                this.taskList.markAndUnmark(additonalInformation);
                break;
            case find:
                try {
                    this.taskList.findTasks(additonalInformation);
                } catch (WilliamException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                break;
            case bye:
                System.out.println("Bye. Hope to see you again soon!");
                try {
                    this.storage.writeToFile(this.taskList.getTasks());
                } catch (IOException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                return false;
            default:
                break;
        }
        return true;
    }

}
