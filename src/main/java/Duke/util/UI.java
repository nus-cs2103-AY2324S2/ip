package duke.util;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * The UI class handles interactions with the user, including displaying messages and receiving input.
 */
public class UI {
    private static final String hRULER = "____________________________________________________________\n";
    private static final String[] token = new String[]{
        "____________________________________________________________",
        "Hello! I'm chinesepoliceman",
        "What can I do for you?",
        "____________________________________________________________",
        " Bye. Hope to see you again soon!",
        "____________________________________________________________"
    };
    private static final String[] listOfCommands = new String[]{
        "  1. bye (exit program)\n",
        "  2. date DD/MM/YYYY HHmm (search relevant task with timeStamp)\n",
        "  3. deadline [task description] /by dd/mm/yyyy HHmm\n",
        "  4. delete [integer]\n",
        "  5. event [task description] /from dd/mm/yyyy HHmm /to dd/mm/yyyy HHmm\n",
        "  6. find [task description] (search for relevant task with desciption\n",
        "  7. list (show current tasks)\n",
        "  8. mark [integer] (mark task at index to be done)\n",
        "  9. todo [task description]\n",
        " 10. unmark [integer]\n",
        " 11. name [your name]\n"
    };
    private Scanner sc = new Scanner(System.in);

    private String userName;

    /**
     * Prompts the user for input and returns the next line of input.
     * @return The next line of input from the user.
     */
    public String receiveNextLine() {
        System.out.print("Command: ");
        return this.sc.nextLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     * @param t The task that has been marked as done.
     */
    public void displayMark(Task t) {
        System.out.printf("%s Nice! I've marked this task as done:\n   %s\n%s",
                hRULER, t.toString(), hRULER);
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     * @param t The task that has been unmarked.
     */
    public void displayUnMark(Task t) {
        System.out.printf("%s OK, I've marked this task as not done yet:\n   %s\n%s",
                hRULER, t.toString(), hRULER);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     * @param t The task that has been deleted.
     * @param currentIdx The current index of tasks in the list.
     */
    public void displayDelete(Task t, int currentIdx) {
        System.out.printf("%s Noted. I've removed this task:\n   %s\n Now you have %d tasks in the list.\n%s",
                hRULER, t, currentIdx, hRULER);
    }

    /**
     * Displays a message indicating that a task has been added.
     * @param t The task that has been added.
     * @param currentIdx The current index of tasks in the list.
     */
    public void displayAdd(Task t, int currentIdx) {
        System.out.printf("%s Got it. I've added this task:\n "
                + "%s\n Now you have %d tasks in the list.\n%s", hRULER, t, currentIdx, hRULER);
    }

    /**
     * Displays the list of tasks.
     * @param l The list of tasks to be displayed.
     */
    public void displayList(ArrayList<Task> l) {
        System.out.printf("%s Here are the tasks in your list:\n", hRULER);
        for (int i = 0; i < l.size(); i++) {
            System.out.printf(" %d.%s\n", i + 1, l.get(i).toString());
        }
        System.out.println(hRULER);
    }

    /**
     * Displays the introduction message.
     */
    public void displayIntro() {
        for (int i = 0; i < 4; i++) {
            System.out.println(token[i]);
        }
    }

    /**
     * Displays the exit message.
     */
    public void displayExit() {
        for (int i = 3; i < 6; i++) {
            System.out.println(token[i]);
        }
    }

    /**
     * Displays an exception message.
     * @param e The exception to be displayed.
     */
    public void displayExceptionMsg(DukeException e) {
        System.out.printf("%s%s%s", hRULER, e, hRULER);
    }

    /**
     * Displays a list of tasks with specified dates.
     * @param l The list of tasks with specified dates.
     */
    public void displayFoundList(ArrayList<Task> l) {
        System.out.printf("%s Here are the deadlines/events with specified date in your list:\n", hRULER);
        for (int i = 0; i < l.size(); i++) {
            System.out.printf(" %d.%s\n", i + 1, l.get(i).toString());
        }
        System.out.println(hRULER);
    }

    /**
     * Displays a list of tasks with specified words.
     * @param l The list of tasks with specified words.
     */
    public void displayFoundTask(ArrayList<Task> l) {
        System.out.printf("%s Here are the deadlines/events with specified word in your list:\n", hRULER);
        for (int i = 0; i < l.size(); i++) {
            System.out.printf(" %d.%s\n", i + 1, l.get(i).toString());
        }
        System.out.println(hRULER);
    }

    public void displayNameMessage() {
        System.out.printf("Hello!! Nice to meet you %s!!!\n", this.userName);
    }

    /**
     * Generates a message indicating that a task has been marked as done.
     * @param t The task that has been marked as done.
     * @return The generated message.
     */
    public String markMessage(Task t) {
        return String.format("Nice! I've marked this task as done:\n%s\n", t.toString());
    }
    /**
     * Generates a message indicating that a task has been marked as not done yet.
     * @param t The task that has been marked as not done yet.
     * @return The generated message.
     */
    public String unMarkMessage(Task t) {
        return String.format("OK, I've marked this task as not done yet:\n%s\n", t.toString());
    }

    /**
     * Generates a message indicating that a task has been deleted.
     * @param t The task that has been deleted.
     * @param currentIdx The current index of tasks in the list.
     * @return The generated message.
     */
    public String deleteMessage(Task t, int currentIdx) {
        return String.format("Noted. I've removed this task:\n"
                + "%s\nNow you have %d tasks in the list.\n", t, currentIdx);
    }

    /**
     * Generates a message indicating that a task has been added.
     * @param t The task that has been added.
     * @param currentIdx The current index of tasks in the list.
     * @return The generated message.
     */
    public String addTaskMessage(Task t, int currentIdx) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", t, currentIdx);
    }

    /**
     * Generates a message displaying the list of tasks.
     * @param l The list of tasks to be displayed.
     * @return The generated message.
     */
    public String displayListMessage(ArrayList<Task> l) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list:\n");
        for (int i = 0; i < l.size(); i++) {
            result.append(String.format(" %d.%s\n", i + 1, l.get(i).toString()));
        }
        return result.toString();
    }

    /**
     * Generates a message for the introduction.
     * @return The generated introduction message.
     */
    public String introMessage() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s\n%s\n\nHere are the list of Commands:\n", token[1], token[2]));
        for (int i = 0; i < listOfCommands.length; i++) {
            result.append(listOfCommands[i]);
        }
        return result.toString();
    }

    /**
     * Generates a message for the exit.
     * @return The generated exit message.
     */
    public String exitMessage() {
        return String.format("%s\n", token[4]);
    }

    /**
     * Generates a message for exceptions.
     * @param e The exception.
     * @return The generated exception message.
     */
    public String exceptionMsg(DukeException e) {
        return String.format("%s", e.toString());
    }

    /**
     * Generates a message displaying a list of tasks with specified dates.
     * @param l The list of tasks with specified dates.
     * @return The generated message.
     */
    public String foundListMessage(ArrayList<Task> l) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the deadlines/events with specified date in your list:\n");
        for (int i = 0; i < l.size(); i++) {
            result.append(String.format("%d.%s\n", i + 1, l.get(i).toString()));
        }
        return result.toString();
    }

    /**
     * Generates a message displaying a list of tasks with specified words.
     * @param l The list of tasks with specified words.
     * @return The generated message.
     */
    public String foundTaskMessage(ArrayList<Task> l) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the deadlines/events with specified word in your list:\n");
        for (int i = 0; i < l.size(); i++) {
            result.append(String.format("%d.%s\n", i + 1, l.get(i).toString()));
        }
        return result.toString();
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        String name = this.userName == null
                ? "User"
                : this.userName;
        return name;
    }
    public String nameMessage() {
        return String.format("Hello!!! Nice to meet you %s!!!\n", this.userName);
    }
}
