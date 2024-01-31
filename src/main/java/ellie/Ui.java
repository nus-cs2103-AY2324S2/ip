package ellie;

public class Ui {

    final static String horizontalLine = "____________________________________________________________";
    final static String logoEllie =
            " _______   ___       ___       ___  _______          \n" +
                    "|\\  ___ \\ |\\  \\     |\\  \\     |\\  \\|\\  ___ \\         \n" +
                    "\\ \\   __/|\\ \\  \\    \\ \\  \\    \\ \\  \\ \\   __/|        \n" +
                    " \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\ \\  \\_|/__      \n" +
                    "  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\ \\  \\_|\\ \\     \n" +
                    "   \\ \\_______\\ \\_______\\ \\_______\\ \\__\\ \\_______\\    \n" +
                    "    \\|_______|\\|_______|\\|_______|\\|__|\\|_______|    \n";


    public Ui() {

    }

    public void hello() {
        System.out.println("Hello! I'm Ellie, your CS2103T chat bot! I help by tracking your tasks!");
        System.out.println(logoEllie + "\n" + horizontalLine);
        System.out.println("What can I do for you? Type 'help' to see available commands! \n");
    }

    public void goodbye() {
        System.out.println("\n Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }




}
