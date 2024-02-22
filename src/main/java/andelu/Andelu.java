package andelu;

/**
 * The Main class of this program.
 */
public class Andelu {


    /** The Storage Manager to load and store the data to a file. */
    private Storage storage;

    /** The TaskList Object to store the list of tasks. */
    private TaskList tasks;

    /** The Ui Manager to handle the interaction with the user. */
    private Ui ui;

    /**
     * Creates a default constructor.
     */
    public Andelu() {}

    /**
     * Creates a constructor and performs all the necessary work.
     *
     * @param filePath the name of the text file to be stored.
     */
    public Andelu(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (AndeluException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the ui.
     *
     * @return Ui.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Returns the TaskList.
     *
     * @return TaskList.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Return the Storage.
     *
     * @return Storage.
     */
    public Storage getStorage() {
        return storage;
    }

}









