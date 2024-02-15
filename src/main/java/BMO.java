import java.io.IOException;
import java.lang.String;
import java.util.Scanner;

public class BMO {

    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private boolean isExit = false;

    public BMO() {
        ui = new UI();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadData(), ui, storage);
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
            tasks = new TaskList(ui);
        }
    }

    public void run() {
        ui.greet();
        ui.printTutorial();

        Scanner sc = new Scanner(System.in);
        do {
            String input = sc.nextLine().toLowerCase().trim();
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.printErrInvalidCommand();
            }
        } while (!isExit);
    }

    public static void main(String[] args) {
        new BMO().run();
    }
}
