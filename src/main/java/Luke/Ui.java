package Luke;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with the task list.
 */
public class Ui {
    protected String input;

    protected String command;

    protected TaskList taskList;
    protected String[] validCommands = {"bye", "list", "unmark", "mark", "todo", "event", "deadline", "delete"};

    protected Parser parser;
    /**
     * Constructs a Ui object with an empty task list.
     */
    Ui() {
        this.input = "";
        this.taskList = new TaskList();
        this.parser = new Parser(validCommands, taskList);
    }

    /**
     * Constructs a Ui object with the given task list.
     *
     * @param taskList the task list to use
     */
    Ui(TaskList taskList) {
        this.input = "";
        this.taskList = taskList;
        this.parser = new Parser(validCommands, taskList);
    }

    /**
     * Handles user input and executes corresponding commands until the user exits.
     */
    protected void handleInput() {
        while (!this.command.equals("bye")) {
            try {
                parser.isInputValid(this.input);
                this.command = parser.getCommand(this.input);
            } catch (LukeException e) {
                System.out.println(e.getMessage());
                this.input = "";
                this.command = "";
            }
            switch (this.command) {
                case "list":
                    this.list();
                    parser.commandList(taskList);
                    break;
                case "mark":
                    try {
                        Task taskMarked = parser.commandMark(this.input);
                        markSuccess(taskMarked);
                    } catch (LukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "unmark":
                    try {
                        Task taskUnmarked = parser.commandUnmark(this.input);
                        unmarkSuccess(taskUnmarked);
                    } catch (LukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        Task taskDeleted = parser.commandDelete(this.input);
                        deleteSuccess(taskDeleted, taskList.getNoTasks());
                    } catch (LukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "todo":
                    try {
                        Task todoAdded = parser.commandTodo(this.input);
                        taskSuccessfullyAdded(todoAdded, taskList.getNoTasks());
                    } catch (LukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        Task deadlineAdded = parser.commandDeadline(this.input);
                        taskSuccessfullyAdded(deadlineAdded, taskList.getNoTasks());
                    } catch (LukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        Task eventAdded = parser.commandEvent(this.input);
                        taskSuccessfullyAdded(eventAdded, taskList.getNoTasks());
                    } catch (LukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
            Scanner scanner = new Scanner (System.in);
            this.input = scanner.nextLine();
        }
    }

    /**
     * Displays a message indicating a loading error when the file is not found.
     */
    public void showLoadingError() {
        System.out.println("File not found.");
    }

    /**
     * Displays a welcome message and prompts the user for input.
     */
    public void welcome() {
        String name = "Luke";
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
        Scanner scanner = new Scanner (System.in);
        this.input = scanner.nextLine();
        try {
            parser.isInputValid(this.input);
            this.command = parser.getCommand(input);
        } catch (LukeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays a message indicating successful marking of a task as done.
     *
     * @param task the task marked as done
     */
    private void list() {
        System.out.println("Here are the tasks in your list:");
    }

    public void markSuccess(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Displays a message indicating successful marking of a task as not done yet.
     *
     * @param task the task marked as not done yet
     */
    public void unmarkSuccess(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    /**
     * Displays a message indicating successful deletion of a task.
     *
     * @param task the task deleted
     * @param noTasks the number of tasks remaining in the list
     */
    public void deleteSuccess(Task task, int noTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + noTasks + " tasks in the list.");
    }

    /**
     * Displays a message indicating successful addition of a task.
     *
     * @param task the task added
     * @param noTasks the number of tasks in the list after addition
     */
    public void taskSuccessfullyAdded(Task task, int noTasks) {
        System.out.println("I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + noTasks + " tasks in the list.");
    }

    /**
     * Displays a farewell message when the user exits.
     */
    public void end() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
