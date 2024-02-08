import yoda.yodaUI.YodaUI;
import yoda.parser.Parser;
import yoda.storage.Storage;
import yoda.task.TaskList;
import java.util.Scanner;
import java.io.IOException;

/**
 * Yoda is a chatbot that helps users to manage their tasks.
 * It can add, delete, and list tasks, as well as mark tasks as done.
 * Yoda can also find tasks by searching for keywords.
 */
public class Yoda {
    private final YodaUI YODA_UI;
    private Parser parser;

    public Yoda(String filePath) {
        Storage storage = new Storage(filePath);
        TaskList tasks;
        try {
            // Load tasks from the storage
            tasks = storage.loadTasks();
        } catch (IOException e) {
            // If there's an error loading tasks, start with an empty task list
            System.out.println("Error loading tasks: " + e.getMessage());
            tasks = new TaskList(null);
        }
        // Initialize YodaUI with the loaded tasks
        this.YODA_UI = new YodaUI("Yoda", tasks, storage);
    }

    public void run() {
        YODA_UI.printGreeting();
        Scanner scanner = new Scanner(System.in);
        Parser commandParser = new Parser(YODA_UI);
        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            try {
                commandParser.parseAndExecute(input);
            } catch (Exception e) {
                YODA_UI.printMessage("Error occurred: " + e.getMessage());
                isRunning = false;
            }

        }
    }

    public static void main(String[] args) {
        // Initialize Yoda with the file path for storing tasks
        Yoda yoda = new Yoda("data/yoda.txt");
        yoda.run();
    }
}
