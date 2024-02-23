package tars;


/**
 * Main chatbot program
 */

public class Tars {
    private static final String FILE_PATH = "data/tars.txt";
    private Ui ui;
    //List<Task> list;
    private TaskList tlist;

    private Storage storage = new Storage();


    /**
     * Constructs an instance of Duke
     */

    public Tars() {
        ui = new Ui();
        tlist = new TaskList();
        storage.load(tlist);
        ui.greet();

    }


    public String getResponse(String input) {
        try {
            return UserHandler.chat(input, tlist, storage);
        } catch (TarsException e) {
            return e.getMessage();
        }
    }

}
