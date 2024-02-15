package jav;

import java.util.Scanner;

import jav.command.Command;
import jav.exception.InvalidCommandException;
import jav.exception.InvalidParamException;
import jav.manager.FileManager;
import jav.manager.ParserManager;
import jav.manager.StorageManager;
import jav.manager.UiManager;

/**
* The main program.
*/
public class Jav {
    private static boolean isExiting = false;
    public static void main(String[] args) {
        // Print any neccessary UI upon starting up
        // Got ASCII Word Art from https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Jav
        String logo =
                  "      ____.  _________   ____\n"
                + "     |    | /  _  \\   \\ /   /\n"
                + "     |    |/  /_\\  \\   Y   /\n"
                + " /\\__|    /    |    \\     /\n"
                + " \\________\\____|__  /\\___/\n"
                + "                  \\/\n"
                + "The Joy Amplifying Virtuoso!\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("<---------------------------------------------------------->");
        UiManager.getInstance().printGreeting();

        // Load any neccessary data
        Scanner scan = new Scanner(System.in);
        try {
            StorageManager.getInstance().load(FileManager.getInstance().loadStorageData());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Main program loop
        while (!isExiting) {
            // Get user input
            System.out.println("| User Input:");
            System.out.print("> ");
            String s = scan.nextLine();

            // Check if requested command can be found
            try {
                String cmd = s;
                String param = "";

                // Check if its a command with parameters
                if (s.indexOf(" ") != -1) {
                    cmd = s.substring(0, s.indexOf(' '));
                    param = s.substring(s.indexOf(' ') + 1);
                }

                // Parse and execute command
                Command c = ParserManager.getInstance().checkCommand(cmd, param);
                c.execute();
            } catch (InvalidCommandException e) {
                UiManager.getInstance().printInvalidCommand();
                UiManager.getInstance().echo(s);
            } catch (InvalidParamException e) {
                UiManager.getInstance().printInvalidParameters();
                UiManager.getInstance().echo(s);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        UiManager.getInstance().printExit();
        System.out.println("<---------------------------------------------------------->");
        scan.close();
    }

    public static void exit() {
        isExiting = true;
    }
}
