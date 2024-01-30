import java.util.Scanner;
public class HeadCube {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    public HeadCube() {
        ui = new Ui();
        storage = new Storage(ui);
        tasks = new TaskList();
    }
    public void run() {
        storage.load(tasks);
        ui.greet();
        this.parser = new Parser(ui, tasks, storage);
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        while (!string.equals("bye")) {
            parser.parse(string);
            string = scanner.nextLine();
        }
        ui.exit();
    }
    public static void main(String[] args) {
        new HeadCube().run();
    }
}