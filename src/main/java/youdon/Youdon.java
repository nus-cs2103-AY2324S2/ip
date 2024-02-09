package youdon;

/**
 * The main class that starts the Youdon chatbot application.
 */
public class Youdon {

    /**
     * The main method of the application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {

        // initialise ui and storage (filepath = "./data/save.txt")
        Ui ui = new Ui();
        Storage storage = new Storage("./data/save.txt");
        TaskList tasks =  new TaskList(storage.loadData());
        Parser parser = new Parser(ui, tasks, storage);

        // chatbot welcome message
        ui.printWelcomeMsg();

        // parse input
        parser.parse();

    }
}