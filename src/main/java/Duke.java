import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static <string> void main(String[] args) throws DukeException, IOException {
        String logo = "__________________________________\n" +
                "Hello! I'm Tim \n" +
                "What can i do for you? \n" +
                "__________________________________\n";
        String exit = "Bye. Hope to see you again soon!\n" +
                "__________________________________";
        System.out.println(logo);

        ArrayList<Task> storage = new ArrayList<>();
        Path filePath = Paths.get("./data/duke.txt");
        Path writeToFile = FileHandler.addTasks(storage, filePath);
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            }
            TaskHandler.addTasks(input, storage, writeToFile);
            input = scan.nextLine();

        }

    }
}
