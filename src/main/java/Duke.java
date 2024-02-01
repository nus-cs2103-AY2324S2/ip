import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        Storage storage = new Storage("././data/tasks.txt");
        TaskList taskList = storage.loadFromFile();
        Intro hi = new Intro();
        hi.response();
        Scanner sc = new Scanner(System.in);

        try {
            while (sc.hasNext()) {
                String command = sc.nextLine();
                Action response = CommandParser.parseCommand(command, taskList);
                storage.writeToFile(taskList);
                if (command.equals("bye")) {
                    break;
                }
            }
        } catch (DukeException | IOException e) {
            System.out.println("Sorry " + e.getMessage());
        }
    }
}



