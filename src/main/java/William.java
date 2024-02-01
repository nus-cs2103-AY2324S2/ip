import java.io.FileNotFoundException;
import java.util.List;

import exceptions.WilliamException;
import tasks.Task;
import utilities.Parser;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

/**
 * The William class is the main class for this chatbot (the other classes have been divided into
 * packages: commands, exceptions, tasks and utilities)
 */
public class William {

    private static String filePath = "data/tasks.txt";
    private TaskList taskList = new TaskList();
    private Storage storage;
    private Ui ui;

    /**
     * Constructor to intialise the tasklist, storage and UI
     * 
     * @param filePath The file path
     */
    public William(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            List<Task> tasks = storage.loadFromFile();
            this.taskList = new TaskList(tasks);
        } catch (FileNotFoundException | WilliamException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    /**
     * Run the chatbot
     */
    public void run() {
        ui.openingTitle();
        Parser parser = new Parser(taskList, storage);
        ui.interactWithUser(parser);
    }

    public static void main(String[] args) {
        new William(filePath).run();
    }
}
