import java.util.Scanner;

public class Duke {


    private TaskList tasks;

    private Storage storage;

    private static Parser p;

    private static Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.storage.loadTasks(this.tasks);
    }

    public void run() {
        ui.printWelcome();
        p = new Parser();
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            isContinue = p.parse(scanner, tasks, ui, this.storage);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
