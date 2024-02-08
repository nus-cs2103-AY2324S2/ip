package main.java.emis;
import main.java.emis.command.*;
import main.java.emis.task.*;
import java.util.Scanner;

// Ui class, deals with interactions with the user
public class Ui {
    private static Scanner sc;
    public Ui () {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("\tHello! I'm Emis!\n \tWhat can I do for you?");
        showLine();
    }

    public static void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showLoadingError() {
        showLine();
        System.out.println("\t There was an error loading the file.");
        showLine();
    }

    public void showError(String str) {
        System.out.println(str);
    }

    public void showHelp() {
        showLine();
        System.out.println("\tEmis is happy to help with printing a list of tasks with the command 'list'.");
        System.out.println("\tAdd todos with the command 'todo (insert task here)'.");
        System.out.println("\tAdd deadlines with the command 'deadline (insert deadline name) /by (insert deadline here in the format yyyy-mm-dd hhmm)'.");
        System.out.println("\tAdd events with the command 'event (insert event name) /from (insert start time) /to (insert end time)'.");
        System.out.println("\tMark tasks as done with the command 'mark (task no)'.");
        System.out.println("\tMark tasks as undone with the command 'unmark (task no)'.");
        System.out.println("\tDelete tasks  with the command 'delete (task no)'.");
        System.out.println("\tTo stop talking to Emis, please say 'bye'.");
        showLine();
    }

    public static void exit() {
        sc.close();
        showLine();
        System.out.println("\tBye. Hope to see you again soon!");
        showLine();
        System.exit(0);
    }
} 