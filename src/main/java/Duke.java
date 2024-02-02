import duke.task.TaskException;
import duke.task.TaskList;
import duke.util.FileManager;
import duke.util.Parser;
import duke.util.Ui;

import java.util.*;
public class Duke {
    private String filePath = "./data/saveData.txt";
    private FileManager storage;  // This is the storage class just named duke.util.FileManager
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        this.filePath = filePath;
        this.storage = new FileManager(filePath);
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (Exception e) {
            tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks, this.ui);
    }

    public void start(){
        ui.intro();
        Scanner input = new Scanner(System.in);
        boolean isDone = false;
        while(!isDone) {
            String current = input.nextLine();
            try {
                parser.read(current);
            } catch (TaskException e) {
                System.out.println(e);
            }
            isDone = parser.isExit();
        }
        storage.saveFile(tasks.giveList());
    }

    public static void main(String[] args){
        new Duke("./data/saveData.txt").start();
    }
}
