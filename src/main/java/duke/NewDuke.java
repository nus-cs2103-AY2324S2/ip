package duke;

public class NewDuke {
    private Storage storage;
    private UI ui;
    private Save saver;
    private Parser parser;
    public NewDuke(String filePath) {
        ui = new UI();
        storage = new Storage();
        saver = new Save(filePath);
        parser = new Parser(storage);
        saver.loadData(storage);
    }

    public void run() {
        ui.onStart();
        parser.run();
        ui.onEnd();
        saver.saveData(storage);
    }

    public static void main(String[] args) {
        new NewDuke("data/duke.txt").run();
    }
}
