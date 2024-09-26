package aurora.ui;

import java.util.ArrayList;
import java.util.Scanner;

import aurora.objects.DoAfter;
import aurora.objects.Task;
import aurora.tasklist.TaskList;

/** The Ui class is used to represents the user interface of the application. */
public class Ui {

    /** Scanner to be used for inputs. */
    private final Scanner scanner;

    private static final String OPENING_MESSAGE = "How are you feeling? " +
            "I'm Aurora, your personal schedule assistant. \n"
            + "What can I do for you?";

    private static final String EXIT_MESSAGE = "Thank you for consulting with me, have a good day. " +
            "Exiting application in 3 seconds.";

    private static final String TASK_LIST_STRING = "These are the tasks in your list:\n";

    private static final String FIND_LIST_STRING = "Here are the matching tasks in your list:\n";

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a String representing the next Command String entered into the scanner.
     *
     * @return Command string entered into the scanner.
     */
    public String nextCommand() {
        return this.scanner.nextLine();
    }

    /** Prints the opening message. */
    public void printOpeningMessage() {
        printALine();
        System.out.println(OPENING_MESSAGE);
        printALine();
    }

    /** Prints a line for separation. */
    public void printALine() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Returns the opening message string.
     *
     * @return OPENING_MESSAGE.
     */
    public static String getOpeningMessage() {
        return OPENING_MESSAGE;
    }


    /**
     * Returns the exit message string.
     *
     * @return EXIT MESSAGE.
     */
    public static String getExitMessage() {
        return EXIT_MESSAGE;
    }


    /**
     * Returns a string representation of the current task list after generating it.
     *
     * @param taskList An ArrayList of Task objects representing the current tasks stored in the application.
     * @return String representation of the current task list
     */
    public String getTaskListString(ArrayList<Task> taskList) {
        StringBuilder message = new StringBuilder();
        message.append(TASK_LIST_STRING);
        for(int i = 0; i < taskList.size(); i++) {
            message.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return message.toString();
    }


    /**
     * Returns a string representation of tasks found based on a search keyword after generating it.
     *
     * @param taskList An ArrayList of Task objects that match the search keyword.
     * @return String representation of the arraylist of found tasks.
     */
    public String getFoundListString(ArrayList<Task> taskList) {
        StringBuilder message = new StringBuilder();
        message.append(FIND_LIST_STRING);
        for(int i = 0; i < taskList.size(); i++) {
            message.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return message.toString();
    }

    /**
     * Returns a string that echoes back the task added to the task list.
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
