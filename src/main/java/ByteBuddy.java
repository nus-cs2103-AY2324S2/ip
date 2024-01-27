import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

import static ui.Ui.printWithSolidLineBreak;


public class ByteBuddy {
    // class variables
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    public static void main(String[] args) throws DukeException {
        new ByteBuddy().run();
    }

    public ByteBuddy() throws DukeException {
        ui = new Ui();
        storage = new Storage();

        try {
            taskList = storage.load();
        } catch (DukeException e) {
            taskList = new TaskList();
            throw new DukeException("Error loading the list from output.txt");
        }
    }

    public void run() {
        Ui.printStartMessage();

        // repeating user commands
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                printWithSolidLineBreak(e.getMessage());
            }
        }

        // bye
        Ui.printByeMessage();
    }
}
