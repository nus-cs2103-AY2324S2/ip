package duke;

import java.util.Timer;
import java.util.TimerTask;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;


/**
 * Duke Chatbot to prompt the user on what task would they like to keep track of
 */

public class Duke {

    private static boolean isExit;
    private final Storage storage;
    private TaskManager manager;

    /**
     * Setups and initialises the chatbot, with an auto save feature every 2 seconds.
     *
     * @param filePath      string path from root where the user wants to store their task.
     * @param saveFrequency input in milliseconds to indicate the frequency of autosave.
     */
    public Duke(String filePath, int saveFrequency) {

        Timer saveTimer = new Timer();
        isExit = false;
        storage = new Storage(filePath);
        try {
            manager = storage.loadFile();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            manager = new TaskManager();
        }
        //assert false : "This is impossible to continue";
        TimerTask savingTask = new TimerTask() {
            @Override
            public void run() {
                storage.saveFile(manager);
            }
        };

        saveTimer.schedule(savingTask, 0, saveFrequency); //update at 2 seconds

    }

    public String getResponse(String input) {
        String response;
        try {
            response = Ui.convertToString(Parser.parse(input, manager));
            isExit = Parser.isExit();

        } catch (DukeException e) {
            response = Ui.handleError(e.getMessage());
        }
        return response;

    }

    /**
     * Checks if you should exit your program.
     *
     * @return Returns the state if you should exit your program.
     */
    public boolean hasExit() {
        return isExit;
    }


}
