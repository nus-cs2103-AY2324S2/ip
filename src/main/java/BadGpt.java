import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BadGpt {
    private static final String NAME = "BadGPT";
    private TaskList taskList;
    private Ui ui;
    private FileManager fileManager;

    public BadGpt() {
        taskList = new TaskList();
        ui = new Ui();
        fileManager = new FileManager();
    }

    public static void main(String[] args) {
        new BadGpt().run();

        Scanner sc = new Scanner(System.in);
    }

    public void run() {
        ui.sayHi(NAME);

        fileManager.loadFile();
        fileManager.readFile(taskList);

        while (true) {
            String in = ui.read();
            Parser.parse(in, this, taskList, ui, fileManager);
        }
    }

    public void bye() {
        taskList.writeChanges(fileManager);
        ui.sayBye();
        System.exit(0);
    }
}
