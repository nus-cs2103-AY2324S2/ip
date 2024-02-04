package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Duke Chatbot to prompt the user on what task would they like to keep track of
 */
public class Duke {

    private TaskManager manager;
    private Storage storage;
    private Ui ui;

    /**
     * Setups and initialises the chatbot, with an auto save feature every 2 seconds.
     *
     * @param filePath      string path from root where the user wants to store their task.
     * @param saveFrequency input in milliseconds to indicate the frequency of autosave.
     */
    public Duke(String filePath, int saveFrequency) {

        ui = new Ui();
        Timer saveTimer = new Timer();
        storage = new Storage(filePath);
        try {
            manager = storage.loadFile();
        } catch (DukeException e) {
            //show loading error but i don't plan to show a loading error
            manager = new TaskManager();
        }
        TimerTask savingTask = new TimerTask() {
            @Override
            public void run() {
                storage.saveFile(manager);
            }
        };
        saveTimer.schedule(savingTask, 0, saveFrequency); //update at 2 seconds


    }

    /**
     * Executes and start running the Duke chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String next = ui.readInstructions();
                ui.showLine();
                ui.printOutput(Parser.parse(next, manager));
                ui.showLine();
                isExit = Parser.isExit();

            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError(" OPPPS!!!!That is not a number!!!!!!!!!!");
            } finally {
                ui.spacer();
            }
        }

        System.exit(0);

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt", 2000).run();

    }
}
