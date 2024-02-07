package duke;

import duke.tasks.TaskList;
import duke.commands.Command;
import duke.commands.Parser;

import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String TASKS_CACHE_PATH = ".duke-cache";
    private static TaskList tasks;
    private static Storage storage;
    private static final String HORIZONTAL_LINE= "---------------------------------\n";

    private static void greet() {
        String greet = "Hello! I'm Dino\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE;
        System.out.println(greet);
    }

    public static void main(String[] args) {
        storage = new Storage(TASKS_CACHE_PATH);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            System.out.println("Issues occurred while loading tasks: " + e.getMessage());
        }

        greet();


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            System.out.println(HORIZONTAL_LINE);
            String messages = Duke.getResponse(input);
            System.out.println(messages);
            storage.save(tasks);
            System.out.println(HORIZONTAL_LINE);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    /**
     * The function takes an input, processes it using a parser, executes a command on a list of tasks, saves the
     * tasks to storage, and returns a string representation of any messages generated during the process.
     * 
     * @param input A string representing the user's input command.
     * @return string message to be print out later.
     */
    public static String getResponse(String input) {
        try {
            Command command = Parser.processInput(input);
            List<String> messages = command.execute(tasks);
            storage.save(tasks);
            return String.join("\n", messages);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
