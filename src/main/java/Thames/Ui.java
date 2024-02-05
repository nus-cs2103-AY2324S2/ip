package Thames;

import java.util.Scanner;
import Thames.task.Task;

/** Class that deals with input from and output to user. */
public class Ui {
    final String LINE = "_______________________________________________________\n";

    /** Scanner to deal with user inputs */
    Scanner sc;
    public Ui(){
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message to user.
     */
    public void greet() {
        System.out.println(LINE +
                "Hello! I'm Thames and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n" +
                LINE);
    }

    /**
     * Prints exit message to user.
     */
    public void bye() {
        this.sc.close();
        System.out.println(LINE + "Bye. Hope to see you soon!\n" + LINE);
    }

    /**
     * Prints message notifying user that the task has been marked as done.
     *
     * @param task Task that was marked as done.
     */
    public void mark(Task task) {
        System.out.println(LINE + "Nice! I've marked this task as done:\n " + task + "\n" + LINE);
    }

    /**
     * Prints message notifying user that the task has been unmarked as done.
     *
     * @param task Task that was unmarked as done.
     */
    public void unmark(Task task) {
        System.out.println(LINE + "Ok, I've marked this task as not done yet:\n " + task + "\n" + LINE);
    }

    /**
     * Prints message notifying user that the task has been deleted from task list.
     *
     * @param task Task to be deleted from task list.
     * @param listSize Size of task list.
     */
    public void delete(Task task, int listSize) {
        System.out.println(LINE +
                "Noted. I've removed this task from the list:\n " + task +
                "\nNow you have " + listSize + " tasks in the list.\n" +
                LINE);
    }

    /**
     * Prints message notifying that the task has been added to task list.
     *
     * @param task Task that was added to task list.
     * @param listSize Size of task list.
     */
    public void addTask(Task task, int listSize) {
        System.out.println(LINE + " added: " +
                task + "\n Now you have " + listSize + " tasks in the list\n" +
                LINE);
    }

    /**
     * Prints content of task list to user.
     *
     * @param taskList Task list to be printed.
     */
    public void showList(TaskList taskList) {
        System.out.println(LINE);

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }

        System.out.println(LINE);
    }

    /**
     * Outputs error message when saved task list cannot be loaded or when no task list has been created before.
     */
    public void showLoadingError() {
        System.out.println("It seems like you do not have a saved task list. I will be creating an empty one for you.");
    }

    /**
     * Output error message.
     *
     * @param s Error message.
     */
    public void showError(String s) {
        System.out.println(LINE + s + "\n" + LINE);
    }

    /**
     * Reads next input from user.
     *
     * @return Returns String of input read from user.
     */
    public String readCommand() {
        return sc.nextLine();
    }



}
