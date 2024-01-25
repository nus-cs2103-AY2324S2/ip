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
        
        System.out.println("____________________________________________________________");
        PrintGreeting();
        System.out.println("____________________________________________________________");

    }

    /*

    * The function prints the greeting to greet the user
    * Returns void
    */
    public static void PrintGreeting() {
        String greetingMessage = "Hello! I'm JAV\n"
                               + "What can I do for you?\n";

        System.out.println(greetingMessage);
    }
}
