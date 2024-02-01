import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
public class Quacky {
    private static TaskList tasks = new TaskList();

    private Storage storage;
    private UI ui;


    public Quacky(String filePath) {
        try {
            ui = new UI();
            storage = new Storage(filePath);
            // Use the filePath parameter instead of hardcoding
            tasks = storage.load();
        } catch (Storage.InvalidStorageFilePathException e) {
            System.out.println(e.getMessage());
        }
        catch (Storage.StorageOperationException e) {
            System.out.println(e.getMessage());
        }
    }
    public void run() {
        ui.showGreeting();
        while (ui.isRunning) {
            try {
                String command = ui.readCommand();
                Parser.parseCommand(command, tasks, ui);
            } catch (QuackyException e) {
                ui.showErrorMessage(e);
            }
        }
        try {
            storage.save(tasks);
        } catch (Storage.StorageOperationException e) {
            ui.showErrorMessage(e);
        }
    }

    public static void main(String[] args) {
        new Quacky("./data/data.txt").run();
    }
}
