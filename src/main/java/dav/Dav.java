package dav;

/**
 * Dav application class for managing tasks.
 */
public class Dav {

    private static final String FILE_PATH = "./data/dav.txt";
    private static TaskList tasks;
    private static Ui ui;
    private static Storage storage;

    /**
     * Constructor for Dav class. Initializes UI, storage, and task list.
     */
    public Dav() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DavException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        return Parser.parseUserInput(input, tasks, ui, storage);
    }
}
