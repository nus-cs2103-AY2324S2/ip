package guanguan;

import java.util.Scanner;

/**
 * The main class for the GuanGuan application.
 */
public class GuanGuan {
    private final Storage storage;
    private final TaskList items;
    private final Ui ui;

    /**
     * Constructor for GuanGuan.
     *
     * @param filePath path of text file to store data
     * @throws GgException if file path is invalid
     */
    public GuanGuan(String filePath) throws GgException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.items = new TaskList(storage.readData());
    }

    /**
     * Constructor for GuanGuan.
     *
     * @param filePath path of text file to store data
     * @param ui Ui class
     * @throws GgException if file path is invalid
     */
    public GuanGuan(String filePath, Ui ui) throws GgException {
        this.ui = ui;
        this.storage = new Storage(filePath);
        this.items = new TaskList(storage.readData());
    }

    /**
     * Responsibe for running the chatbot.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = true;

        ui.welcome();

        while (isValid) {
            assert scanner.hasNextLine() : "No more lines to read!";
            String input = scanner.nextLine();
            try {
                isValid = Parser.parse(input, items, ui);
                storage.saveData(items);
            } catch (GgException e) {
                ui.error(e.getMessage());
            }
            ui.emptyLine();
        }
    }

    public static void main(String[] args) throws GgException {
        new GuanGuan("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        assert input != null : "Input cannot be null";
        assert !input.isEmpty() : "Input cannot be empty";
        try {
            Parser.parse(input, items, ui);
            storage.saveData(items);
        } catch (GgException e) {
            ui.error(e.getMessage());
        }
        return ui.getTextOutput();
    }
}
