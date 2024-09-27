package jelly;

/**
 * Jelly class
 */
//enum is not required
public class Jelly {

    private static final String farewell = "Bye. Hope to see you again soon!";
    private static String path = "jelly.txt";
    private TaskList list;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    /**
     * Default constructor for Jelly
     */
    public Jelly() {

        storage = new Storage(path);

        ui = new Ui();

        try {

            list = new TaskList(storage.load());

        } catch (JellyException e) {

            list = new TaskList();
            ui.printLoadingError(e);
        }

        parser = new Parser(list, ui);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.read(input);
    }

    /**
     * Saves storage of tasks into txt file
     */
    public void saveStorage() {

        storage.save(list);
    }

    /**
     * @return Farewell message
     */
    public String getFarewell() {

        return farewell;
    }

}
