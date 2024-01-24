public class Rochin {
    public static void main(String[] args) {
        Rochin chatbot = new Rochin();
        chatbot.start();
    }

    public void start() {
        greetMessage();

        exitMessage();
    }

    private void greetMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Rochin.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private void exitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

}
