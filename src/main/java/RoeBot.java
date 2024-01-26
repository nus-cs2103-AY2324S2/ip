import commands.Command;
import services.parser.Parser;
import services.Storage;
import services.TaskList;
import services.UI;

import java.io.IOException;

/**
 * Represents a RoeBot.
 */
public class RoeBot {
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    public RoeBot() {
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.ui = new UI();
        this.parser = new Parser();
    }

    public void start() {
        try {
            storage.loadTasks(this.taskList);
        } catch (IOException e) {
            System.out.println("FIX THIS ERROR");
        }
        this.ui.printIntroMessage();
        String userInput;
        do {
            userInput = this.ui.nextCommand();
            Command c = this.parser.parseCommand(userInput);
            c.execute(taskList, ui, storage);
            ui.printHorizontalLine();
        } while (!userInput.equals("bye"));
    }

}
