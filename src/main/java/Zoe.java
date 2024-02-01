import java.util.Scanner;
public class Zoe {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    public Zoe() {
        ui = new Ui();
        Loader r = new Loader("./data/", "SavedTasks.txt");
        tasks = new TaskList(r.loadTasks());
        parser = new Parser(tasks);
    }

    public void run() {
        ui.saysHi();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            parser.process(command);
            command = scanner.nextLine();
        }
        ui.saysBye();
        Saver s = new Saver(tasks.getTasks());
        s.saveTo("./data/");
        scanner.close();
    }
    public static void main(String[] args) {
        new Zoe().run();
    }
}
