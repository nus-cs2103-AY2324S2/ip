package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Ui class handles user interaction and provides methods for displaying messages and reading input.
 */
public class Ui {
    private String user = "Master";
    private String logo = " /$$$$$$$                            /$$           /$$ /$$\n"
            + "| $$__  $$                          | $$          | $$| $$\n"
            + "| $$  \\ $$  /$$$$$$   /$$$$$$   /$$$$$$$  /$$$$$$ | $$| $$\n"
            + "| $$$$$$$/ |____  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$| $$\n"
            + "| $$__  $$  /$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$| $$| $$\n"
            + "| $$  \\ $$ /$$__  $$| $$  | $$| $$  | $$| $$  | $$| $$| $$\n"
            + "| $$  | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$/| $$| $$\n"
            + "|__/  |__/ \\_______/ \\____  $$ \\_______/ \\______/ |__/|__/\n"
            + "                     /$$  \\ $$\n"
            + "                    |  $$$$$$/\n"
            + "                     \\______/\n";

    /**
     * Constructs an Ui instance.
     */
    public Ui() {
    }

    /**
     * Sets the username for the Ui instance.
     *
     * @param username The username to set.
     */
    public void setUser(String username) {
        user = username;
    }

    /**
     * Gets the current username.
     *
     * @return The current username.
     */
    public String getUser() {
        return user;
    }

    /*
     * Reads a user command from the console input.
     *
     * @return The user-entered command as a String.
     *
    public String readCommand(String input) {
        return input;
    }
    */

    /**
     * Displays a welcome message and the application logo.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I am your virtual assistant\n" + "\n" + logo);
        System.out.println("How can I assist you today, " + user + "?");
        showLine();
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println("See ya, " + user + "!");
    }

    /**
     * Displays a horizontal line as a separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a warning message.
     */
    public void showWarning() {
        System.out.println("\n");
        System.out.println("!!! ATTENTION !!!");
    }

    /**
     * Displays a generic message.
     *
     * @param messages The message to display.
     */
    public void showMessage(String... messages) {
        for (String message: messages) {
            System.out.println(message);
        }
    }

    /**
     * Displays the number of tasks in the list.
     *
     * @param taskCount The number of tasks in the list.
     */
    public void showTaskCount(int taskCount) {
        if (taskCount <= 1) {
            System.out.println("Now you have " + taskCount + " task in the list, " + user + "!");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in the list, " + user + "!");
        }
    }

    /**
     * Displays a format error message.
     *
     * @param format The expected format.
     */
    public void showFormatError(String format) {
        System.out.println("Sorry, " + user + ", please format it as '" + format + "'.");
    }

    /**
     * Displays a list of available command formats.
     */
    public void showCommandFormats() {
        System.out.println("Sorry, " + user + ", I didn't understand the command...\n\n"
                + "My commands include:\n"
                + "LIST: Display all tasks in your list.\n"
                + "MARK [task number]: Mark a task as completed.\n"
                + "UNMARK [task number]: Mark a task as not completed.\n"
                + "DELETE [task number]: Remove a task from your list.\n"
                + "[task type] [task description]: Add a new task. Task types include TODO, DEADLINE, EVENT.\n"
                + "FIND [yyyy-mm-dd] / FIND [key word]: Search for all relevant tasks.\n"
                + "DATE / TIME: Show the current date and time.\n"
                + "TAG: Tag the task specified.\n"
                + "BYE: Exit the chatbot.");
    }

    /**
     * Displays the current date and time.
     */
    public void showCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss").withLocale(Locale.US);
        System.out.println(user + ", the current date and time is: " + now.format(formatter) + "!");
    }

    /**
     * Displays a message about supported task types.
     */
    public void showTaskTypes() {
        System.out.println("Sorry, " + user + ", please format your tasks as [task type] [task description]!\n"
                + "I am only able to handle 3 task types: todo, deadline, and event.");
    }

    /**
     * Displays a message for when a task index is not found.
     *
     * @param idx The task index.
     */
    public void showNoTaskIndex(int idx) {
        System.out.println("No task numbered " + (idx + 1) + ", " + user + "!");
    }



    /**
     * Displays an error message to the user when they input an invalid format for the 'find' command.
     */
    public void showFindFormatError() {
        System.out.println("Sorry, " + user + ", please use 'find [yyyy-mm-dd]' / 'find [key word]' "
                + "to search for relevant tasks");
    }

    /**
     * Displays an error message for saving tasks.
     */
    public void showSavingError() {
        System.out.println(user + ", an error occurred while saving tasks!");
    }

    /**
     * Displays an error message for loading tasks.
     *
     * @param reason The reason for loading error.
     */
    public void showLoadingError(String reason) {
        showWarning();

        if (reason == "corrupted") {
            System.out.println(user + ", I found a corrupted line in tasklist file! I'll skip it..!");
        } else if (reason == "file not found") {
            System.out.println(user + ", I could not find the task file... We have an empty task list!");
        } else {
            System.out.println(user + ", I could not read the tasklist file..!");
        }

        showLine();
    }

    /**
     * Displays an error message for creating a file.
     */
    public void showErrorCreatingFile() {
        System.out.println("An error occurred while creating the data folder or tasks.txt file!\n"
                + " Killing Ragdoll...");
    }
}
