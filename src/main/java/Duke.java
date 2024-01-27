import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exceptions.ChatException;

import java.util.Scanner;

public class Duke {

    private TaskList taskList;
    private final Ui ui;

    private Duke() {
        this.ui = new Ui();
        // Inspired and referenced from https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/project.html#a-moreoop
        try {
            this.taskList = new TaskList(Storage.load());
        } catch (ChatException e) {
            this.ui.printError(e);
            this.taskList = new TaskList();
        }

    }

    public void loop() {
        String output = null;
        String line = ui.readCommand();
        boolean isExit = false;
        // Inspired and referenced from https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/project.html#a-moreoop
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                String result = c.execute(this.taskList, ui, null);
                ui.respondUser(result);
                ui.showLine();
                isExit = c.isExit();
            } catch (ChatException e) {
                ui.printError(e);
            } finally {
                taskList.save();
            }
        }
    }

    public void end() {
        this.ui.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.loop();
        duke.end();
    }
}
