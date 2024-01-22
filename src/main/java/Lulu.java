public class Lulu {

    public Lulu() {

    }

    public String start() {
        return "Hello! I'm Lulu \nWhat can I do for you?";
    }

    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        System.out.println(chatbot.start());
        System.out.println(chatbot.exit());
    }
}
