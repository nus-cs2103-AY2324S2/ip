package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
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

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void setUser(String username) {
        user = username;
    }

    public String getUser() {
        return user;
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I am your virtual assistant\n" + "\n" + logo);
        System.out.println("How can I assist you today, " + user + "?");
        showLine();
    }

    public void showGoodbyeMessage() {
        System.out.println("See ya, " + user + "!");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskCount(int taskCount) {
        if (taskCount <= 1) {
            System.out.println("Now you have " + taskCount + " task in the list, " + user + "!");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in the list, " + user + "!");
        }
    }

    public void showFormatError(String format) {
        System.out.println("Sorry, " + user + ", please format it as '" + format + "'.");
    }

    public void showCommandFormats() {
        System.out.println("Sorry, " + user + ", I didn't understand the command...\n\n"
                + "My commands include:\n"
                + "LIST: Display all tasks in your list.\n"
                + "MARK [task number]: Mark a task as completed.\n"
                + "UNMARK [task number]: Mark a task as not completed.\n"
                + "DELETE [task number]: Remove a task from your list.\n"
                + "[task type] [task description]: Add a new task. Task types include TODO, DEADLINE, EVENT.\n"
                + "LIST_ON [yyyy-mm-dd]: List all tasks on a specific date in format yyyy-MM-dd.\n"
                + "DATE / TIME: Show the current date and time."
                + "BYE: Exit the chatbot.");
    }

    public void showCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss").withLocale(Locale.US);
        System.out.println(user + ", the current date and time is: " + now.format(formatter) + "!");
    }

    public void showTaskTypes() {
        System.out.println("Sorry, " + user + ", please format your tasks as [task type] [task description]!\n"
                + "I am only able to handle 3 task types: todo, deadline, and event.");
    }

    public void showNoTaskIndex(int idx) {
        System.out.println("No task numbered " + (idx + 1) + ", " + user + "!");
    }

    public void showDateFormatError() {
        System.out.println("Sorry, " + user + ", please use list_on [yyyy-mm-dd] to list tasks on a specific date.");
    }

    public void showSavingError() {
        System.out.println(user + ", an error occurred while saving tasks!");
    }

    public void showLoadingError(String reason) {
        if (reason == "corrupted") {
            System.out.println(user + ", I found a corrupted line in tasklist file! I'll skip it..!");
        } else if (reason == "file not found") {
            System.out.println(user + ", I could not find the task file... We have an empty task list!");
        } else {
            System.out.println(user + ", I could not read the tasklist file..!");
        }
    }

    public void showErrorCreatingFile() {
        System.out.println("An error occurred while creating the data folder or tasks.txt file!\n"
                + " Killing Ragdoll...");
    }

    public void closeScanner() {
        scanner.close();
    }
}
