public class Lindi {
    public static void printSeparator() {
        System.out.println("-".repeat(50));
    }

    public static void goodByeAndExit() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
        System.exit(0); // exit with code 0, terminates program
    }
    public static void main(String[] args) {
        String name = "Lindi";  // Log It N Do It -> LINDI
        printSeparator();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printSeparator();
        goodByeAndExit();
    }
}
