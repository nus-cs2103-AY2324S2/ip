package chingu;

import chingu.command.Command;
import chingu.exception.ChinguException;
import chingu.exception.NoCommandException;
import chingu.task.TaskList;

import java.io.IOException;

/**
 * Class Duke that is the main class that helps to run the program
 */
public class Chingu {

    private static final String FILEPATH = "store/list.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Chingu() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Helps to run the Duke program
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NoCommandException e) {
                ui.showError(e.getMessage());
            } catch (ChinguException e) {
                throw new RuntimeException(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Collect Command from the user each time and respond to user's command to Chingu
     *
     * @param userInput which consist of command
     * @return string Response to the command of the user
     */
    public String getResponse(String userInput) {
        String Response = "";
        try {
            Command newCommand = Parser.parse(userInput);
            Response = newCommand.execute(tasks, ui, storage);
        } catch (NoCommandException e) {
            return ui.showError(e.getMessage());
        } catch (ChinguException e) {
            throw new RuntimeException(e);
        }
        return Response;
    }

    public static void main(String[] args) {
        new Chingu().run();
    }

}
