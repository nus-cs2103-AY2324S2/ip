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

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
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
                    "%d. %s", i + 1, this.tasks.get(i).getDescription()));
        }
        return messages;
    }
}

class CommandCreator {
    private TaskList taskList;

    public CommandCreator(TaskList taskList) {
        this.taskList = taskList;
    }

    public Command createCommand(String command) {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand(this.taskList);
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
        ui.printMessage(this.taskList.listTasks());
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

    public void printMessage(ArrayList<String> messages) {
        System.out.println(" ".repeat(LINE_INDENTATION) + LINE);
        for (String message : messages) {
            System.out.println(" ".repeat(CONTENT_INDENTATION) + message);
        }
        System.out.println(" ".repeat(LINE_INDENTATION) + LINE);
        System.out.println("");
    }

    public void printWelcomeMessage() {
        printMessage(WELCOME_MESSAGES);
    }

    public void processCommands(CommandCreator commandCreator) {
        while (this.isActive) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            Command command = commandCreator.createCommand(input);
            command.execute(this);
            scanner.close();
        }
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
