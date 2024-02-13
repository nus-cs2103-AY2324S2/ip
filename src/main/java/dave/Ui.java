package dave;

import java.util.Scanner;

import dave.tasks.Task;

public class Ui {
    /** The user input scanner. */
    private Scanner sc;

    /** Name of the chatbot, Dave. */
    static final String NAME = "Dave";
    /** Horizontal line divider for output. */
    static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    /** 
     * Creates new UI object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Shows the horizontal line divider in output.
     */
    public void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Welcomes the user with a message.
     */
    public void sayHello() {
        System.out.println(String.format("%s\n%s: Nice to meet you, I'm the ever-helpful %s!\nHow may I be of service today?\n%s",
        HORIZONTAL_LINE, NAME, NAME, HORIZONTAL_LINE));
        return;
    }

    /**
     * Says bye to the user with a message.
     */
    public void sayBye() {
        System.out.println(String.format("%s\nDave: Thank you for your patronage, goodbye and have a nice day!\n%s",
        HORIZONTAL_LINE, HORIZONTAL_LINE));
        return;
    }

    /**
     * Shows that tasks from an existing task list have been loaded in the chatbot program, if any.
     */
    public void showLoadingSuccess(int noOfTasks) {
        System.out.println(String.format("Dave has found %s existing tasks and loaded them.\n%s", noOfTasks, HORIZONTAL_LINE));
    }

    /**
     * Shows that there is no existing task list to load tasks from.
     */
    public void showLoadingError() {
        System.out.println(String.format("Dave did not find any existing tasks to load.\n%s", HORIZONTAL_LINE));
    }

    /**
     * Shows error from any user-chatbot interaction.
     * 
     * @param msg The error output.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Reads user input.
     * 
     * @return The user input to the chatbot program.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Shows user feedback when a new task is successfully added into the task list.
     * 
     * @param newTask Information about the new task added.
     * @param taskList Current task list.
     */
    public void showTaskAdded(Task newTask, TaskList taskList) {
        System.out.println(String.format("%s\nDave added the task:\n  %s\nYou now have %d task(s).\n%s",
        HORIZONTAL_LINE, newTask.toString(), taskList.getNumberOfTasks(), HORIZONTAL_LINE));
    }

    /**
     * Shows user feedback when a task is successfully deleted from the task list.
     * 
     * @param deletedTask Information about the deleted task.
     * @param taskList Current task list.
     */
    public void showTaskDeleted(Task deletedTask, TaskList taskList) {
        System.out.println(String.format("%s\nDave has removed the task:\n  %s", HORIZONTAL_LINE,
            deletedTask.toString()));
        System.out.println(String.format("\nYou now have %d task(s).\n%s", taskList.getNumberOfTasks(),
            HORIZONTAL_LINE));
    }

    /**
     * Shows user feedback when a task is successfully marked as done.
     * 
     * @param markedTask The task marked as done.
     */
    public void showTaskMarked(Task markedTask) {
        System.out.println(String.format("%s\nGood job! Dave will mark this task as done:\n  %s\n%s",
        HORIZONTAL_LINE, markedTask.toString(), HORIZONTAL_LINE));    
    }

    /**
     * Shows user feedback when a task is successfully unmarked as not done.
     * 
     * @param unmarkedTask The task unmarked as not done.
     */
    public void showTaskUnmarked(Task unmarkedTask) {
        System.out.println(String.format("%s\nAlright, Dave believes you'll get this done eventually:\n  %s\n%s",
        HORIZONTAL_LINE, unmarkedTask.toString(), HORIZONTAL_LINE));

    }
}
