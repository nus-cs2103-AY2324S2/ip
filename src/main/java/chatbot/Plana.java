package chatbot;

import chatbot.exceptions.DukeException;
import chatbot.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Command cmd = Command.toCommand(in);
        switch (cmd) {
            case EXIT:
                this.shouldExit = true;
                break;
            case LIST:
                if (tasks.size() == 0) {
                    System.out.println("You have no tasks, add some!");
                    break;
                }
                System.out.println("You've added the following tasks so far:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s\n", i+1, tasks.get(i).toString());
                }
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                addTask(cmd, in.split("\\s+", 2)[1]);
                break;
            case MARK:
            case UNMARK:
                markTask(cmd, in.split("\\s+", 2)[1]);
                break;
            case DELETE:
                int i;
                try {
                    i = Integer.parseInt(in.split("\\s+", 2)[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidArgumentException();
                }

                if (i < 1 || i > tasks.size()) throw new InvalidArgumentException();
                Task removed = tasks.remove(i-1);
                System.out.println("Got it. I've removed this task:");
                System.out.println(">> " + removed);
                System.out.println("You now have " + tasks.size() + " tasks in the list.");
        }
    }

    private void addTask(Command cmd, String desc) throws DukeException {
        Task t = new Task("");
        switch (cmd) {
            case TODO:
                t = new TodoTask(desc);
                break;
            case DEADLINE:
                Pattern pattern = Pattern.compile("(.+?)\\s+/by\\s+(.+)");
                Matcher matcher = pattern.matcher(desc);

                if (!matcher.find()) {
                    throw new InvalidArgumentException();
                }
                t = new DeadlineTask(matcher.group(1), matcher.group(2));
                break;
            case EVENT:
                pattern = Pattern.compile("(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)");
                matcher = pattern.matcher(desc);

                if (!matcher.find()) {
                    throw new InvalidArgumentException();
                }
                t = new EventTask(matcher.group(1), matcher.group(2), matcher.group(3));
                break;
        }
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(">> " + t);
        System.out.println("You now have " + tasks.size() + " tasks in the list.");
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
