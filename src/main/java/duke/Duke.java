package duke;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Duke {

    //private static FileIO fileIO = new FileIO();
    private static Ui ui = new Ui();
    public Storage storage = new Storage("./data/logfile.txt");
    private static final File FILE_PATH = new File("./data/logfile.txt");
    private List<Task> l = storage.readFromFile();

    public TaskList taskList;
    public static Parser parser;

    /**
     * Constructs the Duke application with necessary initializations.
     *
     * @throws IOException If an input or output exception occurred.
     */
    public Duke() throws IOException {
        this.taskList = new TaskList(storage, ui);
        this.parser = new Parser(ui, storage, taskList);
        l = storage.readFromFile();
    }

    /**
     * The main method is the entry point to the Duke application.
     * It starts the application and runs the main loop.
     *
     * @param args Unused.
     * @throws IOException If an input or output exception occurred.
     */
    public static void main(String[] args) throws IOException {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        Duke lucifer = new Duke();
        String user_word;

        while (true) {
            user_word = ui.readCommand();
            try {
                parser.processCommand(user_word);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

