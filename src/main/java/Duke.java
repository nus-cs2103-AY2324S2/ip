import java.util.Scanner; 

public class Duke {
    public static void main(String[] args) {
        String logo = 
                //   " ____        _        \n"
                // + "|  _ \\ _   _| | _____ \n"
                // + "| | | | | | | |/ / _ \\\n"
                // + "| |_| | |_| |   <  __/\n"
                // + "|____/ \\__,_|_|\\_\\___|\n";

                  "      ____.  _________   ____\n"
                + "     |    | /  _  \\   \\ /   /\n"
                + "     |    |/  /_\\  \\   Y   / \n"
                + " /\\__|    /    |    \\     /  \n"
                + " \\________\\____|__  /\\___/   \n"
                + "                  \\/         \n"
                + "The Joy Amplifying Virtuoso!\n";
                // Got ASCII Word Art from https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Jav

        System.out.println("Hello from\n" + logo);
        System.out.println("<---------------------------------------------------------->");
        MessagePrinter.PrintGreeting();

        boolean isExiting = false;
        Scanner scan = new Scanner(System.in);
        Storage storage = new Storage();
        while (!isExiting) {
            System.out.println("| User Input:");

            System.out.print("> ");
            String s = scan.nextLine();
            if (s.equals("bye") || s.equals("b")) { // Check if exiting
                isExiting = true;
            } else if (s.equals("list") || s.equals("l")) {
                MessagePrinter.PrintStorage(storage.Get());
            } else {
                if (s.indexOf(" ") == -1) { // Check if got cmd
                    MessagePrinter.PrintInvalid();
                    MessagePrinter.Echo(s);
                } else {
                    // Check the cmd given by user
                    String cmd = s.substring(0, s.indexOf(' '));
                    String parameters = s.substring(s.indexOf(' ') + 1);

                    if (cmd.equals("store") || cmd.equals("s")) {
                        MessagePrinter.PrintStoring();
                        storage.Store(parameters);
                    } else if (cmd.equals("mark") || cmd.equals("m")) {
                        if (Integer.parseInt(parameters) >= 1) {
                            if (storage.UpdateTask(Integer.parseInt(parameters) - 1, true)) {
                                MessagePrinter.PrintMarkingTask();
                            } else {
                                MessagePrinter.PrintInvalidParameters();
                            }
                        } else {
                            MessagePrinter.PrintInvalidParameters();
                        }
                    } else if (cmd.equals("unmark") || cmd.equals("u")) {
                        if (Integer.parseInt(parameters) >= 1) {
                            if (storage.UpdateTask(Integer.parseInt(parameters) - 1, false)) {
                                MessagePrinter.PrintUnmarkingTask();
                            } else {
                                MessagePrinter.PrintInvalidParameters();
                            }
                        } else {
                            MessagePrinter.PrintInvalidParameters();
                        }
                    } else {
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
}
