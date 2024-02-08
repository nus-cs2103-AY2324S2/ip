package youdon;

public class Youdon {
    public static void main(String[] args) {

        // initialise ui and storage (filepath = "./data/save.txt")
        Ui ui = new Ui();
        Storage storage = new Storage("./data/save.txt");
        TaskList taskList =  new TaskList(storage.loadData());
        Parser parser = new Parser(ui, taskList, storage);

        // chatbot welcome message
        ui.printWelcomeMsg();

        // parse input
        parser.parse();

    }
}