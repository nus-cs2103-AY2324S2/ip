import java.util.Scanner;

public class Jav {
    public static Storage storage = new Storage();

    public static void main(String[] args) {
        String logo = 
                //   " ____        _        \n"
                // + "|  _ \\ _   _| | _____ \n"
                // + "| | | | | | | |/ / _ \\\n"
                // + "| |_| | |_| |   <  __/\n"
                // + "|____/ \\__,_|_|\\_\\___|\n";

                  "      ____.  _________   ____\n"
                + "     |    | /  _  \\   \\ /   /\n"
                + "     |    |/  /_\\  \\   Y   /\n"
                + " /\\__|    /    |    \\     /\n"
                + " \\________\\____|__  /\\___/\n"
                + "                  \\/\n"
                + "The Joy Amplifying Virtuoso!\n";
                // Got ASCII Word Art from https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Jav

        System.out.println("Hello from\n" + logo);
        System.out.println("<---------------------------------------------------------->");
        MessagePrinter.PrintGreeting();

        boolean isExiting = false;
        Scanner scan = new Scanner(System.in);
        while (!isExiting) {
            System.out.println("| User Input:");
            System.out.print("> ");
            String s = scan.nextLine();

            if (s.equals("bye") || s.equals("b")) { // Check if exiting
                isExiting = true;
            } else if (CheckPrintList(s)) {
            } else {
                if (s.indexOf(" ") == -1) { // Check if got cmd
                    MessagePrinter.PrintInvalid();
                    MessagePrinter.Echo(s);
                } else {
                    // Check the cmd given by user
                    String cmd = s.substring(0, s.indexOf(' '));
                    String parameters = s.substring(s.indexOf(' ') + 1);

                    if (CheckStoring(cmd, parameters)) {} 
                    else if (CheckMarking(cmd, parameters)) {} 
                    else {
                        MessagePrinter.PrintInvalid();
                        MessagePrinter.Echo(s);
                    }
                }
            }
        }

        MessagePrinter.PrintExit();
        System.out.println("<---------------------------------------------------------->");
        scan.close();
    }

    // Check if user wants to print the entire storage and prints if requested
    public static boolean CheckPrintList(String _s) {
        if (_s.equals("list") || _s.equals("l")) {
            MessagePrinter.PrintStorage(storage.Get());
            return true;
        } else {
            return false;
        }
    }


    // Check if user is storing and stores if requested
    public static boolean CheckStoring(String _cmd, String _param) {
        if (_cmd.equals("todo") || _cmd.equals("t")) {
            MessagePrinter.PrintStoring();
            storage.Store(_param, Storage.StorageType.TODO);
            return true;
        } else if (_cmd.equals("deadline") || _cmd.equals("d")) {
            MessagePrinter.PrintStoring();
            storage.Store(_param, Storage.StorageType.DEADLINE);
            return true;
        }  else if (_cmd.equals("event") || _cmd.equals("e")) {
            MessagePrinter.PrintStoring();
            storage.Store(_param, Storage.StorageType.EVENT);
            return true;
        } else {
            return false;
        }
    }
    
    // Check if user is marking/unmarking and marks/unmarks respectively if requested
    public static boolean CheckMarking(String _cmd, String _param) {
        if (_cmd.equals("mark") || _cmd.equals("m")) {
            if (Integer.parseInt(_param) >= 1) {
                if (storage.UpdateTask(Integer.parseInt(_param) - 1, true)) {
                    MessagePrinter.PrintMarkingTask();
                } else {
                    MessagePrinter.PrintInvalidParameters();
                }
            } else {
                MessagePrinter.PrintInvalidParameters();
            }
            return true;
        } else if (_cmd.equals("unmark") || _cmd.equals("u")) {
            if (Integer.parseInt(_param) >= 1) {
                if (storage.UpdateTask(Integer.parseInt(_param) - 1, false)) {
                    MessagePrinter.PrintUnmarkingTask();
                } else {
                    MessagePrinter.PrintInvalidParameters();
                }
            } else {
                MessagePrinter.PrintInvalidParameters();
            }
            return true;
        } else {
            return false;
        }
    }
}
