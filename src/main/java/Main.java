import java.io.FileNotFoundException;

public class Main {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String filePath;
    
    public Main(String filePath) {
        System.out.println("main run");
        this.filePath = filePath;
        taskList = new TaskList();
        ui = new Ui(taskList);
        storage = new Storage(filePath, taskList);
        try {
            taskList.initialisePrevTaskList(storage.readFromFile());

        } catch (FileNotFoundException e) {
//            ui.showLoadingError();
            System.out.println(e.getMessage());
//            tasks = new TaskList();
        }
    }

    public void run() {
        ui.start();
        // tasks added to tasklist already from above

        // then we store it
        storage.writeToFile(filePath);

    }

    public static void main(String[] args) {
        new Main("data/hal.txt").run();
    }
}
