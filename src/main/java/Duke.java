public class Duke {
    public static void main(String[] args) {
        Duke.horizontalLine();
        System.out.println("Hello! I'm Ezra.\nWhat can I do for you?");
        Duke.horizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.horizontalLine();
    }

    public static void horizontalLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print("─");
        }
        System.out.println();
    }
}
