package Irwyn;

import Commands.Command;
import Commands.CommandParser;
import Irwyn.Exceptions.IrwynException;
import Irwyn.Tasks.TaskList;
import Misc.StorageManager;
import Misc.Ui;

import java.util.Scanner;


public class Irwyn {

    private static TaskList taskList;
    private static Ui ui;
    private static StorageManager storageManager;
    private static final String filePath = System.getProperty("user.dir") + "/storage/taskData.txt";

    private static void load() {
        try {
            ui = new Ui();
            storageManager = new StorageManager(filePath);
            taskList = new TaskList(storageManager.load());
            ui.start();
        } catch (Exception e) {
            System.out.println("Irwyn failed to start");
        }
    }
    public static void run(){
        boolean isExit = false;
        while (!isExit) {
            try {
                String commands = new Scanner(System.in).nextLine();
                Command c = CommandParser.parse(commands);
                c.execute(taskList, ui, storageManager);
                isExit = c.isExit();
            } catch (IrwynException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Irwyn.load();
        Irwyn.run();
    }
}
