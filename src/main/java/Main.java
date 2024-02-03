import java.util.ArrayList;

/**
 * Main class
 */
public class Main {
    private Storage storage;
    private ArrayList<Task> taskList = new ArrayList<>();
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    

    public Main() {
        this.ui = new Ui();
        this.storage = new Storage(taskList);
        this.tasks = new TaskList(storage);
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
            
            if (!parser.parseInput(input)) {
                break;
            }

            input = ui.getUserInput();
        }
        ui.showGoodbye();
        ui.closeScanner();
    }

    public static void main(String[] args) {
        Main mainApp = new Main();
        mainApp.start();
    }    
}