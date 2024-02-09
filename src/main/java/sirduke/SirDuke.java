package sirduke;

import command.Command;
import dukeexceptions.DukeException;
import dukeexceptions.InvalidCmd;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * This class represents the Sir Duke Chatbot
 */
public class SirDuke {
    /** The TaskList object that Sir Duke will use to store his tasks */
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    public SirDuke(String filePath) {
        ui = new Ui();
        // have storage and taskList to have a bidirectional navigability
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage, ui);
        parser = new Parser();
        ui.setTasks(tasks);
        storage.setTasks(tasks);
        storage.load();
    }

    public void run() {
        ui.welcome();
        Boolean isBye;
        while (true) {
            try {
                String cmd = ui.readCommand();
                Command c = parser.parseCommand(cmd);
                // i set tasks and ui here so that I dont have to pass it to parse command
                c.setTasksAndUi(tasks, ui);
                c.execute();
            } catch (DukeException e) {
                ui.errorMsg(e.getMessage());
            }
        }
    }

    /**
     * Runs Sir Duke
     *
     * @param args String arguments to be passes by User
     * @throws InvalidCmd If command is not in Commands
     */
    public static void main(String[] args) {
        new SirDuke("data/sirDuke.txt").run();
    }
}
