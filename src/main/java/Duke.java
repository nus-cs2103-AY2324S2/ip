
import duke.IOHandler;
import duke.Parser;
import duke.Storage;
import duke.task.TaskList;
import exceptions.FileIOException;
import ui.Ui;

public class Duke {
    private Ui ui;
    private final IOHandler ioHandler = new IOHandler();
    private final TaskList taskList;
    private Parser parser = new Parser();
    private final Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.readFromFile());
    }

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