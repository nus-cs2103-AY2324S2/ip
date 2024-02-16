package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;


/**
 * Class Duke that is the main class that helps to run the program
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String filePath = "./data/list.txt";



    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
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
            } catch (NoCmdException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String userInput) {
        String Response = "";
        try {
            Command newCommand = Parser.parse(userInput);
            Response = newCommand.execute(tasks, ui, storage);
        } catch (NoCmdException e) {
            throw new RuntimeException(e);
        }
        return Response;


    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
