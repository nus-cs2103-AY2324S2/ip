import java.util.Scanner;
import java.util.NoSuchElementException;

public class Duke {
    static final String CHATBOT_NAME = "Echo";
    static final int CELL_WIDTH = 70;

    /**
     * Function to print the banner.
     */
    public void printBanner() {
        // Banner somewhat inspired from SageMath's banner.
        System.out.println("┌" + new String(new char[CELL_WIDTH - 2]).replace("\0", "─") + "┐");
        // Java 8 compatible, for now.
        String firstLine = CHATBOT_NAME + ", the chatbot. Version 0.0.0.";
        System.out.println(String.format("│ %-" + (CELL_WIDTH - 4) + "s │", firstLine));
        String secondLine = "Using Java " + System.getProperty("java.version") + ".";
        System.out.println(String.format("│ %-" + (CELL_WIDTH - 4) + "s │", secondLine));
        System.out.println("└" + new String(new char[CELL_WIDTH - 2]).replace("\0", "─") + "┘");
    }

    /**
     * Function to ask user for a prompt.
     */
    public String askForPrompt() {
        System.out.print(">>> ");
        return sc.nextLine();
    }

    Scanner sc = new Scanner(System.in);
    Parser parser = new Parser();
    Storage storage = new Storage();

    /**
     * Main function to run the chatbot.
     */
    public void run() {
        printBanner();
        while (true) {
            String prompt;
            try {
                prompt = askForPrompt();
            } catch (NoSuchElementException e) {
                System.out.println();
                new ByeCommand().execute(storage);
                break;
            }

            Command command;
            try {
                command = parser.parse(prompt);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
                continue;
            }
            command.execute(storage);
        }
    }

    public static void main(String[] args) {
        Commands.registerCommands();
        Duke duke = new Duke();
        duke.run();
    }
}
