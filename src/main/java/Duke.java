import java.io.IOException;
import java.util.ArrayList;
import commands.ParseCommand;
import commands.Command;
import commands.CommandException;
import storage.Storage;
import ui.Ui;
import tasks.Task;

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
