import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static UI ui;
    private static TaskList taskList;
    private static CommandCreator commandCreator;

    public static void main(String[] args) {
        ui = new UI();
        taskList = new TaskList();
        commandCreator = new CommandCreator(taskList);
        ui.printWelcomeMessage();
        ui.processCommands(commandCreator);
    }
}

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return String.format("[%s] %s", status, this.description);
    }
}

class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<String> listTasks() {
        ArrayList<String> messages = new ArrayList<String>();
        for (int i = 0; i < this.tasks.size(); i++) {
            messages.add(String.format(
                    "%d.%s", i + 1, this.tasks.get(i).toString()));
        }
        return messages;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }
}

class CommandCreator {
    private TaskList taskList;

    public CommandCreator(TaskList taskList) {
        this.taskList = taskList;
    }

    private int parseIndex(String command) {
        String[] tokens = command.split(" ");
        return Integer.parseInt(tokens[1]) - 1;
    }

    public Command createCommand(String command) {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand(this.taskList);
        } else if (command.startsWith("mark ")) {
            int index = parseIndex(command);
            return new MarkAsDoneCommand(index, this.taskList);
        } else if (command.startsWith("unmark")) {
            int index = parseIndex(command);
            return new UnmarkAsDoneCommand(index, this.taskList);
        } else {
            return new AddToListCommand(command, this.taskList);
        }
    }
}

abstract class Command {
    public abstract void execute(UI ui);
}

class ByeCommand extends Command {
    static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
    }

    @Override
    public void execute(UI ui) {
        ui.printMessage(BYE_MESSAGE);
        ui.setActive(false);
    }
}

class EchoCommand extends Command {
    private String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(UI ui) {
        ui.printMessage(message);
    }
}

class AddToListCommand extends Command {
    private String description;
    private TaskList taskList;

    public AddToListCommand(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public void execute(UI ui) {
        Task task = new Task(this.description);
        this.taskList.addTask(task);
        ui.printMessage(String.format("added: %s", task.getDescription()));
    }
}

class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute(UI ui) {
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList("Here are the tasks in your list:"));
        messages.addAll(this.taskList.listTasks());
        ui.printMessages(messages);
    }
}

class MarkAsDoneCommand extends Command {
    private int index;
    private TaskList taskList;

    public MarkAsDoneCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void execute(UI ui) {
        Task task = this.taskList.getTask(this.index);
        task.markAsDone();
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList("Nice! I've marked this task as done:",
                        "  " + task.toString()));
        ui.printMessages(messages);
    }
}

class UnmarkAsDoneCommand extends Command {
    private int index;
    private TaskList taskList;

    public UnmarkAsDoneCommand(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    @Override
    public void execute(UI ui) {
        Task task = this.taskList.getTask(this.index);
        task.unmarkAsDone();
        ArrayList<String> messages = new ArrayList<String>(
                Arrays.asList("OK, I've marked this task as not done yet:",
                        "  " + task.toString()));
        ui.printMessages(messages);
    }
}

class UI {
    private static final String LINE = "_".repeat(60);
    private static final ArrayList<String> WELCOME_MESSAGES = new ArrayList<String>(
            Arrays.asList("Hello! I'm Echon", "What can I do for you?"));
    private static final int LINE_INDENTATION = 4;
    private static final int CONTENT_INDENTATION = 5;

    private boolean isActive = true;

    public UI() {
    }

    public void printMessage(String message) {
        System.out.println(" ".repeat(LINE_INDENTATION) + LINE);
        System.out.println(" ".repeat(CONTENT_INDENTATION) + message);
        System.out.println(" ".repeat(LINE_INDENTATION) + LINE);
        System.out.println("");
    }

    public void printMessages(ArrayList<String> messages) {
        System.out.println(" ".repeat(LINE_INDENTATION) + LINE);
        for (String message : messages) {
            System.out.println(" ".repeat(CONTENT_INDENTATION) + message);
        }
        System.out.println(" ".repeat(LINE_INDENTATION) + LINE);
        System.out.println("");
    }

    public void printWelcomeMessage() {
        printMessages(WELCOME_MESSAGES);
    }

    public void processCommands(CommandCreator commandCreator) {
        Scanner scanner = new Scanner(System.in);
        while (this.isActive) {
            String input = scanner.nextLine();
            Command command = commandCreator.createCommand(input);
            command.execute(this);
        }
        scanner.close();
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
