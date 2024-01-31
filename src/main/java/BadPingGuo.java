import java.io.*;
import java.util.Scanner;

public class BadPingGuo {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);

        String filename = "src/main/data/whiteSpace.txt";

        try {
            File file = new File(filename);
            FileReader fc = new FileReader(file);
            BufferedReader reader = new BufferedReader(fc);

            System.out.println("Welcome to White Space! You booted up your laptop and wonder what to do...");

            Tracker.suppressMessages = true;
            Tracker.parseTasks(file);
            Tracker.suppressMessages = false;

            Tracker.listTasks(reader);
            System.out.println("Waiting for something to happen?");

            // self note: update the file everytime an operation is complete.
            // Delete the old file, for loop all the tasks into new file. Rename it to old file name.
            while(true) {
                String request = sc.nextLine();
                if (request.equalsIgnoreCase("bye")) break;
                Tracker.ProcessQuery(request, file);
            }

            System.out.println("--------------------------------");
            System.out.println("Everything is going to be okay.");

            fc.close();
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("You've been living here for as long as... wait, no headspace detected?");
            System.out.println("Would you like to enter White Space? \n " +
                    "Only 'yes' will create the required files" );
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

    public static boolean makeFile() {
        try {
            File f = new File("src/main/data/whiteSpace.txt");
            return f.createNewFile();
        } catch (IOException e) {
            System.out.println("Humphrey has denied your entrance to white space! \n " +
                    "perhaps the write permissions aren't working?");
            return false;
        }
    }
}
