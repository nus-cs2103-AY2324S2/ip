package shodan.ui;

import java.io.PrintStream;
import java.util.List;

import shodan.ShodanException;
import shodan.tasks.Task;

/**
 * Handles text output to the CLI for Shodan
 */
public class TermUi {
    private PrintStream ps;

    public TermUi(PrintStream ps) {
        this.ps = ps;
    }
    /**
     * Prints the greeting message to the user.
     */
    public void showGreeting() {
        String logo = "   _____ __  ______  ____  ___    _   __  \n"
                + "  / ___// / / / __ \\/ __ \\/   |  / | / /\n"
                + "  \\__ \\/ /_/ / / / / / / / /| | /  |/ / \n"
                + " ___/ / __  / /_/ / /_/ / ___ |/ /|  /    \n"
                + "/____/_/ /_/\\____/_____/_/  |_/_/ |_/    \n";
        ps.println(logo);
        ps.println("Greetings, human.");
        ps.println("What can I do for you?");
    }

    /**
     * Shows the exit message to the user..
     */
    public void showExitMsg() {
        ps.println("Goodbye.");
    }

    /**
     * Show the prompt symbol, used to let the user know
     * that we need their input.
     */
    public void showPrompt() {
        ps.print("> ");
    }

    /**
     * Outputs the list of tasks to the user.
     *
     * @param tasks the list of tasks.
     */
    public void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            ps.println("You currently have no tasks.");
        }
        ps.println("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            ps.print(i + 1 + ". ");
            ps.println(tasks.get(i));
        }
    }
    /**
     * Accepts a list of searched tasks, and outputs them to the user.
     *
     * @param tasks the list of tasks.
     */
    public void listSearchedTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            ps.println("No tasks were found matching your search.");
        }
        ps.println("Here are the tasks that matched your search:");
        for (int i = 0; i < tasks.size(); i++) {
            ps.print(i + 1 + ". ");
            ps.println(tasks.get(i));
        }
    }

    /**
     * Print a message to the user.
     *
     * @param s the message to print.
     */
    public void printMsg(String s) {
        ps.println(s);
    }

    /**
     * Prints the error message of any caught exceptions.
     *
     * @param e the exception to print.
     */
    public void printError(ShodanException e) {
        ps.println(e.getMessage());
    }
}
