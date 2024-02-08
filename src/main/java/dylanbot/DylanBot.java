package dylanbot;

import java.io.IOException;

/**
 * Represents a DylanBot
 */
public class DylanBot {
    private Ui ui;
    private Storage st;
    private Parser ps;
    private TaskList tl;

    /**
     * Creates a DylanBot with the specified save file at the provided filePath
     *
     * @param filePath The file path of the desired file
     */
    public DylanBot(String filePath) {
        this.ui = new Ui();
        this.st = new Storage(filePath, ui);
        try {
            this.tl = new TaskList(this.st.loadDataFromFile(), ui);
            Ui.print("Loaded data from file");
        } catch (IOException e) {
            System.out.println(e);
            ui.displayIoError();
            this.tl = new TaskList(ui);
            Ui.print("No data to load, created new file");
        }
    }

    /**
     * Runs DylanBot based on the provided user input
     */
    public void run() {
        ui.sendGreeting();
        Parser ps = new Parser(ui, tl);
        String command;
        try {
            while (!(command = ui.takeInput()).equals("bye")) {
                ps.process(command);
            }
        } catch (DylanBotException e) {
            ui.displayError(e);
        }
        try {
            st.saveDataToFile(tl);
        } catch (IOException e) {
            ui.displayIoError();
        }
        ui.sendExit();
    }

    public static void main(String[] args) {
        new DylanBot("./data/DylanBotData.txt").run();
    }
}
