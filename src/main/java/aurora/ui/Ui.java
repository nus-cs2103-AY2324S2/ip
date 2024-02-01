package aurora.ui;

import java.util.ArrayList;
import java.util.Scanner;
import aurora.objects.Deadline;
import aurora.objects.DukeException;
import aurora.objects.Event;
import aurora.objects.Task;
import aurora.objects.Todo;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;

/**
 * The Ui class is used to represent the user interface, including the scanner of the application.
 */
public class Ui {

    /** Scanner to be used for inputs. */
    private final Scanner scanner;

    /**
     * Constructor for the Ui class
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
     * Method to print the exit message.
     */
    public void printExitMessage() {
        printALine();
        String exitMessage = "Thank you for consulting with me, have a good day.";
        System.out.println(exitMessage);
        printALine();
    }

    /**
     * Method to print a task list.
     *
     * @param  taskList The taskList to be printed.
     */
    public void printTaskList(ArrayList<Task> taskList) {
        printALine();
        System.out.println("These are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            String taskString = (i+1) + ". " + taskList.get(i).toString();
            System.out.println(taskString);
        }
        printALine();
    }

    /**
     * Method to print a found task list.
     *
     * @param  taskList The taskList to be printed.
     */
    public void printFoundList(ArrayList<Task> taskList) {
        printALine();
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            String taskString = (i+1) + ". " + taskList.get(i).toString();
            System.out.println(taskString);
        }
        printALine();
    }

    /**
     * Method to echo an add command.
     */
    public void echoAddTask(TaskList taskList) {
        ArrayList<Task> taskListArray = taskList.getTaskList();
        int taskNumber = taskListArray.size();
        printALine();
        String echo =
                "Added this task: \n" + taskListArray.get(taskNumber - 1) + "\nNumber of tasks in list: " + taskNumber;
        System.out.println(echo);
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
}
