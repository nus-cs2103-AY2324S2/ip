package Luke;

import java.util.Scanner;

public class Ui {
    protected String input;

    protected String command;

    protected TaskList taskList;
    protected String[] validCommands = {"bye", "list", "unmark", "mark", "todo", "event", "deadline", "delete"};

    protected Parser parser;
    Ui() {
        this.input = "";
        this.taskList = new TaskList();
        this.parser = new Parser(validCommands, taskList);
    }

    Ui(TaskList taskList) {
        this.input = "";
        this.taskList = taskList;
        this.parser = new Parser(validCommands, taskList);
    }

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

    public void showLoadingError() {
        System.out.println("File not found.");
    }
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

    private void list() {
        System.out.println("Here are the tasks in your list:");
    }

    public void markSuccess(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void unmarkSuccess(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public void deleteSuccess(Task task, int noTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + noTasks + " tasks in the list.");
    }

    public void taskSuccessfullyAdded(Task task, int noTasks) {
        System.out.println("I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + noTasks + " tasks in the list.");
    }

    public void inputError(String type) {
        switch (type) {
            case "taskNumberInvalid":
                System.out.println("Luke.Task does not exist. Please give a valid task number.");
            case "descriptionEmpty":
                System.out.println("Invalid command. The description cannot be empty.");
            case "deadlineEmpty":
                System.out.println("Invalid command. The deadline cannot be empty.");
            case "eventFromEmpty":
                System.out.println("Invalid command. The from section cannot be empty.");
            case "eventToEmpty":
                System.out.println("Invalid command. The to section cannot be empty.");
        }
    }

    public void end() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
