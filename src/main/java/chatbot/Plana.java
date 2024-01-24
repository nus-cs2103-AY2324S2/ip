package chatbot;

import chatbot.exceptions.DukeException;
import chatbot.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Scanner;

public class Plana {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    private static final String LOGO =
            "    ____  __                 \n" +
            "   / __ \\/ /___ _____  ____ _\n" +
            "  / /_/ / / __ `/ __ \\/ __ `/\n" +
            " / ____/ / /_/ / / / / /_/ / \n" +
            "/_/   /_/\\__,_/_/ /_/\\__,_/  \n";

    private static final String NAME = "Plana";

    private final ArrayList<Task> tasks;

    private boolean shouldExit;

    public Plana() {
        this.tasks = new ArrayList<>();
        this.shouldExit = false;
    }

    public void greet() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm " + NAME + "!");
        System.out.println("What can I do for you?");
        System.out.println("======================");
    }

    public void chat() {
        Scanner scanner = new Scanner(System.in);

        while (!shouldExit) {
            try {
                System.out.print(ANSI_GREEN + "> " + ANSI_CYAN);
                String userInput = scanner.nextLine();

                parseInput(userInput);

            } catch (DukeException e) {
                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            }
        }

        scanner.close();
    }

    private void parseInput(String in) throws DukeException {
        switch (Command.toCommand(in)) {
            case EXIT:
                this.shouldExit = true;
                break;
            case LIST:
                System.out.println("You've added the following tasks so far:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s\n", i+1, tasks.get(i).getDescription());
                }
                break;
            case TODO:
                tasks.add(new Task(in));
                System.out.println("I've added the task: " + in);
                break;
            case MARK:
            case UNMARK:
                markTask(Command.toCommand(in), in.split("\\s+")[1]);
                break;
        }
    }

    private void markTask(Command cmd, String idx) throws DukeException {
        int i;
        try {
            i = Integer.parseInt(idx);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException();
        }

        if (i < 1 || i > tasks.size()) throw new InvalidArgumentException();

        switch (cmd) {
            case MARK:
                tasks.get(i-1).mark();
                System.out.println("Task " + i + "marked as done");
                break;
            case UNMARK:
                tasks.get(i-1).unmark();
                System.out.println("Task " + i + "marked as undone");
                break;
        }

    }

    public void bye() {
        System.out.println(ANSI_RESET + "==================");
        System.out.println("See you next time!");
    }
}
