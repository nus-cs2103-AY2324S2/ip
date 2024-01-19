public class ConvoBot {
    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
    private static void printWelcomeMsg() {
        System.out.println(" Hello! I'm ConvoBot\n What can I do for you?");
    }
    private static void printExitMsg() {
        System.out.println(" Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        printHorizontalLine();
        printWelcomeMsg();
        printHorizontalLine();
        printExitMsg();
        printHorizontalLine();
    }
}
