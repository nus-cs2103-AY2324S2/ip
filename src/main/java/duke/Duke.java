package duke;

import duke.Command.Command;
import duke.Tasks.TaskList;
import java.util.ArrayList;

public class Duke {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
/*
    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
   /* public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }
    */

    public Duke() {
        String filePath = "./data/botYue.txt";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(String.valueOf(e));
            tasks = new TaskList(new ArrayList<>());
            //return String.valueOf(e);
        }
    }


    /**
     * Runs the chatbot, displaying welcome message and processing user commands.
     */
    /*public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());

            }
        }
    }*/
/*
    public static void main(String[] args) {
        new Duke("./data/botYue.txt").run();
    }*/


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException {
        try {
            Command com = this.parser.parse(input);
            String res = com.execute(this.tasks, this.storage);
            return res;
        } catch (DukeException e) {
            String response = e.getMessage();
            return response;
        }
    }

}
