
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        
        this.ui = new Ui();

        try {
            this.storage = new Storage(filePath);
            this.tasks = storage.readSaveData(ui);
        } catch (FileNotFoundException e) {
            this.ui.botPrint("Error reading file: " + e.getMessage() + "\nMaking new task list");
            this.tasks = new TaskList();
        } catch (IOException e) {
            this.ui.botPrint("Save file could not be generated: " + e.getMessage() + "\nMaking new task list");
            this.tasks = new TaskList();
        } 

    }

    public void run() {
        ui.startupMessage();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.nextCommand();
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (NoSuchCommandException e) {
                ui.botPrint(e.getMessage());
            } catch (TaskCreationException e) {
                ui.botPrint("Error Creating Task: " + e.getMessage());
            } catch (DateTimeParseException e) {
                ui.botPrint("Error parsing datetime: " + e.getMessage() + "\nUse the format \"DD/MM/YYYY, HH:MM\" to enter date and time." );
            } catch (IndexOutOfBoundsException e) {
                ui.botPrint(e.getMessage());
            } catch (NumberFormatException e) {
                ui.botPrint("Invalid selection for marking or deletion: " + e);
            } catch (TaskModificationException e) {
                ui.botPrint("Error Modifying Task: " + e.getMessage());
            } catch (IOException e) {
                ui.botPrint(e.getMessage());
            }
        }
        
        ui.goodbyeMessage();
        ui.closeUi();
    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}
