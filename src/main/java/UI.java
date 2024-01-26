import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UI {
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

    public void processCommands(CommandCreator commandCreator, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        while (this.isActive) {
            String input = scanner.nextLine();
            try {
                Command command = commandCreator.createCommand(input);
                command.execute(this);
                storage.save();
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }
        scanner.close();
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}