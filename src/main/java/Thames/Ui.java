package Thames;

import java.util.Scanner;
import Thames.task.Task;

/** Class that deals with input from and output to user. */
public class Ui {
    /** Scanner to deal with user inputs */
    Scanner sc;
    public Ui(){
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message to user.
     */
    public String greet() {
        String greeting = "Hello! I'm Thames and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n";
        return greeting;
    }

    /**
     * Prints exit message to user.
     */
    public String bye() {
        this.sc.close();
        return "Bye. Hope to see you soon!\n";
    }

    /**
     * Prints message notifying user that the task has been marked as done.
     *
     * @param task Task that was marked as done.
     */
    public String mark(Task task) {
        return "Nice! I've marked this task as done:\n " + task + "\n";
    }

    /**
     * Prints message notifying user that the task has been unmarked as done.
     *
     * @param task Task that was unmarked as done.
     */
    public String unmark(Task task) {
        return "Ok, I've marked this task as not done yet:\n " + task + "\n";
    }

    /**
     * Prints message notifying user that the task has been deleted from task list.
     *
     * @param task Task to be deleted from task list.
     * @param listSize Size of task list.
     */
    public String delete(Task task, int listSize) {
        return "Noted. I've removed this task from the list:\n " + task +
                "\nNow you have " + listSize + " tasks in the list.\n";
    }

    /**
     * Prints message notifying that the task has been added to task list.
     *
     * @param task Task that was added to task list.
     * @param listSize Size of task list.
     */
    public String addTask(Task task, int listSize) {
        return " added: " +
                task + "\n Now you have " + listSize + " tasks in the list\n";
    }

    /**
     * Prints content of task list to user.
     *
     * @param taskList Task list to be printed.
     */
    public String showList(TaskList taskList) {
        String list = "";

        for (int i = 0; i < taskList.size(); i++) {
            list += i + 1 + "." + taskList.get(i) + "\n";
        }
        return list;
    }



    /**
     * Outputs error message when saved task list cannot be loaded or when no task list has been created before.
     */
    public String showLoadingError() {
        return "It seems like you do not have a saved task list. I will be creating an empty one for you.";
    }

    /**
     * Output error message.
     *
     * @param s Error message.
     */
    public String showError(String s) {
        return s + "\n";
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
