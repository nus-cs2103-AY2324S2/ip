import java.io.IOException;
import java.util.Scanner;

public class Yoda {
    private Storage storage;
    private TaskList tasks;
    private YodaUI yodaUI;

    public Yoda(String filePath) {
        this.storage = new Storage(filePath);
        try {
            // Load tasks from the storage
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            // If there's an error loading tasks, start with an empty task list
            System.out.println("Error loading tasks: " + e.getMessage());
            this.tasks = new TaskList(null);
        }
        // Initialize YodaUI with the loaded tasks
        this.yodaUI = new YodaUI("Yoda", tasks, storage);
    }

    public static void main(String[] args) {
        // Initialize Yoda with the file path for storing tasks
        Yoda yoda = new Yoda("data/yoda.txt");

        Scanner scanner = new Scanner(System.in);
        Parser commandParser = new Parser(yoda.yodaUI);

        // Display the greeting message
        yoda.yodaUI.printGreeting();

        while (yoda.yodaUI.isChatting()) {
            String input = scanner.nextLine();
            try {
                commandParser.parseAndExecute(input);
            } catch (Exception e) {
                yoda.yodaUI.printMessage("Error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
