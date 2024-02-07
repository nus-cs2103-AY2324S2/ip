package duke.main;
import duke.exception.*;

import java.io.IOException;

public class TalkingBox {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public TalkingBox(String path) {
        taskList = new TaskList();
        ui = new Ui(taskList);
        parser = new Parser(this.taskList, this.ui);
        try {
            storage = new Storage(taskList, path);
        } catch (FileNotFoundException f) {
            this.ui.printException(f);
        }
    }

    public void run() throws IOException {
        this.ui.printWelcomeMessage();
        boolean isExit = false;
        boolean isError = false;
        while (!isExit && !isError) {
            String command = this.ui.readLine();
            isExit = this.parser.isExit(command);
            try {
                this.parser.parse(command);
            } catch (UnknownInputException e) {
                this.ui.printException(e);
                isError = true;
            }
        }
        
        this.storage.store();
        this.ui.printExitMessage();
    }

    public static void main(String[] args) throws IOException {
        new TalkingBox("save.txt").run();
    }
}