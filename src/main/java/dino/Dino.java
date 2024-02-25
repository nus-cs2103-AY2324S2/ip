package dino;

import java.util.ArrayList;
import java.util.List;

import dino.commands.Command;
import dino.commands.Parser;
import dino.tasks.TaskList;

/**
 * The Dino class is a Java program that acts as a task manager, allowing users to input commands to manage and
 * manipulate a list of tasks.
 */

public class Dino {

    private static final String TASKS_CACHE_PATH = ".dino-cache";
    private Storage storage;
    private TaskList tasks;
    public boolean shouldTerminate = false;

    public Dino() {
        this.storage = new Storage(TASKS_CACHE_PATH);
        this.tasks = new TaskList();
        try {
            tasks = storage.load();
        } catch (Exception e) {
            System.out.println("Issues occurred while loading tasks: " + e.getMessage());
        }
    }

    public String getGreeting() {
        List<String> greeting = new ArrayList<>();
        greeting.add("Hello! I'm Dino");
        Reminders.getReminders(tasks, greeting);
        greeting.add("\nHow can I help you?");
        return String.join("\n", greeting);
    }

    /**
     * The function takes an input, processes it using a parser, executes a command on a list of tasks, saves the
     * tasks to storage, and returns a string representation of any messages generated during the process.
     * @param input A string representing the user's input command.
     * @return string message to be print out later.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.processInput(input);
            List<String> messages = command.execute(this.tasks);
            storage.save(this.tasks);
            if (input.startsWith("bye")) {
                shouldTerminate = true;
            }
            return String.join("\n", messages);
        } catch (Exception e) {
            return "Oops! Something went wrong\n" + e.getMessage();
        }
    }
}
