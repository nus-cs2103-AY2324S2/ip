package plato;

import java.util.Timer;
import java.util.TimerTask;

import plato.parser.Parser;
import plato.storage.Storage;
import plato.task.TaskManager;
import plato.ui.Ui;


/**
 * Chatbot to prompt the user on what task would they like to keep track of
 */

public class Plato {

    private static boolean isExit;
    private final Storage storage;
    private TaskManager manager;

    /**
     * Setups and initialises the chatbot, with an auto save feature every 2 seconds.
     *
     * @param filePath      string path from root where the user wants to store their task.
     * @param saveFrequency input in milliseconds to indicate the frequency of autosave.
     */
    public Plato(String filePath, int saveFrequency) {

        Timer saveTimer = new Timer();
        isExit = false;
        storage = new Storage(filePath);
        try {
            manager = storage.loadFile();
        } catch (PlatoException e) {
            System.out.println(e.getMessage());
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

    public String getResponse(String input) {
        String response;
        try {
            response = Ui.convertToString(Parser.parse(input, manager));
            isExit = Parser.isExit();

        } catch (PlatoException e) {
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
