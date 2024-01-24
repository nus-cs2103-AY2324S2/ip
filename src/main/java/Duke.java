public class Duke {
    private static final String chatbotName = "Sylvia";

    private static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Cya!");
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        greet();
        exit();
    }
}
