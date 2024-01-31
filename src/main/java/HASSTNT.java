import java.io.FileNotFoundException;
import java.io.IOException;


public class HASSTNT{

    private final Storage  storage;
    private TaskList tasks;
    private final Ui ui;

    public HASSTNT(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.displayErrorMessage("Error Loading File from: " + e.getMessage());
            ui.display("Cannot load last session files, so now the list is empty");
            tasks = new TaskList();
        }
    }

    public void run() {
        // Display welcome message
        ui.displayWelcomeMessage();

        // Continuously listen for user input
        while (true) {
            String userInput = ui.getUserInput();
            if(Parser.IsexitCommand(userInput)){
                ui.displayByeMessage();
                try {
                    storage.saveTasks(tasks);
                } catch (IOException e) {
                    System.out.println("Error cannot save files to the following path : " + e.getMessage());
                }
                break;
            }
            if(Parser.isDisplayCommand(userInput)){
                ui.displayCommand();
            }
            // Process user input
            try {
                // Parse user input and execute corresponding actions
                Command c = Parser.parseInput(userInput);
                c.execute(tasks,ui);
            } catch (IllegalArgumentException e) {
                // Handle any exceptions or errors
                ui.displayErrorMessage(e.getMessage());
            }

            // Save tasks to file after each command
        }

    }


    public static void main(String[] args) {
        new HASSTNT("./src/list_log").run();
    }
}

