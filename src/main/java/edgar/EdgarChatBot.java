package edgar;

import commands.Command;
import util.Parser;
import util.Ui;
import util.TaskList;
import util.Storage;
import java.io.IOException;

public class EdgarChatBot {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public EdgarChatBot() {
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public void startBot() {
        try {
            storage.loadFromFile(this.taskList);
        } catch (IOException e) {
            System.out.println("Oops! There was an error loading from file.");
        }
        this.ui.printGreetings();
        String userInput;
        do {
            userInput = this.ui.nextCommand();
            Command c = this.parser.firstParse(userInput);
            ui.printDivider();
            c.execute(taskList, ui, storage);
            ui.printDivider();
        } while (!userInput.equals("bye"));
    }
}

