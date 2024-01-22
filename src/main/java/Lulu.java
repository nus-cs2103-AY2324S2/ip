public class Lulu {
    public Lulu() {

    }

    public void start() {
        System.out.println("Hello! I'm Lulu \nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        chatbot.start();
        chatbot.exit();
    }
}
