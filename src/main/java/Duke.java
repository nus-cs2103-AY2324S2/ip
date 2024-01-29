import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(CommandType.FILEPATH.toString());
        this.storage.getStorageFromHardDisk(this.tasks);
    }


    /**
     * Starts communication with chatbot
     */
    public void startChat() {
        ui.welcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();

            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }


    public static void main(String[] args) {
        new Duke().startChat();
    }
}
