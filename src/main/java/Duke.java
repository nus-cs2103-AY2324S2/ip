import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        // String indent = "    ";
        // String newLine = indent + "____________________________________________________________ \n";
        // String greeting = indent + "Good day good sir! I am Chatimous Maximous, here to help you with your every need!\n";
        // System.out.println(newLine + greeting);

        ChatBot Chatty = new ChatBot();
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        String message;

        while (Chatty.hasFinished() == false) {
            try {
                message = Chatty.interact(input);
            } catch (UnrecognizedException e) {
                message = "Apologies Sir! I am not equipped to handle such a command!";
            } catch (MissingInputException e) {
                message = "Apologies Sir! You did not include the name of the item!";
            }
            System.out.println(message);
            input = reader.nextLine();
        }

        reader.close();

    }
}
