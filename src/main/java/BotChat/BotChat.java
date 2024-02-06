package BotChat;

//import java.util.Scanner;

import javafx.application.Platform;

/**
 * The main class for the botChat application, responsible for handling user input and managing tasks.
 */
public class BotChat {
    private static final String FILE_PATH = "./botChat.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new botChat instance.
     */
    public BotChat() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = storage.load();
    }

//    /**
//     * Handles user input by continuously scanning and processing commands until the user exits.
//     */
//    private void userInput() {
//        // Scanner to scan what the user is inputting
//        Scanner scanner = new Scanner(System.in);
//        try {
//            while (true) {
//                String input = scanner.nextLine();
//                try {
//                    handleInput(input);
//                } catch (BotChatException e) {
//                    ui.showErrorMessage(e.getMessage());
//                }
//            }
//        } finally {
//            scanner.close();
//        }
//    }

    /**
     * Handles different types of user input commands.
     *
     * @param input The user input command.
     * @return A response message or status.
     * @throws BotChatException If an error occurs during command processing.
     */
    public String handleInput(String input) throws BotChatException {
        Command command = Parser.getCommand(input.split(" ")[0]);
        switch (command) {
        case BYE:
            Storage.saveTaskToHardDisk(tasks.getTasks());
            Platform.exit();
            return "Goodbye!";
        case LIST:
            return tasks.listTasks();
        case MARK:
            return tasks.markTask(input);
        case UNMARK:
            return tasks.unmarkTask(input);
        case EVENT:
            return tasks.addEventTask(input);
        case DEADLINE:
            return tasks.addDeadlineTask(input);
        case TODO:
            return tasks.addTodoTask(input);
        case DELETE:
            return tasks.deleteTask(input);
        case FIND:
            return tasks.findTasks(input);
        case UNKNOWN:
            throw new BotChatException("Sorry, I do not understand that command. Please try again.");
        default:
            return "";
        }
    }
}
