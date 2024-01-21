public class Duke {
    static String horizontalLine = "――――――――――――――――――――――――――――――――――――――――";
    static String chatbotName = "Bob";

    public static void main(String[] args) {
        greet();
        exit();
    }

    public static void greet() {
        System.out.println(horizontalLine + "\n" + "Hello! I'm " + chatbotName + "\n"
                + "What can I do for you?\n" + horizontalLine);
    }

    public static void exit() {
        System.out.println(horizontalLine + "\n" + "Bye! Hope to see you again soon!\n" + horizontalLine);
    }

}
