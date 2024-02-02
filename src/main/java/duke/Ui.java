package duke;

import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main class that prints outputs.
 *
 * @author Lim Zi Jia
 */
public class Ui {
    /** Logo of Skibidi. */
    public static String logo = " ____  _  _____ ____ ___ ____ ___ \n" +
            "/ ___|| |/ /_ _| __ )_ _|  _ \\_ _|\n" +
            "\\___ \\| ' / | ||  _ \\| || | | | | \n" +
            " ___) | . \\ | || |_) | || |_| | | \n" +
            "|____/|_|\\_\\___|____/___|____/___|";

    /**
     * Prints a solid line that separates each action.
     */
    public static void printLine() {
        System.out.println("\n-------------------------------------------------------------------\n");
    }

    /**
     * Greets the user in the console.
     */
    public void greet() {
        System.out.println(Ui.logo);
        printLine();
        System.out.println("Hello! I'm Skibidi!\nWhat can I do for you?");
        printLine();
    }

    /**
     * Says goodbye to the user.
     */
    public void bye() {
        System.out.println("Bye! Hope to see you again soon!");
        printLine();
    }

    /**
     * The main chatting loop of the chatbot.
     * @param tasks The current tasks.
     * @param storage An object that has the paths to the save location and ability to load and save the list.
     */
    public void chat(TaskList tasks, Storage storage) {
        Scanner sc = new Scanner(System.in);
        String in = null;
        Parser p = new Parser();

        while (!(tasks == null)) {
            in = sc.nextLine();

            tasks = p.parse(in, tasks, storage); // Parse will return null if command is "bye"

            printLine();
        }
    }

}