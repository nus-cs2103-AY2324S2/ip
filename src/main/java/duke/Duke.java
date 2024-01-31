package duke;

/**
 *  * Represents the main class of the application.
 *
 *
 * @author Loh Jin Hun
 *
 */
public class Duke {

    /**
     * The storage unit of the chatbot that saves and loads from the hard drive
     */
    private static Storage storage;

    /**
     * The temporary storage/tasklist that has operations to add/delete tasks in the list
     */
    private static TaskList list;

    private static Ui ui;
    private static Parser parser;
    private static final String FILE_PATH = "data/duke.txt";











    public static void main(String[] args) throws DukeException {
        ui = new Ui();
        ui.intro();
        storage = new Storage(FILE_PATH);
        list = new TaskList(storage);// array to store tasks given
        parser = new Parser(list, storage);
        ui.read_commands(parser);



                }
        }




