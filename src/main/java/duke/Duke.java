package duke;

import java.io.ByteArrayOutputStream;
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
     * Given a user input, this calls the command parser and returns the output
     */
    String userOps(String input) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream originalOut = System.out; // Save the original output
        System.setOut(printStream); // Redirect output to the ByteArrayOutputStream

        try {
            this.commandParser.processCommand(input);
            this.saveOps();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Restore the original output
            System.setOut(originalOut);
        }

        // Return the captured output
        return outputStream.toString();
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
