package duke;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Duke is a simple task manager app
 * The class does the initial setup for components for the main application
 */
public class Duke {
    //    public static final String DB_PATH = "../data/duke_test.txt"; // uncommment for runtest.sh
    public static final String DB_PATH = "./duke.txt";
    private Storage storage;
    private TaskList myTasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initialises a Duke instance, and loads tasks and components before starting up the app
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DB_PATH);
        try {
            myTasks = new TaskList(storage.load());
            parser = new Parser(new Scanner(System.in), this.myTasks);
        } catch (DukeException e) {
            ui.showLoadingError();
            myTasks = new TaskList();
        }
    }

    /**
     * Given a user input string, invokes the parser and returns the output in the UI.
     */
    String getResponse(String input) {
        // Create a StringBuilder to hold the output
        StringBuilder outputBuilder = new StringBuilder();

        // Create a PrintStream that writes to the StringBuilder
        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                outputBuilder.append((char) b);
            }
        });

        // Save the original System.out
        PrintStream prev = System.out;

        // Set the System.out to the custom PrintStream
        System.setOut(ps);
        // process the user input
        try {
            this.parser.processCmd(input);
            this.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Restore the original System.out
            System.setOut(prev);
        }
        // Return the captured output
        return outputBuilder.toString();
    }

    /**
     * Saves the current tasklist to the hard drive.
     */
    private void save() {
        List<String> lines = new ArrayList<>();
        for (Task t : this.myTasks.taskList) {
            String stringTask = TaskList.task2Db(t);
            lines.add(stringTask);
        }
        this.storage.writeLinesToFile(lines);
    }

    /**
     * Starts up the bot and sets up the Parser and UI.
     */
    public void run() {
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm steve\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";

        String goodbye = " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        ui.greet();

        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        String line = scanner.nextLine(); // Get first input

        while (this.parser.processCmd(line)) {
            line = scanner.nextLine();
        }
        // save the tasks from myTasks to duke.Storage
        this.save();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
