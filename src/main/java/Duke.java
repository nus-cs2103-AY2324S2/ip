import java.util.Scanner;

import DukeException.DukeException;
import commands.*;
import tasks.Storage;
import tasks.TaskList;

public class Duke {

    private static final String TASKS_CACHE_PATH = ".duke-cache";
    public static TaskList tasks;
    private static final String HORIZONTALLINE= "---------------------------------\n";

    private static void greet() {
        String greet = "Hello! I'm Dino\n"
                + "What can I do for you?\n"
                + HORIZONTALLINE;
        System.out.println(greet);
    }

    public static void main(String[] args) {
        Storage storage = new Storage(TASKS_CACHE_PATH);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            System.out.println("Invalid Instruction: " + e.getMessage());
        }

        greet();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            System.out.println(HORIZONTALLINE);
            try {
                Command command = Parser.processInput(input);
                if (command != null) {
                    command.execute(tasks);
                }
            } catch(DukeException e) {
                System.out.println("Invalid Instruction: " + e.getMessage());
            }
            storage.save(tasks);
            System.out.println(HORIZONTALLINE);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }


}
