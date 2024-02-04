import java.io.FileNotFoundException;

public class Main {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Main(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
//            tasks = new TaskList(storage.readFromFile());
            tasks = new TaskList(storage.readFromFile());

        } catch (FileNotFoundException e) {
//            ui.showLoadingError();
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.start();
    }

    public static void main(String[] args) {
        new Main("data/hal.txt").run();
    }
}
