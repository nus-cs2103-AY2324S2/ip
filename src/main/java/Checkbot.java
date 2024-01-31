import java.util.Scanner;

public class Checkbot {
    public static final String TASK_FILE_DIR = "./tasks.txt";

    private final TodoList todoList;
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    public Checkbot(String filePath) {
        this.storage = new Storage(filePath);
        this.todoList = this.storage.loadTasks();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        new Checkbot(TASK_FILE_DIR).run();
    }

    public void run() {
        ui.showWelcome();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            try {
                Command c = parser.parse(input);
                c.execute(todoList, storage, ui);
                if (c.isBye()) {
                    break;
                }
            } catch (CheckbotException e) {
                ui.print(e.getMessage());
            }
        }
    }
}
