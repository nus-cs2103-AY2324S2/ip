import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    protected String input;

    protected String command;
    protected String[] validCommands = {"bye", "list", "unmark", "mark", "todo", "event", "deadline", "delete"};

    protected Parser parser;
    Ui() {
        this.input = "";
        this.parser = new Parser(validCommands);
    }

    protected void handleInput() {
        while (!this.command.equals("bye")) {
            try {
                if (!parser.isInputValid(this.input)) {
                    throw new LukeException("Invalid command/task type.");
                }

            } catch (LukeException e) {
                System.out.println(e);
            }
            switch (this.command) {
                case "list":
                    list(Tasklist.getList());
                case "mark":
                    mark(this.input);
                case "unmark":

                case "delete":

                case "todo":

                case "deadline":

                case "event":
            }

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
        if (parser.isInputValid(this.input)) {
            this.command = parser.getCommand(input);
        }
    }

    private void list(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
        Scanner scanner = new Scanner (System.in);
        this.input = scanner.nextLine();
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
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + noTasks + " tasks in the list.");
    }

    public void inputError(String type) {
        switch (type) {
            case "taskNumberInvalid":
                System.out.println("Task does not exist. Please give a valid task number.");
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
