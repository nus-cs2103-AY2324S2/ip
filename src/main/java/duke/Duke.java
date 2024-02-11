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

//    public static void main(String[] args) {
//        launch(args);
//
//        new Duke("data/tasks.txt", 2000).run();
//
//    }

    //

    public String getResponse(String input) {
        String response;
        try {
            response = Ui.convertToString(Parser.parse(input, manager));
            isExit = Parser.isExit();

        } catch (DukeException e) {
            response = Ui.handleError(e.getMessage());
        } catch (NumberFormatException e) {
            response = " OPPPS!!!!That is not a number!!!!!!!!!!";
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

    /**
     * Executes and start running the Duke chatbot.
     */
    /*
    public void run() {
        //ui.showWelcome();
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
                storage.saveFile(manager);
            }
        }

        System.exit(0);

    }
    */

}
