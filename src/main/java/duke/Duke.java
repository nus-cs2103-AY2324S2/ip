package duke;


import duke.Ui.Ui;
import duke.exceptions.FileIOException;
import duke.task.TaskList;
public class Duke {
    private Ui ui;
    private final IOHandler ioHandler = new IOHandler();
    private final TaskList taskList;
    private Parser parser = new Parser();
    private final Storage storage;

    /**
<<<<<<< HEAD
     * Constructs a simple Duke-Object.
=======
     * Constructs a simple Duke Object.
>>>>>>> branch-Level-9
     * @param filePath Stores the file path for loading and saving.
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.readFromFile());
    }

    /**
     * It runs the Duke Application.
     * Displays Contents, handle the user inputs and commands until user exits.
     */
    public void run() throws FileIOException {
        ioHandler.welcomeMessage();
        boolean exit = false;
        while (!exit) {
            exit = !parser.parse(ioHandler.typeMessage(), ioHandler, taskList, storage);
        }
    }

    public static void main(String[] args) {

        try {
            new Duke("data/duck.txt").run();
        } catch (FileIOException e) {

            System.out.println(e.getMessage());
        }
    }
}