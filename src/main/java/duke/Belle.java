package duke;

import java.util.ArrayList;

import duke.command.Command;

import duke.run.Parser;
import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;

import duke.others.BelleException;

/**
 * Manages tasks on a list in a chatbot form.
 */
public class Belle {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs Belle.
     *
     * @param filePath Specifies filepath to store data.
     */
    public Belle(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList(new ArrayList<>());
        try {
            tasks = new TaskList(storage.loadList());
        } catch (BelleException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs Belle chatbot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String currInput = ui.readInput();
                Command com = parser.parse(currInput);
                com.execute(storage, tasks, ui);
                isExit = com.isExit();
            } catch (BelleException e) {
                ui.printError(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new Belle("src/main/data/belleList.txt").run();
    }
}
