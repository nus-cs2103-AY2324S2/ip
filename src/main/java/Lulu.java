import command.Command;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

import exceptions.LuluException;
import exceptions.InvalidCommandException;
import exceptions.InvalidDateException;
import exceptions.InvalidSlashParameterException;

/**
 * The main class for Lulu, a simple chatbot application.
 * Lulu interacts with the user through a command-line interface (CLI)
 * to perform tasks such as adding, listing, and deleting tasks.
 */
public class Lulu {

    /** The storage component for managing data persistence. */
    private Storage storage;

    /** The task list for storing and managing tasks. */
    private TaskList tasks;

    /** The parser for interpreting user input and generating commands. */
    private Parser parser;

    /**
     * Constructs a new instance of Lulu with default configurations.
     * Initializes the storage, task list, and parser.
     */
    public Lulu() {
        storage = new Storage("src/main/java/data/lulu.txt");
        tasks = new TaskList(storage.retrieveLines());
        parser = new Parser();
    }

    /**
     * Initiates the Lulu application.
     * Prints a welcome message to the user.
     */
    public void start() {
        UI.print("Hello! I'm Lulu \n\tWhat can I do for you?");
    }

    /**
     * Exits the Lulu application.
     * Prints a goodbye message to the user.
     */
    public void exit() {
        UI.print("Bye. Hope to see you again soon!");
    }

    /**
     * Listens for user input and responds accordingly until the user exits.
     * Handles various user commands and exceptions during interaction.
     */
    public void respond() {
        while (true) {
            String input = UI.nextLine();

            try {
                if (input.toLowerCase().equals("bye")) {
                    break;
                } else if (input.toLowerCase().equals("list")) {
                    UI.printTasks(this.tasks);
                } else {
                    Command command = parser.parse(input);
                    command.execute(this.tasks, this.storage);
                }
            } catch (InvalidCommandException e) {
                UI.print("Sorry, I don't think I quite understood what you meant...");
            } catch (InvalidDateException e) {
                UI.print("Please ensure that you are inputting valid start and end dates.");
            } catch (InvalidSlashParameterException e) {
                UI.print("Please ensure that you are inputting valid date parameters.");
            } catch (LuluException e) {
                UI.print(e.getMessage());
            }
        }
    }

    /**
     * The main method to run the Lulu chatbot application.
     * Creates an instance of Lulu, starts the application, and handles exceptions.
     * Exits the application with a goodbye message.
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        chatbot.start();
        try {
            chatbot.respond();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        chatbot.exit();
    }
}