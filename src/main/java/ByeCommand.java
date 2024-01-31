import java.util.*;
import java.io.*;

public class ByeCommand extends Command {

    public static final String COMMAND = "bye";

    @Override
    public void execute() throws DukeException, IOException {
        Storage storage = new Storage();
        storage.saveFile(tasks);
        Ui.exit();
    }
}
