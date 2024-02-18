package whisper;

/**
 * The Whisper class represents the main application that interacts with the user, manages tasks, and handles input/output.
 */
public class Whisper {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    //private static final String FILE_PATH = "/Users/wengyan/Desktop/CS2103/ip/src/main/java/data/whisper.txt";
    private static final String FILE_PATH = "src/main/java/data/whisper.txt";

    //private static final String CURRENT_DIR = System.getProperty("user.dir");
    //public static final Path FILE_PATH = Paths.get(CURRENT_DIR, "data", "whisper.txt");


//    private static final String CURRENT_DIR = System.getProperty("user.dir");
//    public static final Path FILE_PATH = Paths.get(CURRENT_DIR, "data", "whisper.txt");

    /**
     * Constructs a new Whisper instance.
     *
//     * @param filePath The file path for storing and loading tasks.
     */
    public Whisper() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
//        ui.showWelcomeMsg();
        try {
            tasks = new TaskList(storage.load());
        } catch (WhisperException e) {
            ui.printLoadFileError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Whisper application, continuously accepting and processing user commands until an exit command is received.
     */
//    public void run() {
//        ui.showWelcomeMsg();
//        boolean isExit = false;
//
//        while (!isExit) {
//            try {
//                String input = ui.inputCommand();
//                Command command = Parser.parse(input);
//                command.execute(tasks.getTaskList(), ui, storage);
//                tasks = new TaskList(storage.load());
//                isExit = command.isExit();
//            } catch (WhisperException e) {
//                ui.printError(e.getMessage());
//            }
//        }
//    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input, ui);
            command.execute(tasks.getTaskList(), ui, storage);
            tasks = new TaskList(storage.load());
//            return "Command executed successfully.";
            return ui.getResponse();
        } catch (WhisperException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * The entry point for the Whisper application.
     *
     * @param args Command-line arguments (not used in this application).
     */
//    public static void main(String[] args) {
//        Whisper whisper = new Whisper(".data/whisper.txt");
//        whisper.run();
//    }
}
