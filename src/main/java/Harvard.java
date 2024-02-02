import harvard.Ui;
import harvard.Storage;
import harvard.Parser;
import harvard.TaskList;
import java.util.Scanner;

public class Harvard {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Harvard(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Parser parser = new Parser(storage, tasks, ui);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String echoInput = scanner.nextLine();

            if (echoInput.equals("bye")) {
                break;
            }

            parser.parse(echoInput);
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Harvard(System.getProperty("user.dir") + "/data/harvard.txt").run();
    }


}

