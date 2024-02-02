import java.util.ArrayList;

public class Duchess {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;


    private static final String FILE_PATH = "./data/duchess.txt";

    public Duchess() throws DuchessException {
        storage = new Storage(FILE_PATH);
        taskList = new TaskList();
        ArrayList<Task> tasksStored = storage.loadData();
        if (!tasksStored.isEmpty()) {
            taskList = new TaskList(storage.loadData());
        }
        ui = new Ui();
    }

    public static void main(String[] args) {
        try {
            new Duchess().run();
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }

    }

    public void run() {
        ui.printOpeningGreeting();
        try {
            ui.printEcho(taskList, storage);
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        } finally {
            //Close scanner
            ui.closeScanner();
        }
    }


}
