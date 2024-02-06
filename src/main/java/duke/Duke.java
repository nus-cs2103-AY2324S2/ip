package duke;
import java.util.ArrayList;

/**
 * Main class
 */
public class Duke {
    private Storage storage;
    private ArrayList<Task> taskList = new ArrayList<>();
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    
    /**
     * Constructor for Duke
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(taskList);
        storage.loadTaskList();
        this.tasks = new TaskList(storage, taskList);
        this.parser = new Parser(tasks);
    }

    /**
     * Starts the program
     * 
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     * @throws StringIndexOutOfBoundsException if input is not a number
     */
    public void start() {

        ui.showGreeting();

        String input = ui.getUserInput();
        
        while (true) {
            ui.printLine();
            
            if (!parser.executeUserInput(input)) {
                break;
            }

            input = ui.getUserInput();
        }
        ui.showGoodbye();
        ui.closeScanner();
    }

    /**
     * Main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        Duke mainApp = new Duke();
        mainApp.start();
    }    
}