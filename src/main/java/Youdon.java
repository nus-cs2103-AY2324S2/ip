public class Youdon {
    public static void main(String[] args) {

        // initialise ui and storage
        Ui ui = new Ui();
        Storage storage = new Storage("./src/main/data/save.txt");
        TaskList taskList =  new TaskList(storage.loadData());
        Parser parser = new Parser(ui, taskList, storage);

        // chatbot welcome message
        ui.printWelcomeMsg();

        // parse input
        parser.parse();

    }
}