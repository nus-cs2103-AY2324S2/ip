import java.util.Scanner;

import commands.Command;
import storage.StorageManager;
import tasklist.TaskList; 
import ui.Ui;

public class Cal {
    private static TaskList tasks;
    private static StorageManager storageManager;
    private static Ui ui;

    public static void init() {
        storageManager = new StorageManager();
        tasks = storageManager.load();
    }

    public static void run() {
        ui = new Ui();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand(sc);
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, storageManager);
                isExit = c.isExit();
            } catch(Exception e) {
                ui.showErrorMsg(e);
            } finally {
                ui.showLine();
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        init();
        Ui.showGreeting();
        run();
    }
}
