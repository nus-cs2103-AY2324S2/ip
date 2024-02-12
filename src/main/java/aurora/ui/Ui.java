package aurora.ui;

import java.util.ArrayList;
import java.util.Scanner;

import aurora.objects.Task;
import aurora.tasklist.TaskList;

/**
 * The Ui class is used to represent the user interface, including the scanner of the application.
 */
@SuppressWarnings("checkstyle:Indentation")
public class Ui {

    /** Scanner to be used for inputs. */
    private final Scanner scanner;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Method that returns the command entered.
     *
     * @return Command string entered into the scanner.
     */
    public String nextCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Method to print the greeting message.
     */
    public void printOpeningMessage() {
        printALine();
        String openingMessage = "How are you feeling? I'm Aurora, your personal schedule assistant. \n"
                + "What can I do for you?";
        System.out.println(openingMessage);
        printALine();
    }

    /**
     * Method to print a line for separation.
     */
    public void printALine() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Generates and returns the opening message for the application.
     *
     * @return A string containing the opening message.
     */
    public String getOpeningMessage() {
        StringBuilder message = new StringBuilder();
        message.append("How are you feeling? I'm Aurora, your personal schedule assistant. \n")
                .append("What can I do for you?");
        return message.toString();
    }


    /**
     * Generates and returns the exit message for the application.
     *
     * @return A string containing the exit message.
     */
    public String getExitMessage() {
        return "Thank you for consulting with me, have a good day. Exiting application in 3 seconds.";
    }


    /**
     * Generates and returns a string representation of the current task list.
     *
     * @param taskList An ArrayList of Task objects representing the current tasks.
     * @return A string representation of the task list.
     */
    public String getTaskListString(ArrayList<Task> taskList) {
        StringBuilder message = new StringBuilder();
        message.append("These are the tasks in your list:\n");
        for(int i = 0; i < taskList.size(); i++) {
            message.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return message.toString();
    }


    /**
     * Generates and returns a string representation of tasks found based on a search keyword.
     *
     * @param taskList An ArrayList of Task objects that match the search criteria.
     * @return A string representation of the found tasks.
     */
    public String getFoundListString(ArrayList<Task> taskList) {
        StringBuilder message = new StringBuilder();
        message.append("Here are the matching tasks in your list:\n");
        for(int i = 0; i < taskList.size(); i++) {
            message.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return message.toString();
    }

    /**
     * Generates and returns a string that echoes back the task added to the task list.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @return A string echoing the added task and the current number of tasks.
     */
    public String getEchoAddTaskString(TaskList taskList) {
        ArrayList<Task> taskListArray = taskList.getTaskList();
        int taskNumber = taskListArray.size();
        StringBuilder message = new StringBuilder();
        message.append("Added this task: \n")
                .append(taskListArray.get(taskNumber - 1))
                .append("\nNumber of tasks in list: ")
                .append(taskNumber);
        return message.toString();
    }
}
