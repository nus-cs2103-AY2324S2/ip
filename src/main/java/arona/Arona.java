package arona;

import java.io.PrintStream;
import java.io.OutputStream;

/**
 * Main class where the program runs. Users can input commands
 * from console and respond to prompts
 */
public class Arona  {
    private String name;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor to initialize the UI and fetch data from storage
     *
     * @throws TaskException if file cannot be found
     */
    public Arona() throws TaskException {
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }
        }));

        name = "";
        storage = new Storage();
        ui = new Ui();

        try {
            taskList = new TaskList(storage.load(), ui);
        } catch (FileException e) {
            taskList = new TaskList(ui);
        }
        System.setOut(originalOut);
    }


    /**
     * Allow for users to enter commands to perform various tasks,
     * such as add, delete, mark, unmark tasks
     *
     * @throws FileException if file cannot be found
     * @throws AronaException if there are errors in program logic
     * @throws IndexOutOfBoundsException if user enter a task number
     *      that does not exist
     */
    public void run(String fullCommand) {
        try {
            Command command = Parser.parseCommand(fullCommand);
            command.execute(taskList, ui, storage);

        } catch (FileException | AronaException | TaskException | IndexOutOfBoundsException e) {
            ui.setResponse(e.getMessage());
        }

    }

    /**
     * Obtain the current
     * @return
     */
    public String getResponse() {
        return ui.getResponse();
    }

    public void greetUser() {
        ui.greetings();
    }

    public static void main(String[] args) {

    }
}
