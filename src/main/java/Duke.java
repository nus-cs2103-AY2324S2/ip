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
        PrintGreeting();

        boolean isExiting = false;
        Scanner scan = new Scanner(System.in);
        while (!isExiting) {
            System.out.println("| User Input:");

            System.out.print("> ");
            String s = scan.next();
            if (s.equals("bye")) {
                isExiting = true;
            } else {
                Echo(s);
            }
        }

        PrintExit();
        System.out.println("<---------------------------------------------------------->");
    }

    /*

    * The function prints the greeting message when the user enters
    * Returns void
    */
    public static void PrintGreeting() {
        String greetingMessage = "| Welcome! I'm JAV\n"
                               + "| How may I sprinkle a bit of happiness into your day today?";

        System.out.println(greetingMessage);
    }

    /*

    * The function prints the farewell message when the user exits
    * Returns void
    */
    public static void PrintExit() {
        String greetingMessage = "| Farewell!\n"
                               + "| May your days be filled with laughter and warmth!";

        System.out.println(greetingMessage);
    }

    
    /*

    * The function echos a given string
    * Returns void
    * Parameters: string
    */
    public static void Echo(String _input) {
        System.out.println("| Echoing input \n| " + _input);
    }
}
