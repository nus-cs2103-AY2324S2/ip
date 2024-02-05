package badgpt;

import badgpt.exceptions.BadException;
import badgpt.util.FileManager;
import badgpt.util.Parser;
import badgpt.util.TaskList;
import badgpt.util.Ui;

/**
 * The main class for the chatbot program.
 */
public class BadGpt {
    private final String NAME = "BadGPT";
    private TaskList taskList;
    private Ui ui;
    private FileManager fileManager;

    /**
     * Initialises the TaskList, Ui and FileManager instances needed to run the bot.
     */
    public BadGpt() {
        taskList = new TaskList();
        ui = new Ui();
        fileManager = new FileManager();
    }

    /**
     * Starts running the bot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new BadGpt().run();
    }

    /**
     * Runs the bot. The bot gives a greeting and loads the task list saved from the previous session, if there is one.
     * Then, the bot will take in commands until "bye" is entered.
     */
    public void run() {
        ui.sayHi(NAME);

        fileManager.loadFile();
        fileManager.readFile(taskList);

        while (true) {
            String in = ui.read();
            try {
                Parser.parse(in, this, taskList);
            } catch (BadException e) {
                ui.printException(e);
            }
        }
    }

    /**
     * Exits the bot. Save any changes made to the task list and the bot gives a farewell message.
     */
    public void bye() {
        taskList.writeChanges(fileManager);
        ui.sayBye();
        ui.stop();
        System.exit(0);
    }
}
