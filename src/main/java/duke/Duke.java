package duke;

import java.io.IOException;
import java.util.ArrayList;
import duke.commands.ParseCommand;
import duke.commands.Command;
import duke.commands.CommandException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.Task;

public class Duke {

    public static void main(String[] args) throws IOException {
        ArrayList<Task> tasks = Storage.init();
        Ui ui = new Ui();
        ui.printWelcomeMsg();
        while (true) {
            try {
                String[] strArrCommand = ui.readCommand();
                Command command = ParseCommand.parse(strArrCommand, tasks);
                command.execute(tasks, strArrCommand);
            } catch (CommandException | IOException e) {
                Ui.printOutput(e.getMessage());
            }
        }

    }
}
