import command.Command;
import exception.TobiasException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Tobias {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the bot Tobias.
     *
     * @param filePath The relative string file path of the saved data.
     * */
    public Tobias(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.createLocalStorage();
        try {
            tasks = storage.localToCurrent();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String input) throws TobiasException{

        String response = "";
        try {
            Command c = Parser.parseCommands(input.trim(), tasks);
            response = c.execute(tasks, ui, storage);
        } catch (TobiasException e) {
            response = e.printMessage();
        }

        return response;
    }

}
