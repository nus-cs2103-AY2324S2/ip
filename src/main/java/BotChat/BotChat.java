package BotChat;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        case HELP:
            showHelpWindow();
            return "See help window! Please close the help window before keying in next command :)";
        case UNKNOWN:
            throw new BotChatException("Sorry, I do not understand that command. Please try again.");
        default:
            return "";
        }
    }

    /**
     * Displays a help message with available commands.
     *
     * @return The help message.
     */
    private String showHelpMessage() {
        StringBuilder helpMessage = new StringBuilder("   === Help ===\n");
        helpMessage.append("   Here are the available commands:\n");
        helpMessage.append("   /bye - Exit the application\n");
        helpMessage.append("   /list - List all tasks\n");
        helpMessage.append("   /mark [taskIndex] - Mark a task as done\n");
        helpMessage.append("   /unmark [taskIndex] - Unmark a task as undone\n");
        helpMessage.append("   /event [event details] /from [start] /to [end] - Add an event task\n");
        helpMessage.append("   /deadline [deadline details] /by [date/time] - Add a deadline task\n");
        helpMessage.append("   /todo [task details] - Add a todo task\n");
        helpMessage.append("   /delete [taskIndex] - Delete a task\n");
        helpMessage.append("   /find [keyword] - Find tasks containing a keyword\n");
        helpMessage.append("   /help - Display this help message\n");
        return helpMessage.toString();
    }

    /**
     * Displays a help message in another window.
     */
    private void showHelpWindow() {
        Stage helpStage = new Stage();
        helpStage.initModality(Modality.APPLICATION_MODAL);
        helpStage.setTitle("Help");

        Label helpLabel = new Label(showHelpMessage());
        VBox vbox = new VBox(helpLabel);
        Scene scene = new Scene(vbox, 400, 300);

        helpStage.setScene(scene);
        helpStage.show();
    }
}
