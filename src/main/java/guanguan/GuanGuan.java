package guanguan;

import java.util.Scanner;

/**
 * Main class of chatbot.
 */
public class GuanGuan {
    private final Storage storage;
    private final TaskList items;
    private final Ui ui;

    /**
     * Constructor for GuanGuan.
     *
     * @param filePath path of text file to store data
     * @throws GGException if file path is invalid
     */
    public GuanGuan(String filePath) throws GGException {
        this.ui = new Ui();
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
            String command = scanner.nextLine();
            try {
                isValid = Parser.parse(command, items, ui);
                storage.saveData(items);
            } catch (GGException e) {
                ui.error(e.getMessage());
            }
            ui.emptyLine();
        }
    }

    public static void main(String[] args) throws GGException {
        new GuanGuan("data/test.txt").run();
    }
}