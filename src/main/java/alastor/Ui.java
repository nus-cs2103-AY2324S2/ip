package alastor;

import java.util.Scanner;

import alastor.task.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    protected static boolean dirCreated = false;
    protected static boolean fileCreated = false;
    protected Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Shows the greeting message.
     */
    public String showGreet() {
        String temp = "Hello there! Alastor speaking. What devilish deeds can I assist you with today.\n";
        if (dirCreated) {
            temp += "Couldn't locate the directory, so I took the liberty of crafting one for you.\n";
            dirCreated = false;
        }
        if (fileCreated) {
            temp += "Couldn't locate the file, so I took the liberty of crafting one for you.\n";
            fileCreated = false;
        }
        return temp;
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public String showList(TaskList tasks) {
        String temp = "Behold! Here unfurls the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            temp += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return temp;
    }

    /**
     * Shows the tasks that match the keyword.
     *
     * @param tasks The list of tasks that match the keyword.
     */
    public String showFind(TaskList tasks) {
        if (tasks.size() == 0) {
            return "I'm afraid I couldn't find any tasks that match your keyword.\n";
        }
        String temp = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            temp += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return temp;
    }

    /**
     * Shows the mark of the task.
     *
     * @param task The task to be marked.
     * @param isMark The boolean value of whether the task is marked.
     */
    public String showMark(Task task, boolean isMark) {
        if (isMark) {
            return "Well, isn't this delightful! I've marked this task as done:\n"
                    + "  " + task.toString() + "\n";
        } else {
            return "Very well! I've noted this task as yet untouched:\n"
                    + "  " + task.toString() + "\n";
        }
    }

    /**
     * Shows the add of the task.
     *
     * @param task The task to be added.
     * @param tasks The list of tasks.
     */
    public String showAdd(Task task, TaskList tasks) {
        return "Marvelous! Another task graces our repertoire:\n"
                + "  " + task.toString() + "\n"
                + "And with this latest addition, our list of tasks swells to a delightful " + tasks.size() + ".\n";
    }

    /**
     * Shows the deletion of the task.
     *
     * @param task The task to be deleted.
     */
    public String showDelete(Task task) {
        return "Very well! I've removed this task from our list:\n"
                + "  " + task.toString() + "\n";
    }

    /**
     * Shows the exit message.
     */
    public String showExit() {
        return "Farewell! Should you ever require my services again, simply summon me.\n";
    }

    /**
     * * Shows the help message.
     */
    public String showHelp() {
        return "Here are the commands you can use:\n"
                + "1. todo <description> - Adds a todo task to the list.\n"
                + "2. deadline <description> /by <date> - Adds a deadline task to the list.\n"
                + "3. event <description> /from <date> /to <date>  - Adds an event task to the list.\n"
                + "4. list - Lists all the tasks in the list.\n"
                + "5. mark <index> - Marks the task at the specified index as done.\n"
                + "6. unmark <index> - Marks the task at the specified index as undone.\n"
                + "7. delete <index> - Deletes the task at the specified index.\n"
                + "8. find <keyword> - Finds tasks that match the keyword.\n"
                + "9. help - Shows the list of commands.\n"
                + "10. bye - Exits the program.\n";
    }
}
