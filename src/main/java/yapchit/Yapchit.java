package yapchit;

import yapchit.yapchitexceptions.YapchitException;

/**
 * Yapchit is a bot that allows users to create and manage their tasks. The Yapchit class
 * is the entry point into the program and encapsulates a number of other classes that
 * enable the functionality of Yapchit.
 *
 * @author Archit Goswami
 * @version 1.0
 * @since 2024-02-01
 */
public class Yapchit {

    /**
     * List of operations that the Yapchit functionality can handle. Operations
     * are keywords that the user can enter as Yapchit input.
     * Yapchit additionally stores tasks across restarts.
     */
    enum Operations {
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        TODO,
        DELETE,
        FIND;
    }

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Handler handler;
    private boolean isBye;
    private String filePath;

    /**
     * Constructor of a Yapchit object. Initiates instance of components of Yapchit and loads
     * tasks from existing file (if any).
     *
     * @param filePath The file path to the storage file used to keep track of tasks.
     */
    public Yapchit(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isBye = false;
        this.parser = new Parser();
        this.handler = new Handler();
        this.filePath = filePath;

        try{
            this.tasks = storage.importFromFile(filePath, ui, handler, parser);
        } catch(YapchitException e){
            ui.printTasklistLoadError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Initiates core functionality of the bot by running a main loop that harnesses relevant components
     * to accept, handle inputs and output a response.
     *
     * Main loop ends when the 'bye' command is entered by the user.
     */
    public void run(){

        ui.printIntro();
        String input = ui.scanInput();
        while(!handler.checkIsBye(input)){
            try{
                Operations k = parser.parseInputOperation(input);
                String[] parts = parser.parseInputParts(input);
                handler.handleOperation(input, k, tasks, ui, parser);
            } catch (YapchitException e){
                Ui.print(e.getMessage());
            } finally {
                input = ui.scanInput();
            }
        }
        storage.updateFile(filePath, this.tasks);
        ui.printOutro();
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        Yapchit bot = new Yapchit("./src/main/data/dataStore.txt");
        bot.run();
    }

}
