package alpa.ui;

import alpa.tasks.Task;
import java.util.Scanner;
import java.util.List;

/**
 * The Ui class represents the user interface of the application.
 * It provides methods to display messages, read user input, and handle errors.
 */
public class Ui {
    private Scanner scanner;
    
    /**
     * Constructs a new Ui object.
     * Initializes the scanner to read user input from the console.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message along with an ASCII art of an Alpaca.
     * The Alpaca art is scaled down by half for better visibility.
     */
    public void showWelcome() {
        String logo = 
                "     _    _             \n"
                + "    / \\  | |_ __   __ _ \n"
                + "   / _ \\ | | '_ \\ / _` |\n"
                + "  / ___ \\| | |_) | (_| |\n"
                + " /_/   \\_\\_| .__/ \\__,_|\n"
                + "           |_|          ";
        String[] art = {
                "⠀⠀⠀⠀⡾⣦⡀⠀⠀⡀⠀⣰⢷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⠀⠀⣠⠗⠛⠽⠛⠋⠉⢳⡃⢨⢧⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⣰⠋⠁⠀⠀⠀⠀⠀⠀⠙⠛⢾⡈⡏⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⣼⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠸⢦⡀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⢈⠟⠓⠶⠞⠒⢻⣿⡏⢳⡀⠀⠀⠀⠀⢸⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⡴⢉⠀⠀⠀⠀⠀⠈⠛⢁⣸⠇⠀⠀⠀⠀⢺⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⢧⣸⡁⠀⠀⣀⠀⠀⣠⠾⠀⠀⠀⠀⠀⠀⣹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⠉⠓⢲⠾⣍⣀⣀⡿⠃⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⠀⠀⠀⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⠀⠀⠀⣏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⠀⠀⢀⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
                "⠀⠀⠀⣸⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠺⠦⠤⠤⣤⣄⣀⣀⡀⠀⠀⠀⠀⠀",
                "⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠳⣦⣄⠀⠀",
                "⠀⠀⢀⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣆⠀",
                "⠀⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣆",
                "⠀⠀⣏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿",
                "⠀⠀⢹⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼",
                "⠀⠀⠀⣏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞",
                "⠀⠀⠀⠈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇",
                "⠀⠀⠀⠀⠈⢻⣦⣀⠀⣏⠀⠀⠀⠀⠀⠀⢸⡆⠀⠀⢠⡄⠀⠀⠀⠀⠀⢀⡿⠀",
                "⠀⠀⠀⠀⠀⠀⠻⡉⠙⢻⡆⠀⠀⠀⠀⠀⡾⠚⠓⣖⠛⣧⡀⠀⠀⠀⢀⡾⠁⠀",
                "⠀⠀⠀⠀⠀⠀⠀⠙⡇⢀⡿⣦⡀⠀⢀⡴⠃⠀⠀⠈⣷⢈⠷⡆⠀⣴⠛⠀⠀⠀",
                "⠀⠀⠀⠀⠀⠀⠀⠀⠛⠚⠀⢸⡇⣰⠏⠁⠀⠀⠀⠀⢉⠁⢸⠷⠼⠃⠀⠀⠀⠀"
            };
        // Image scaled down by half
        StringBuilder scaledArt = new StringBuilder();
        for (int i = 0; i < art.length; i += 2) {
            for (int j = 0; j < art[i].length(); j += 2) {
                scaledArt.append(art[i].charAt(j)); 
            }   
            scaledArt.append("\n");
        }
        System.out.println("Hello Human! I am your fluffy assistant, \n" + logo + "\n the Alpaca! \n" + scaledArt);
        System.out.println("I'm here to help you sort through the woolly world of tasks.\n");
        System.out.println("෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴⚘෴෴⚘෴\n");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("\nIt's been a pleasure grazing through your tasks! Goodbye human! Stay cozy!\n");
        System.out.println("𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣\n");
    }

    /**
     * Reads a command from the user.
     *
     * @return the command entered by the user as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message when loading fails.
     *
     * @param message The error message to be displayed.
     */
    public void showLoadingError(String message) {
        printDecoratedMessage(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        printDecoratedMessage(message);
    }

    /**
     * Displays a message indicating that a task has been added successfully.
     * 
     * @param task The task that was added.
     * @param size The number of tasks in the list after adding the new task.
     */
    public void showAddedTask(Task task, int size) {
        StringBuilder message = new StringBuilder();
        message.append("\nYou added a task human!\n" + "  " + task).append("\n")
                .append("Now you have ").append(size).append(" tasks in your list!");
        printDecoratedMessage(message.toString());
    }

    /**
     * Displays the task list to the user.
     * If the task list is empty, it prints a message indicating that the list is empty.
     * Otherwise, it prints the task list in a formatted manner.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            printDecoratedMessage("\nYour list is empty, human!");            
            return;
        }
        StringBuilder listOutput = new StringBuilder("\nYour list, human!\n");
        for (int i = 0; i < tasks.size(); i++) {
            listOutput.append("  ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        printDecoratedMessage(listOutput.toString());    
    }

    /**
     * Displays the found tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showFoundTasks(List<Task> tasks) {
        StringBuilder messageBuilder = new StringBuilder();
        if (tasks.isEmpty()) {
            messageBuilder.append("\nHuman! There are no tasks!!");
        } else {
            messageBuilder.append("\nHere are the matching tasks in your list human:\n");
            for (int i = 0; i < tasks.size(); i++) {
                messageBuilder.append((i + 1) + ". " + tasks.get(i).toString()).append("\n");
            }
        }
        printDecoratedMessage(messageBuilder.toString());
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that has been deleted.
     * @param size The number of tasks remaining after deletion.
     */
    public void showDeletedTask(Task task, int size) {
        printDecoratedMessage("\nRemoved this task for you, human.\n" + "  " + task + "\nNow you have " + size + " tasks left human!");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkedAsDone(Task task) {
        printDecoratedMessage("\nMarked as done, human!\n" + "  " + task);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     * 
     * @param task The task that has been marked as not done.
     */
    public void showMarkedAsNotDone(Task task) {
        printDecoratedMessage("\nNot done with this yet, human?\n" + "  " + task);
    }

    /**
     * Prints a message with a decorative pattern.
     * 
     * @param message the message to be printed
     */
    public void printDecoratedMessage(String message) {
        String decoration = "\n↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟\n";
        System.out.println(message + decoration);
    }
}
