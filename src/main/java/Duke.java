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

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    private String byDate;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.byDate);
    }
}

class Event extends Task {
    private String fromDate;
    private String toDate;

    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.fromDate, this.toDate);
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

    public int getSize() {
        return this.tasks.size();
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

    private String parseStringArgument(String command) {
        String[] tokens = command.split(" ");
        String argument = tokens[1];
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].startsWith("/")) {
                break;
            }
            argument += " " + tokens[i];
        }
        return argument;
    }

    private String parseNamedArgument(String command, String name) {
        String[] tokens = command.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/" + name)) {
                String argument = tokens[i + 1];
                for (int j = i + 2; j < tokens.length; j++) {
                    if (tokens[j].startsWith("/")) {
                        break;
                    }
                    argument += " " + tokens[j];
                }
                return argument;
            }
        }
        throw new IllegalArgumentException();
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
        } else if (command.startsWith("todo ")) {
            String description = parseStringArgument(command);
            return new AddTodoCommand(description, this.taskList);
        } else if (command.startsWith("deadline ")) {
            String description = parseStringArgument(command);
            String byDate = parseNamedArgument(command, "by");
            return new AddDeadlineCommand(description, byDate, this.taskList);
        } else if (command.startsWith("event ")) {
            String description = parseStringArgument(command);
            String fromDate = parseNamedArgument(command, "from");
            String toDate = parseNamedArgument(command, "to");
            return new AddEventCommand(description, fromDate, toDate,
                    this.taskList);
        } else {
            throw new IllegalArgumentException();
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

abstract class AddTaskCommand extends Command {
    protected String description;
    private TaskList taskList;

    public AddTaskCommand(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    protected abstract Task createTask();

    @Override
    public void execute(UI ui) {
        Task task = this.createTask();
        this.taskList.addTask(task);
        ArrayList<String> messages = new ArrayList<String>(Arrays.asList(
                "Got it. I've added this task:", "  " + task.toString(),
                String.format("Now you have %d tasks in the list.",
                        this.taskList.getSize())));
        ui.printMessages(messages);
    }
}

class AddTodoCommand extends AddTaskCommand {
    public AddTodoCommand(String description, TaskList taskList) {
        super(description, taskList);
    }

    @Override
    protected Task createTask() {
        return new Todo(this.description);
    }
}

class AddDeadlineCommand extends AddTaskCommand {
    private String byDate;

    public AddDeadlineCommand(String description, String byDate,
            TaskList taskList) {
        super(description, taskList);
        this.byDate = byDate;
    }

    @Override
    protected Task createTask() {
        return new Deadline(this.description, this.byDate);
    }
}

class AddEventCommand extends AddTaskCommand {
    private String fromDate;
    private String toDate;

    public AddEventCommand(String description, String fromDate, String toDate,
            TaskList taskList) {
        super(description, taskList);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    protected Task createTask() {
        return new Event(this.description, this.fromDate, this.toDate);
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
