package Echo;

import Echo.Storage.Storage;
import Echo.Ui.Ui;

import java.io.File;




public class Echo {
    private TaskManager taskManager;
    private Storage storage;
    private Ui ui;
    private final String FILE_PATH = "." + File.separator + "data" + File.separator + "echo.txt";

    public Echo() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        this.taskManager = new TaskManager(storage);
    }

    public static void main(String[] args) {
        Echo echo = new Echo();
        Ui.greetUser();
        echo.start();
        Ui.endConversation();
    }

    public void start() {
        Ui.startConversation(this.taskManager);
    }

    public void echoCommand(String command) {
        System.out.println("____________________________________________________________");
        System.out.println(command);
        System.out.println("____________________________________________________________");
    }
}
