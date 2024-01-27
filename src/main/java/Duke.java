import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import task.taskList;

public class Duke {
    public static void main(String[] args) {
        final String FILE_LOCATION = "projects/personal/data/duke.txt";
        System.out.println("Hello! I'm KAI\n" + "What can i do for you?");

        taskList tasklist = new taskList();
        File file = new File(FILE_LOCATION);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File created!!");
            } catch (IOException e) {
                System.out.println("Error creating the file...");
            }
        } else {
            System.out.println("File already exists, you can continue");
        }

        Command.readFile(FILE_LOCATION, tasklist);
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                break;
            }
            Command.process(line, tasklist);
        }

        Command.writeFile(FILE_LOCATION, tasklist);
        scanner.close();
        System.out.println("Bye Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
