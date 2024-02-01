package jade;

import jade.commands.Command;
import jade.data.TaskList;
import jade.exception.JadeException;
import jade.parser.Parser;
import jade.ui.Ui;
import jade.storage.Storage;


public class Jade {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

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

    public void run() {
        ui.launch();
        boolean exitProg = false;
        while (!exitProg) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                exitProg = c.isExit();
            } catch (JadeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
