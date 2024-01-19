import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        UI ui = new UI();
        ui.start();
    }
}

class CommandCreator {
    private CommandCreator() {
    }

    public static Command createCommand(String command) {
        if (command.equals("bye")) {
            return new ByeCommand();
        } else {
            return new EchoCommand(command);
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

    public void getAndExecuteCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Command command = CommandCreator.createCommand(input);
        command.execute(this);
    }

    public void printWelcomeMessage() {
        printMessage(WELCOME_MESSAGES);
    }

    public void start() {
        printWelcomeMessage();
        while (this.isActive) {
            getAndExecuteCommand();
        }
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
