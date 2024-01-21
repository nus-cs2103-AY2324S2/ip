public class Chatbot {
    private final String name;
    private static final String lineSep = "____________________________________________________________";

    public Chatbot(String name) {
        this.name = name;
    }

    private void printMessage(String message) {
        System.out.println(lineSep);
        System.out.println(message);
        System.out.println(lineSep);
    }

    public void greet() {
        printMessage("Hello! I'm " + this.name + "\n"
                        + "What can I do for you?");
    }

    public void exit() {
        printMessage(" Bye. Hope to see you again soon!");
    }
}