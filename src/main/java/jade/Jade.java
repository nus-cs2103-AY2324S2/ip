package jade;

import jade.commands.Command;
import jade.data.TaskList;
import jade.exception.JadeException;
import jade.parser.Parser;
import jade.ui.Ui;
import jade.storage.Storage;

/**
 * A <code>Jade</code> object is a personal chatbot assistant that
 * helps users with task management with the option of adding dates available.
 *
 * @author Feiyang Shang
 * @version v1.0
 * @since 2024-01-23
 */
public class Jade {
    private TaskList taskList; // list that stores all user tasks
    private Storage storage; // storage object to load from and save to local file
    private Ui ui; // user interface for reading input and printing output

    /**
     * Class constructor specifying the local filepath that stores user tasks.
     */
    public Jade(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (JadeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        Jade myJade = new Jade("data/jadeList.txt");
        myJade.run();
    }

    /**
     * Receives user input to run the jade object.
     * When user enters the exit command, the program terminates.
     */
    public void run() {
        ui.launch();
        boolean exitProg = false;
        while (!exitProg) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                exitProg = c.shouldExit();
            } catch (JadeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
