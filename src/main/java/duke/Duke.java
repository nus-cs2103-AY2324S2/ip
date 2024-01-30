package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Duke instance to run.
     * Reads from file to set up chatbot.
     *
     * @param file Name of file to save data and read from.
     * @param fileParent Parent directory of file.
     * @param name Name of chatbot.
     * @param logo Logo of chatbot.
     */
    public Duke(String file, String fileParent, String name, String logo) {
        ui = new Ui(name, logo, System.in);
        storage = new Storage(file, fileParent);
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
        boolean isExit = false;
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
     *
     * Starts Duke chatbot. Entry point of application.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        String logo = "\t  ____   __  _  _  __    ___  ___  ____  ___  ____\n\t"
                + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n\t"
                + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n\t"
                + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";

        new Duke("Duke.txt", "./data", "JavAssist", logo).run();
    }
}