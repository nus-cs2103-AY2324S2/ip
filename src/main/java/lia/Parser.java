package lia;

import java.util.ArrayList;

/**
 * The Parser class is responsible for interpreting user commands and delegating actions accordingly.
 * It interacts with the user interface, task list, and handles parsing and execution of commands.
 */
public class Parser {
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Parser object with the specified user interface and task list.
     *
     * @param ui    The user interface component.
     * @param tasks The task list to be managed by the parser.
     */
    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Parses the user command and performs the corresponding action.
     *
     * @param input The user command to be parsed.
     */
    public void parseCommand(String input) {
        try {
            if (input.equals("exit")) {
                ui.showGoodbye();
            } else if (input.equals("list")) {
                ArrayList<Task> listTasks = tasks.getTasks();
                ui.showTasks(listTasks);

                if (listTasks.isEmpty()) {
                    System.out.println("Your task list is currently empty.");
                }
            } else if (input.startsWith("mark")) {
                markTaskAsDone(input);
            } else if (input.startsWith("unmark")) {
                markTaskAsNotDone(input);
            } else if (input.startsWith("todo")) {
                addTodoTask(input);
            } else if (input.startsWith("deadline")) {
                addDeadlineTask(input);
            } else if (input.startsWith("event")) {
                addEventTask(input);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else if (input.startsWith("find")) {
                findTasks(input);
            } else if (input.equals("help")) {
                ui.showHelp();
            } else {
                ui.showInvalidCommand();
            }
        } catch (LiaException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Marks the specified task as done based on the user command input.
     *
     * @param input The user command input.
     * @throws LiaException If an error occurs while processing the command.
     */
    void markTaskAsDone(String input) throws LiaException {
        String[] tokens = input.split(" ");
        int pos = Integer.parseInt(tokens[1]);

        tasks.validateTaskPosition(pos);
        tasks.markTaskAsDone(pos);
        ui.showMarkedAsDone(tasks.getTask(pos - 1));
    }

    /**
     * Marks the specified task as not done based on the user command input.
     *
     * @param input The user command input.
     * @throws LiaException If an error occurs while processing the command.
     */
    void markTaskAsNotDone(String input) throws LiaException {
        String[] tokens = input.split(" ");
        int pos = Integer.parseInt(tokens[1]);

        tasks.validateTaskPosition(pos);
        tasks.markTaskAsNotDone(pos);
        ui.showMarkedAsNotDone(tasks.getTask(pos - 1));
    }

    /**
     * Adds a todo task based on the user command input.
     *
     * @param input The user command input.
     * @throws LiaException If an error occurs while processing the command.
     */
    void addTodoTask(String input) throws LiaException {
        String todo = input.replaceFirst("todo", "").trim();

        if (todo.isEmpty()) {
            throw new LiaException("lia.Task description cannot be empty.");
        }

        tasks.addTodoTask(todo);
        ui.showAddedTask(tasks.getLastTask(), tasks);
    }

    /**
     * Adds a deadline task based on the user command input.
     *
     * @param input The user command input.
     * @throws LiaException If an error occurs while processing the command.
     */
    void addDeadlineTask(String input) throws LiaException {
        String deadline = input.replaceFirst("deadline", "").split("/by")[0].trim();

        if (deadline.isEmpty()) {
            throw new LiaException("lia.Task description cannot be empty.");
        }

        String date = input.split("/by")[1].trim();

        tasks.addDeadlineTask(deadline, date);
        ui.showAddedTask(tasks.getLastTask(), tasks);
    }

    /**
     * Adds an event task based on the user command input.
     *
     * @param input The user command input.
     * @throws LiaException If an error occurs while processing the command.
     */
    void addEventTask(String input) throws LiaException {
        String event = input.replaceFirst("event", "").split("/from")[0].trim();

        if (event.isEmpty()) {
            throw new LiaException("lia.Event description cannot be empty.");
        }

        String range = input.split("/from")[1].trim();
        String start = range.split("/to")[0].trim();
        String end = range.split("/to")[1].trim();

        tasks.addEventTask(event, start, end);
        ui.showAddedTask(tasks.getLastTask(), tasks);
    }

    /**
     * Deletes the specified task based on the user command input.
     *
     * @param input The user command input.
     * @throws LiaException If an error occurs while processing the command.
     */
    void deleteTask(String input) throws LiaException {
        String[] tokens = input.split(" ");
        int pos = Integer.parseInt(tokens[1]);

        tasks.validateTaskPosition(pos);
        Task removedTask = tasks.getTask(pos - 1);
        tasks.deleteTask(pos);
        ui.showRemovedTask(removedTask, tasks);
    }

    /**
     * Finds tasks containing the specified keyword and displays them.
     *
     * @param input The user command input.
     */
    void findTasks(String input) {
        String keyword = input.replaceFirst("find", "").trim();
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (!matchingTasks.isEmpty()) {
            System.out.println("Matching tasks:");
        }
        ui.showTasks(matchingTasks);

        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        }
    }
}