package arona;

import java.io.PrintStream;
import java.io.OutputStream;

/**
 * Main class where the program runs. Users can input commands
 * from console and respond to prompts
 */
public class Arona {
    private String name;
    private String filePath = "./src/data/tasklist.txt";
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

        this.name = "";
        this.storage = new Storage(filePath);
        try {
            this.ui = new Ui();
            this.taskList = new TaskList(storage.load(), ui);
        } catch (FileException e) {
            this.taskList = new TaskList(ui);

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
    public void run() {
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parseCommand(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (FileException e) {
                System.err.println(e.getMessage());
            } catch (AronaException e) {
                System.err.println(e.getMessage());
            } catch (TaskException e) {
                System.err.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws TaskException {
        Arona arona = new Arona();
        arona.run();
    }
}
