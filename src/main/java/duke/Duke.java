package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;


/**
 * Represents a ChatBot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;
    /**
     * Constructs a new Duke instance to run.
     * Reads from file to set up chatbot.
     *
     * @param file Name of file to save data and read from.
     * @param name Name of chatbot.
     * @param logo Logo of chatbot.
     */
    public Duke(String file, String name, String logo) {
        ui = new Ui(name, logo, System.in);
        try {
            storage = new Storage(file);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts up the chatbot with welcome message and start taking in commands until bye command is inputted.
     */
    public void run() {
        ui.showWelcome();
        isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns response corresponding to input.
     *
     * @param input User input.
     * @return Message from chatbot.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns true if current command is bye.
     *
     * @return Value of isExit.
     */
    public boolean isExit() {
        return isExit;
    }

    public String getLogo() {
        return ui.showWelcome();
    }

    /**
     * Starts Duke chatbot on command line. Entry point of application.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        String logo = "\t  ____   __  _  _  __    ___  ___  ____  ___  ____\n\t"
                + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n\t"
                + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n\t"
                + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";

        new Duke("Duke.txt", "JavAssist", logo).run();
    }
}
