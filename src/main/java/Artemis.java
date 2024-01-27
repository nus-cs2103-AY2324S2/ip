import java.util.Scanner;

public class Artemis {
    private static final String FILE_PATH = "./data/artemis.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Artemis(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ArtemisException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        ui.showWelcomeMessage();

        while (true) {
            String input = sc.nextLine();
            try {
                Parser.parseInput(input, tasks, ui, storage);
            } catch (ArtemisException e) {
                ui.showError("Oops, there might be invalid input..");
            }
            if (input.contains("bye")) {
                break;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Artemis(FILE_PATH).run();
    }
}
