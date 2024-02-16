package yapchit.yapchitbackend;

import yapchit.yapchitexceptions.YapchitException;
import yapchit.yapchitui.Yapchit;

public class YapchitBackend {

    /**
     * List of operations that the Yapchit functionality can handle. Operations
     * are keywords that the user can enter as Yapchit input.
     * Yapchit additionally stores tasks across restarts.
     */
    public enum Operations {
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        TODO,
        DELETE,
        FIND,
        UPDATE;
    }

    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Handler handler;
    private boolean isBye;
    private String filePath;

    /**
     * Constructor of a YapchitBackend object. Initiates instance of components of YapchitBackend and loads
     * tasks from existing file (if any).
     *
     * @param filePath The file path to the storage file used to keep track of tasks.
     */
    public YapchitBackend(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isBye = false;
        this.parser = new Parser();
        this.handler = new Handler();
        this.filePath = filePath;
    }


    /**
     * Initiates core functionality of the bot by running a main loop that harnesses relevant components
     * to accept, handle inputs and output a response.
     *
     * Main loop ends when the 'bye' command is entered by the user.
     */
    public String run(String input) {
        assert this.ui != null;
        assert this.parser != null;
        assert this.handler != null;
        assert this.tasks != null;
        String retVal;

        try{
            YapchitBackend.Operations k = parser.parseInputOperation(input);
            retVal = handler.handleOperation(input, k, tasks, ui, parser, true);
            storage.updateFile(filePath, this.tasks);
        } catch (YapchitException e) {
            retVal = e.getMessage();
        }
        return retVal;
    }

    public String getIntro(){
        String errorMsg = "";

        try{
            this.tasks = storage.importFromFile(filePath, ui, handler, parser);
        } catch (YapchitException e) {
            errorMsg = ui.printTasklistLoadError();
            this.tasks = new TaskList();
        }

        String errorAndIntro = errorMsg + "\n" + ui.printIntro();
        return errorMsg == "" ? ui.printIntro() : errorAndIntro;
    }

    public String getOutro(){
        storage.updateFile(filePath, this.tasks);
        return ui.printOutro();
    }

    public boolean checkIsBye(String input){

        assert input instanceof String : "input should be a String instance";

        return handler.checkIsBye(input);
    }
}
