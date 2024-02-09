package youdon;

public class Youdon {
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