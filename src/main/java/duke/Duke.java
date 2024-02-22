package duke;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple task manager application.
 * Handles initial setup for components of the main application.
 */

public class Duke {
    public static final String DATABASE_PATH = "./duke.txt";
    private DatabaseHandler databaseHandler;
    private TaskList taskList;
    private Ui userInterface;
    private CommandParser commandParser;

    /**
     * Initializes a Duke instance, loads tasks and components before starting up the application.
     */
    public Duke() {
        userInterface = new Ui();
        databaseHandler = new DatabaseHandler(DATABASE_PATH);
        try {
            taskList = new TaskList(databaseHandler.loadData());
            commandParser = new CommandParser(new Scanner(System.in), this.taskList);
        } catch (DukeBotException e) {
            userInterface.displayLoadError();
            taskList = new TaskList();
        }
    }

    /**
     * Given a user input string, invokes the command parser and returns the output in the UI.
     */
    String getResponse(String input) {
        // Create a StringBuilder to hold output
        StringBuilder outputBuilder = new StringBuilder();

        // Create a PrintStream that writes to the StringBuilder
        PrintStream stream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                outputBuilder.append((char) b);
            }
        });

        // Save the original System.out
        PrintStream og = System.out;

        // Set the System.out to the custom PrintStream
        System.setOut(stream);
        // Process the user input
        try {
            this.commandParser.processCommand(input);
            this.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Restore the original System.out
            System.setOut(og);
        }
        // Return the captured output
        return outputBuilder.toString();
    }

    /**
     * Saves the current task list
     */
    private void save() {
        List<String> lines = new ArrayList<>();
        for (Task task : this.taskList.getTaskList()) {
            String stringTask = TaskList.taskToDbString(task);
            lines.add(stringTask);
        }
        this.databaseHandler.writeToFile(lines);
    }

    /**
     * Starts up the application.
     */
    public void run() {
        String greeting = "____________________________________________________________\n"
                + " Hey! I'm Hari!"
                + " How may I be of service today?"
                + "____________________________________________________________";

        String goodbye = " Au revoir! Till we meet again!\n"
                + "____________________________________________________________";
        userInterface.greeting();

        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        String input = scanner.nextLine(); // Get first input

        while (this.commandParser.processCommand(input)) {
            input = scanner.nextLine();
        }
        // Save the tasks from taskList to duke.DatabaseHandler
        this.save();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
