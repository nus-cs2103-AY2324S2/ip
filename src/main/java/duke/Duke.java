package duke;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles initial setup of main application.
 */

public class Duke {
    public static final String DATABASE_PATH = "./duke.txt";
    private DatabaseHandler databaseHandler;
    private TaskList taskList;
    private Ui userInterface;
    private CommandParser commandParser;

    /**
     * Starts a Duke instance, loads necessary components
     */
    public Duke() {
        userInterface = new Ui();
        databaseHandler = new DatabaseHandler(DATABASE_PATH);
        try {
            taskList = new TaskList(databaseHandler.loadOps());
            commandParser = new CommandParser(new Scanner(System.in), this.taskList);
        } catch (DukeBotException e) {
            userInterface.displayLoadError();
            taskList = new TaskList();
        }
    }

    /**
     * Given a user input, this calls the command parser and returns the output
     */
    String userOps(String input) {
        StringBuilder outputBuilder = new StringBuilder();

        PrintStream stream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                outputBuilder.append((char) b);
            }
        }); // Writes to the StringBuilder

        PrintStream og = System.out; // Save the original output

        System.setOut(stream); // Set the output to the custom PrintStream

        // Process the user input
        try {
            this.commandParser.processCommand(input);
            this.saveOps();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Restore the original output
            System.setOut(og);
        }
        // Return the captured output
        return outputBuilder.toString();
    }

    /**
     * Saves the current task list
     */
    private void saveOps() {
        List<String> lines = new ArrayList<>();
        for (Task task : this.taskList.getTaskList()) {
            String stringTask = TaskList.taskToDbString(task);
            lines.add(stringTask);
        }
        this.databaseHandler.writeOps(lines);
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

        Scanner scanner = new Scanner(System.in); // Scanner object to process input
        String input = scanner.nextLine();

        while (this.commandParser.processCommand(input)) {
            input = scanner.nextLine();
        }
        this.saveOps(); // Save tasks in storage
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
