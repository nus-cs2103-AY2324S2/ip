package BadApple.main;

import BadApple.task.Parser;
import BadApple.task.Storage;
import BadApple.task.TaskList;
import BadApple.task.Messenger;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * This is the entry point to the program's code
 * It handles the creation of required files and
 * kickstarts the application by reading the file
 * in the drive.
 */
public class BadPingGuo {
    public static void main(String[] args) {
        Ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String filename = "src/main/data/whiteSpace.txt";

        try {
            File file = new File(filename);
            FileReader fc = new FileReader(file);
            BufferedReader reader = new BufferedReader(fc);

            Messenger.suppressMessages = true;
            Storage.parseTasks(file);
            Messenger.suppressMessages = false;

            TaskList.listTasks(reader);
            System.out.println("Waiting for something to happen?");

            while(true) {
                String request = sc.nextLine();
                if (request.equalsIgnoreCase("bye")) break;
                Parser.ProcessQuery(request, file);
            }

            Ui.sayGoodbye();
            fc.close();
            reader.close();

        } catch (FileNotFoundException e) {
            Ui.askToCreateFile();
            String askToCreateFile = sc.nextLine();
            if (askToCreateFile.equalsIgnoreCase("yes")) {
                makeFile();
            }
        } catch (IOException e) {
            System.out.println("unable to process file");
        } catch (BadAppleException be) {
            System.out.println(be.toString());
        }

    }

    /**
     * Creates a file and directory for saving data
     * to the local drive.
     */
    public static void makeFile() {
        try {
            File f1 = new File("src/main/data");
            File f = new File("src/main/data/whiteSpace.txt");
            if (f1.mkdir()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Humphrey has denied your entrance to white space! \n " +
                    "perhaps the write permissions aren't working?");
        }
    }
}
