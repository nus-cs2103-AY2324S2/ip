import java.util.Scanner;

public class Lia {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Lia() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser(ui, tasks);

        try {
            tasks.setTasks(storage.loadTasks());
        } catch (LiaException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        ui.showWelcomeMessage();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("exit")) {
                break;
            }

            parser.parseCommand(input);
            storage.saveTasks(tasks.getTasks());
        }

        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Lia().run();
    }
}