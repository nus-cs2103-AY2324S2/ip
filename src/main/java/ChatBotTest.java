public class ChatBotTest {
    public static void main(String[] args) {
        Ping p = new Ping();
        String name = p.name;

        System.out.println("Hello! I'm "+name+
                "\nWhat can I do for you?");
        System.out.println("Bye. Hope to see you again soon!");
    }
}
