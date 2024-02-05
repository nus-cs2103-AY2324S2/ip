import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";

    public static void main(String[] args) {
        String name = "Georgie";
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCounter = 0;

        TaskReader.loadTasksFromFile(tasks, FILE_PATH);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String userInput = scanner.nextLine().trim();
                CommandHandler.handleCommand(userInput, tasks, taskCounter);
                TaskWriter.saveTasksToFile(tasks, FILE_PATH);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}