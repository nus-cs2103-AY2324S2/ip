import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Martin {
    private static String NAME = "Martin";
    protected static ArrayList<Task> todoList = new ArrayList<>();
    private static String FILEPATH = "./data/martin.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Command command;

    public Martin() {
        this.storage = new Storage(FILEPATH); // fixed file path for now 
        this.tasks = new TaskList(todoList);
        this.ui = new Ui();
        this.parser = new Parser();
        this.command = new Command(tasks, storage, ui, parser);
    }

    public void run() {
        ui.sayGreeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine().strip();
            ChatbotKeyword command = parser.parse(input);
            String remainingWords = parser.getRemainingWords(input);
            try {
                this.command.handleCommand(command, remainingWords);
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
        }
        ui.sayBye();
        sc.close();
    }
    
    public static void main(String[] args) {
        Martin martin = new Martin();
        martin.run();
    }
}
