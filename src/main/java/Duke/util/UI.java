package duke.util;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * The UI class handles interactions with the user, including displaying messages and receiving input.
 */
public class UI {
    private static final String[] token = new String[]{
        "Hello! I'm chinesepoliceman",
        "What can I do for you?",
        " Bye. Hope to see you again soon!"
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
        result.append(String.format("%s\n%s\n\nHere are the list of Commands:\n", token[0], token[1]));
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
        return String.format("%s\n", token[2]);
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

    /**
     * Sets the user's name.
     * @param userName The user's name.
     */
    public void setName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user's name.
     * @return The user's name.
     */
    public String getName() {
        String name = this.userName == null
                ? "User"
                : this.userName;
        return name;
    }

    /**
     * Generates a message for the user's name.
     * @return The generated message.
     */
    public String nameMessage() {
        return String.format("Hello!!! Nice to meet you %s!!!\n", this.userName);
    }
}

