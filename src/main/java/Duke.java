public class Duke {
    public static void main(String[] args) {
        Duke aurora1 = new Duke();
        aurora1.exeAurora();
    }

    /**
     * Method for execution.
     */
    public void exeAurora() {
        printALine();
        printOpeningMessage();
        printExitMessage();
    }

    /**
     * Method to print the greeting message.
     */
    public void printOpeningMessage() {
        String openingMessage = "How are you feeling? I'm Aurora, your personal schedule assistant. \n"
                + "What can I do for you?";
        System.out.println(openingMessage);
        printALine();
    }

    /**
     * Method to print the exit message.
     */
    public void printExitMessage() {
        String exitMessage = "Thank you for consulting with me, have a good day.";
        System.out.println(exitMessage);
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
