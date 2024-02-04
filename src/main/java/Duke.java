import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private Ui ui;
    private Storage storage;
    private Tasklist tasks;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        ui.printWelcomeMessage();
        Parser parser = new Parser();
        boolean isBye = false;
        while (!isBye) {
            String command = ui.getCommand();
            isBye = parser.parseCommand(command, ui, storage);
        }
        ui.printByeMessage();
    }
}
