package duke;

import duke.tasks.TaskList;
import duke.commands.Command;
import duke.commands.Parser;

import java.util.List;
import java.util.Scanner;


public class Duke {

    private static final String TASKS_CACHE_PATH = ".duke-cache";
    private static final String HORIZONTAL_LINE = "---------------------------------\n";


    public static void main(String[] args) {

        Storage storage = new Storage(TASKS_CACHE_PATH);

        TaskList tasks = new TaskList();
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
            String messages = Duke.getResponse(input, storage, tasks);
            System.out.println(messages);
            storage.save(tasks);
            System.out.println(HORIZONTAL_LINE);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void greet() {
        String greet = "Hello! I'm Dino\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE;
        System.out.println(greet);
    }

    /**
     * The function takes an input, processes it using a parser, executes a command on a list of tasks, saves the
     * tasks to storage, and returns a string representation of any messages generated during the process.
     * 
     * @param input A string representing the user's input command.
     * @return string message to be print out later.
     */
    public static String getResponse(String input, Storage storage, TaskList tasks) {
        try {
            Command command = Parser.processInput(input);
            List<String> messages = command.execute(tasks);
            storage.save(tasks);
            return String.join("\n", messages);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
