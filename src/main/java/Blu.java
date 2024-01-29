import java.io.IOException;
import java.util.Scanner;
import exceptions.BluException;

public class Blu {
    private static final String PROMPT = "> ";
    private static final String STORAGE_PATH = "./data.csv";
    private static Storage storage;

    private static String readUserInput(Scanner scanner) {
        System.out.print(PROMPT);
        return scanner.nextLine();
    }
    
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        try {
            storage = new Storage(STORAGE_PATH);
            taskList = storage.loadTasks();
        } catch (IOException | BluException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        Chatbot bot = new Chatbot("Blu", taskList, storage);
        InputHandler inputHandler = new InputHandler();
        bot.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput = readUserInput(scanner);
        while (!userInput.equals("bye")) {
            if (!userInput.isEmpty()) {
                try {
                    inputHandler.handleInput(userInput, bot);
                    storage.close();
                } catch (IOException | BluException e) {
                    System.out.println(e.getMessage());
                } 
            }
            userInput = readUserInput(scanner);
        }
        scanner.close();
        bot.exit();
    }
}