package dylanbot;

import java.io.*;
public class DylanBot {
    private Ui ui;
    private Storage st;
    private Parser ps;
    private TaskList tl;

    public DylanBot(String filePath) {
        this.ui = new Ui();
        this.st = new Storage(filePath);
        try {
            this.tl = new TaskList(this.st.loadDataFromFile(), ui);
            ui.print("Loaded data from file");
        } catch (IOException e) {
            System.out.println(e);
            ui.displayIOError();
            this.tl = new TaskList(ui);
            ui.print("No data to load, created new file");
        }
    }

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
            ui.displayIOError();
        }
        ui.sendExit();
    }

    public static void main(String[] args) {
        new DylanBot("./data/DylanBotData.txt").run();
    }
}


