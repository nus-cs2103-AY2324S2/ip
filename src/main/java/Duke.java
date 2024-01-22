import java.util.Scanner;
public class Duke {
    private Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Duke aurora1 = new Duke();
        aurora1.exeAurora();
    }

    /**
     * Method for execution.
     */
    public void exeAurora() {
        printOpeningMessage();
        boolean exit = false;
        while(!exit) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                exit = true;
            } else {
                echoCommand(command);
            }
        }
        printExitMessage();
    }

    /**
     * Method to print the greeting message.
     */
    public void printOpeningMessage() {
        printALine();
        String openingMessage = "How are you feeling? I'm Aurora, your personal schedule assistant. \n"
                + "What can I do for you?";
        System.out.println(openingMessage);
        printALine();
    }

    /**
     * Method to print the exit message.
     */
    public void printExitMessage() {
        printALine();
        String exitMessage = "Thank you for consulting with me, have a good day.";
        System.out.println(exitMessage);
        printALine();
    }

    /**
     * Method to echo a given command.
     */
    public void echoCommand(String command) {
        printALine();
        System.out.println(command);
        printALine();
    }

    /**
     * Method to print a line for separation.
     */
    public void printALine() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

}
