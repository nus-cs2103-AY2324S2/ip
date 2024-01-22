import java.util.*;

public class Lulu {
    private List<String> items;

    public Lulu() {
        this.items = new ArrayList<>();
    }

    public void start() {
        System.out.println("Hello! I'm Lulu \nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println(input);
        }
    }

    public void insert() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("list")) {
                for (int i = 0; i < this.items.size(); i++) {
                    String output = i + ". " + this.items.get(i);
                    System.out.println(output);
                }
                break;
            }
            this.items.add(input);
            String output = "added: " + input;
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        chatbot.start();
        chatbot.insert();
        chatbot.exit();
    }
}
