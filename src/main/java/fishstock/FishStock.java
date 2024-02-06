package fishstock;

import java.io.IOException;

import javafx.application.Application;

/**
 * Encapsulates a FishStock object.
 */
public class FishStock {
    private Storage storage;
    private TaskList list;
    private boolean hasStopped = false;

    /**
     * List of keywords to run respective commands.
     */
    protected enum Command {
        INVALID, BYE, LIST, MARK, UNMARK, DELETE, FIND, TODO, DEADLINE, EVENT
    }

    /**
     * Initializes a FishStock object.
     */
    public FishStock() {
        storage = new Storage("./data/tasks.txt");

        try {
            list = new TaskList(storage.load());
        } catch (FishStockException e) {
            e.printStackTrace();
            list = new TaskList();
        }
    }

    /**
     * The response to user input.
     * @param input The user input.
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        try {
            switch (command) {
            case BYE:
                hasStopped = true;
                return "Bye! You'll be back ;)";
            case LIST:
                return "Here are the tasks in your list:\n" + list.toString();
            case MARK:
                return "Did you actually finish this? \uD83E\uDD14:\n" + "  "
                        + list.changeMark(command, input);
            case UNMARK:
                return "I knew you didn't finish it! \uD83D\uDE0F:\n" + "  "
                        + list.changeMark(command, input);
            case DELETE:
                return "This task has been removed:\n  " + list.deleteTask(input) + "\n"
                        + "Now you have " + list.getSize() + " task(s) in total.";
            case FIND:
                return "Here are the matching tasks in your list:\n" + list.findTasks(input);
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                return "This task has been added:\n  " + list.addTask(command, input) + "\n"
                        + "Now you have " + list.getSize() + " task(s) in total.";
            default:
                return "OH NOSE! Wakaranai... :(";
            }
        } catch (FishStockException e) {
            return e.getMessage();
        }
    }

    /**
     * Exits program while saving list.
     */
    public void exit() {
        try {
            storage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns stopped status.
     */
    public boolean getStopped() {
        return hasStopped;
    }

    /**
     * Entrypoint to FishStock.
     * @param args ignored
     */
    public static void main(String[] args) {
        Application.launch(fishstock.ui.Main.class, args);
    }
}
